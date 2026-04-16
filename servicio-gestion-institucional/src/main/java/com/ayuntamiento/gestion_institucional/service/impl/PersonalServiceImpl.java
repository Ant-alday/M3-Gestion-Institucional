package com.ayuntamiento.gestion_institucional.service.impl;

import com.ayuntamiento.gestion_institucional.dto.PersonalDTO;
import com.ayuntamiento.gestion_institucional.exception.ResourceNotFoundException;
import com.ayuntamiento.gestion_institucional.mapper.PersonalMapper;
<<<<<<< HEAD
import com.ayuntamiento.gestion_institucional.model.Departamento;
import com.ayuntamiento.gestion_institucional.model.Personal;
import com.ayuntamiento.gestion_institucional.repository.DepartamentoRepository;
import com.ayuntamiento.gestion_institucional.repository.PersonalRepository;
import com.ayuntamiento.gestion_institucional.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
=======
import com.ayuntamiento.gestion_institucional.model.*;
import com.ayuntamiento.gestion_institucional.repository.*;
import com.ayuntamiento.gestion_institucional.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
<<<<<<< HEAD
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;
    private final DepartamentoRepository departamentoRepository;
    private final PersonalMapper personalMapper;

    @Override
    public List<PersonalDTO> listarTodos() {
=======
@Transactional
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;
    private final PuestoRepository puestoRepository;
    private final CuadrillaRepository cuadrillaRepository;
    private final PersonalMapper personalMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PersonalDTO> findAll() {
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
        return personalRepository.findAll()
                .stream()
                .map(personalMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
<<<<<<< HEAD
    public PersonalDTO buscarPorId(Long id) {
        Personal personal = personalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Personal no encontrado con id: " + id));
        return personalMapper.toDTO(personal);
    }

    @Override
    public List<PersonalDTO> listarPorDepartamento(Long departamentoId) {
        return personalRepository.findByDepartamentoId(departamentoId)
                .stream()
                .map(personalMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonalDTO> listarDisponibles() {
        return personalRepository.findByDisponibleTrue()
                .stream()
                .map(personalMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonalDTO> listarDisponiblesPorDepartamento(Long departamentoId) {
        return personalRepository.findByDepartamentoIdAndDisponibleTrue(departamentoId)
                .stream()
                .map(personalMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonalDTO guardar(PersonalDTO dto) {
        Departamento departamento = departamentoRepository
                .findById(dto.getDepartamentoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Departamento no encontrado con id: " + dto.getDepartamentoId()));
        Personal personal = personalMapper.toEntity(dto, departamento);
        return personalMapper.toDTO(personalRepository.save(personal));
    }

    @Override
    public PersonalDTO actualizar(Long id, PersonalDTO dto) {
        Personal existente = personalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Personal no encontrado con id: " + id));
        Departamento departamento = departamentoRepository
                .findById(dto.getDepartamentoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Departamento no encontrado con id: " + dto.getDepartamentoId()));
        existente.setNombre(dto.getNombre());
        existente.setPuesto(dto.getPuesto());
        existente.setDepartamento(departamento);
        existente.setDisponible(dto.getDisponible());
        return personalMapper.toDTO(personalRepository.save(existente));
=======
    @Transactional(readOnly = true)
    public PersonalDTO findById(Long id) {
        return personalMapper.toDTO(personalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Personal no encontrado con id: " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonalDTO> findByDisponibilidad(Boolean disponible) {
        return personalRepository.findByDisponible(disponible)
                .stream()
                .map(personalMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonalDTO> findByCuadrilla(Long cuadrillaId) {
        return personalRepository.findByCuadrillaId(cuadrillaId)
                .stream()
                .map(personalMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonalDTO save(PersonalDTO dto) {
        Puesto puesto = puestoRepository.findById(dto.getPuestoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Puesto no encontrado con id: " + dto.getPuestoId()));
        Cuadrilla cuadrilla = cuadrillaRepository.findById(dto.getCuadrillaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cuadrilla no encontrada con id: " + dto.getCuadrillaId()));
        Personal p = personalMapper.toModel(dto);
        p.setPuesto(puesto);
        p.setCuadrilla(cuadrilla);
        return personalMapper.toDTO(personalRepository.save(p));
    }

    @Override
    public PersonalDTO update(Long id, PersonalDTO dto) {
        Personal p = personalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Personal no encontrado con id: " + id));
        Puesto puesto = puestoRepository.findById(dto.getPuestoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Puesto no encontrado"));
        Cuadrilla cuadrilla = cuadrillaRepository.findById(dto.getCuadrillaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cuadrilla no encontrada"));
        p.setNombre(dto.getNombre());
        p.setPuesto(puesto);
        p.setCuadrilla(cuadrilla);
        if (dto.getDisponible() != null) p.setDisponible(dto.getDisponible());
        return personalMapper.toDTO(personalRepository.save(p));
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
    }

    @Override
    public PersonalDTO cambiarDisponibilidad(Long id, Boolean disponible) {
<<<<<<< HEAD
        Personal personal = personalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Personal no encontrado con id: " + id));
        personal.setDisponible(disponible);
        return personalMapper.toDTO(personalRepository.save(personal));
    }

    @Override
    public void eliminar(Long id) {
        if (!personalRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Personal no encontrado con id: " + id);
        }
=======
        Personal p = personalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Personal no encontrado con id: " + id));
        p.setDisponible(disponible);
        return personalMapper.toDTO(personalRepository.save(p));
    }

    @Override
    public void delete(Long id) {
        if (!personalRepository.existsById(id))
            throw new ResourceNotFoundException(
                    "Personal no encontrado con id: " + id);
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
        personalRepository.deleteById(id);
    }
}