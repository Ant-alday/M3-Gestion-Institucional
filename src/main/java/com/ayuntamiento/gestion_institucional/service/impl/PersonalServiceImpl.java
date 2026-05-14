package com.ayuntamiento.gestion_institucional.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth.client_sdk.client.UsuarioClient;
import com.auth.client_sdk.dto.UsuarioAuthDto;
import com.ayuntamiento.gestion_institucional.dto.PersonalDTO;
import com.ayuntamiento.gestion_institucional.exception.ResourceNotFoundException;
import com.ayuntamiento.gestion_institucional.mapper.PersonalMapper;
import com.ayuntamiento.gestion_institucional.model.Cuadrilla;
import com.ayuntamiento.gestion_institucional.model.Personal;
import com.ayuntamiento.gestion_institucional.model.Puesto;
import com.ayuntamiento.gestion_institucional.repository.CuadrillaRepository;
import com.ayuntamiento.gestion_institucional.repository.PersonalRepository;
import com.ayuntamiento.gestion_institucional.repository.PuestoRepository;
import com.ayuntamiento.gestion_institucional.service.PersonalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository  personalRepository;
    private final PuestoRepository    puestoRepository;
    private final CuadrillaRepository cuadrillaRepository;
    private final PersonalMapper      personalMapper;
    private final UsuarioClient       usuarioClient;

    // ── Consultas a MS1 via libreria ─────────────────────────────

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioAuthDto> getTodosUsuariosDeMS1() {
        try {
            return usuarioClient.obtenerTodosLosUsuarios();
        } catch (Exception e) {
            log.error("❌ Error de conexion con Auth-Service al listar usuarios: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioAuthDto> getEmpleadosDeMS1() {
        try {
            return usuarioClient.obtenerTodosLosUsuarios()
                    .stream()
                    .filter(u -> "EMPLEADO".equalsIgnoreCase(u.rol())
                              || "ADMIN".equalsIgnoreCase(u.rol()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("❌ Error de conexion con Auth-Service: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    // ── CRUD de Personal ─────────────────────────────────────────

    @Override
    @Transactional(readOnly = true)
    public List<PersonalDTO> findAll() {
        // ✅ CORREGIDO: si AUTH falla, se retorna el personal con nombre "MS1 no disponible"
        Map<Long, UsuarioAuthDto> usuariosPorId;
        try {
            usuariosPorId = usuarioClient.obtenerTodosLosUsuarios()
                    .stream()
                    .collect(Collectors.toMap(UsuarioAuthDto::id, u -> u));
        } catch (Exception e) {
            log.warn("⚠️ Auth-Service no disponible en findAll, se retorna personal sin datos de usuario: {}", e.getMessage());
            usuariosPorId = Collections.emptyMap();
        }

        final Map<Long, UsuarioAuthDto> mapaFinal = usuariosPorId;
        return personalRepository.findAll().stream()
                .map(p -> personalMapper.toDTO(p, mapaFinal.get(p.getUsuarioId())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PersonalDTO findById(Long id) {
        Personal p = personalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Personal no encontrado con id: " + id));
        return personalMapper.toDTO(p, fetchUsuario(p.getUsuarioId()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonalDTO> findByDisponibilidad(Boolean disponible) {
        return personalRepository.findByDisponible(disponible)
                .stream()
                .map(p -> personalMapper.toDTO(p, fetchUsuario(p.getUsuarioId())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonalDTO> findByCuadrilla(Long cuadrillaId) {
        return personalRepository.findByCuadrillaId(cuadrillaId)
                .stream()
                .map(p -> personalMapper.toDTO(p, fetchUsuario(p.getUsuarioId())))
                .collect(Collectors.toList());
    }

    @Override
    public PersonalDTO save(PersonalDTO dto) {
        UsuarioAuthDto usuario = fetchUsuarioOrThrow(dto.getUsuarioId());

        if (personalRepository.existsByUsuarioId(dto.getUsuarioId())) {
            throw new IllegalArgumentException(
                "El usuario con id " + dto.getUsuarioId() +
                " ya esta registrado como personal.");
        }

        Puesto puesto = puestoRepository.findById(dto.getPuestoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Puesto no encontrado con id: " + dto.getPuestoId()));
        Cuadrilla cuadrilla = cuadrillaRepository.findById(dto.getCuadrillaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cuadrilla no encontrada con id: " + dto.getCuadrillaId()));

        Personal p = personalMapper.toModel(dto);
        p.setPuesto(puesto);
        p.setCuadrilla(cuadrilla);

        return personalMapper.toDTO(personalRepository.save(p), usuario);
    }

    @Override
    public PersonalDTO update(Long id, PersonalDTO dto) {
        Personal p = personalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Personal no encontrado con id: " + id));

        UsuarioAuthDto usuario;
        if (!p.getUsuarioId().equals(dto.getUsuarioId())) {
            usuario = fetchUsuarioOrThrow(dto.getUsuarioId());
            if (personalRepository.existsByUsuarioId(dto.getUsuarioId())) {
                throw new IllegalArgumentException(
                    "El usuario con id " + dto.getUsuarioId() +
                    " ya esta registrado como personal.");
            }
            p.setUsuarioId(dto.getUsuarioId());
        } else {
            usuario = fetchUsuario(p.getUsuarioId());
        }

        Puesto puesto = puestoRepository.findById(dto.getPuestoId())
                .orElseThrow(() -> new ResourceNotFoundException("Puesto no encontrado"));
        Cuadrilla cuadrilla = cuadrillaRepository.findById(dto.getCuadrillaId())
                .orElseThrow(() -> new ResourceNotFoundException("Cuadrilla no encontrada"));

        p.setPuesto(puesto);
        p.setCuadrilla(cuadrilla);
        if (dto.getDisponible() != null) p.setDisponible(dto.getDisponible());

        return personalMapper.toDTO(personalRepository.save(p), usuario);
    }

    @Override
    public PersonalDTO cambiarDisponibilidad(Long id, Boolean disponible) {
        Personal p = personalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Personal no encontrado con id: " + id));
        p.setDisponible(disponible);
        return personalMapper.toDTO(personalRepository.save(p), fetchUsuario(p.getUsuarioId()));
    }

    @Override
    public void delete(Long id) {
        if (!personalRepository.existsById(id))
            throw new ResourceNotFoundException(
                    "Personal no encontrado con id: " + id);
        personalRepository.deleteById(id);
    }

    // ── Metodos auxiliares ────────────────────────────────────────

    private UsuarioAuthDto fetchUsuario(Long usuarioId) {
        try {
            return usuarioClient.obtenerUsuario(usuarioId);
        } catch (Exception e) {
            log.warn("❌ Error de conexion con Auth-Service: {}", e.getMessage());
            return new UsuarioAuthDto(null, "Usuario Desconocido", "Sin Rol", "0000000", null);
        }
    }

    private UsuarioAuthDto fetchUsuarioOrThrow(Long usuarioId) {
        try {
            UsuarioAuthDto usuario = usuarioClient.obtenerUsuario(usuarioId);
            if (usuario == null || "Usuario Desconocido".equals(usuario.nombre())) {
                throw new ResourceNotFoundException(
                    "El usuario con id " + usuarioId +
                    " no existe en MS1 (Usuarios).");
            }
            return usuario;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("❌ Error de conexion con Auth-Service: {}", e.getMessage());
            throw new RuntimeException(
                "No se pudo validar el usuario en MS1. " +
                "Verifica que el servicio de Usuarios este corriendo.");
        }
    }

    @Override
    public String getNombreUsuarioDesdeMS1(Long usuarioId) {
        try {
            UsuarioAuthDto usuario = usuarioClient.obtenerUsuario(usuarioId);
            return usuario.email();
        } catch (Exception e) {
            log.error("❌ Error de red al intentar obtener el nombre: {}", e.getMessage());
            return "Usuario Desconocido";
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getPermisosByPersonal(Long id) {
        Personal p = personalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Personal no encontrado con id: " + id));
        Puesto puesto = puestoRepository.findById(p.getPuesto().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Puesto no encontrado"));
        return puesto.getPermisos();
    }
}