package com.ayuntamiento.gestion_institucional.service.impl;

import com.ayuntamiento.gestion_institucional.dto.PersonalDTO;
import com.ayuntamiento.gestion_institucional.exception.ResourceNotFoundException;
import com.ayuntamiento.gestion_institucional.mapper.PersonalMapper;
import com.ayuntamiento.gestion_institucional.model.Departamento;
import com.ayuntamiento.gestion_institucional.model.Personal;
import com.ayuntamiento.gestion_institucional.repository.DepartamentoRepository;
import com.ayuntamiento.gestion_institucional.repository.PersonalRepository;
import com.ayuntamiento.gestion_institucional.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;
    private final DepartamentoRepository departamentoRepository;
    private final PersonalMapper personalMapper;

    @Override
    public List<PersonalDTO> listarTodos() {
        return personalRepository.findAll()
                .stream()
                .map(personalMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
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
    }

    @Override
    public PersonalDTO cambiarDisponibilidad(Long id, Boolean disponible) {
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
        personalRepository.deleteById(id);
    }
}