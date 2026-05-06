package com.ayuntamiento.gestion_institucional.service.impl;

import com.ayuntamiento.gestion_institucional.dto.DepartamentoDTO;
import com.ayuntamiento.gestion_institucional.exception.ResourceNotFoundException;
import com.ayuntamiento.gestion_institucional.mapper.DepartamentoMapper;
import com.ayuntamiento.gestion_institucional.model.Departamento;
import com.ayuntamiento.gestion_institucional.repository.DepartamentoRepository;
import com.ayuntamiento.gestion_institucional.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
=======
import org.springframework.transaction.annotation.Transactional;
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
<<<<<<< HEAD
=======
@Transactional
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final DepartamentoMapper departamentoMapper;

    @Override
<<<<<<< HEAD
    public List<DepartamentoDTO> listarTodos() {
=======
    @Transactional(readOnly = true)
    public List<DepartamentoDTO> findAll() {
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
        return departamentoRepository.findAll()
                .stream()
                .map(departamentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
<<<<<<< HEAD
    public DepartamentoDTO buscarPorId(Long id) {
=======
    @Transactional(readOnly = true)
    public DepartamentoDTO findById(Long id) {
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
        Departamento dep = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Departamento no encontrado con id: " + id));
        return departamentoMapper.toDTO(dep);
    }

    @Override
<<<<<<< HEAD
    public DepartamentoDTO guardar(DepartamentoDTO dto) {
        Departamento dep = departamentoMapper.toEntity(dto);
=======
    public DepartamentoDTO save(DepartamentoDTO dto) {
        Departamento dep = departamentoMapper.toModel(dto);
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
        return departamentoMapper.toDTO(departamentoRepository.save(dep));
    }

    @Override
<<<<<<< HEAD
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
=======
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
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
        departamentoRepository.deleteById(id);
    }
}