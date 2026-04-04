package com.ayuntamiento.gestion_institucional.service.impl;

import com.ayuntamiento.gestion_institucional.dto.PersonalDTO;
import com.ayuntamiento.gestion_institucional.exception.ResourceNotFoundException;
import com.ayuntamiento.gestion_institucional.mapper.PersonalMapper;
import com.ayuntamiento.gestion_institucional.model.*;
import com.ayuntamiento.gestion_institucional.repository.*;
import com.ayuntamiento.gestion_institucional.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;
    private final PuestoRepository puestoRepository;
    private final CuadrillaRepository cuadrillaRepository;
    private final PersonalMapper personalMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PersonalDTO> findAll() {
        return personalRepository.findAll()
                .stream()
                .map(personalMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
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
    }

    @Override
    public PersonalDTO cambiarDisponibilidad(Long id, Boolean disponible) {
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
        personalRepository.deleteById(id);
    }
}