package com.ayuntamiento.gestion_institucional.service.impl;

import com.ayuntamiento.gestion_institucional.dto.DepartamentoDTO;
import com.ayuntamiento.gestion_institucional.exception.ResourceNotFoundException;
import com.ayuntamiento.gestion_institucional.mapper.DepartamentoMapper;
import com.ayuntamiento.gestion_institucional.model.Departamento;
import com.ayuntamiento.gestion_institucional.repository.DepartamentoRepository;
import com.ayuntamiento.gestion_institucional.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final DepartamentoMapper departamentoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DepartamentoDTO> findAll() {
        return departamentoRepository.findAll()
                .stream()
                .map(departamentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DepartamentoDTO findById(Long id) {
        Departamento dep = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Departamento no encontrado con id: " + id));
        return departamentoMapper.toDTO(dep);
    }

    @Override
    public DepartamentoDTO save(DepartamentoDTO dto) {
        Departamento dep = departamentoMapper.toModel(dto);
        return departamentoMapper.toDTO(departamentoRepository.save(dep));
    }

    @Override
    public DepartamentoDTO update(Long id, DepartamentoDTO dto) {
        Departamento dep = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Departamento no encontrado con id: " + id));
        dep.setNombre(dto.getNombre());
        dep.setDescripcion(dto.getDescripcion());
        return departamentoMapper.toDTO(departamentoRepository.save(dep));
    }

    @Override
    public void delete(Long id) {
        if (!departamentoRepository.existsById(id))
            throw new ResourceNotFoundException(
                    "Departamento no encontrado con id: " + id);
        departamentoRepository.deleteById(id);
    }
}