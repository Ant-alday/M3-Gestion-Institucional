package com.ayuntamiento.gestion_institucional.service.impl;

import com.ayuntamiento.gestion_institucional.dto.DepartamentoDTO;
import com.ayuntamiento.gestion_institucional.exception.ResourceNotFoundException;
import com.ayuntamiento.gestion_institucional.mapper.DepartamentoMapper;
import com.ayuntamiento.gestion_institucional.model.Departamento;
import com.ayuntamiento.gestion_institucional.repository.DepartamentoRepository;
import com.ayuntamiento.gestion_institucional.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final DepartamentoMapper departamentoMapper;

    @Override
    public List<DepartamentoDTO> listarTodos() {
        return departamentoRepository.findAll()
                .stream()
                .map(departamentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartamentoDTO buscarPorId(Long id) {
        Departamento dep = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Departamento no encontrado con id: " + id));
        return departamentoMapper.toDTO(dep);
    }

    @Override
    public DepartamentoDTO guardar(DepartamentoDTO dto) {
        Departamento dep = departamentoMapper.toEntity(dto);
        return departamentoMapper.toDTO(departamentoRepository.save(dep));
    }

    @Override
    public DepartamentoDTO actualizar(Long id, DepartamentoDTO dto) {
        Departamento existente = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Departamento no encontrado con id: " + id));
        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        return departamentoMapper.toDTO(departamentoRepository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        if (!departamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Departamento no encontrado con id: " + id);
        }
        departamentoRepository.deleteById(id);
    }
}