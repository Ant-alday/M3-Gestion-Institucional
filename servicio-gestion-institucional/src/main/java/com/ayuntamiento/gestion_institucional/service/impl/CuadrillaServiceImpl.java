package com.ayuntamiento.gestion_institucional.service.impl;

import com.ayuntamiento.gestion_institucional.dto.CuadrillaDTO;
import com.ayuntamiento.gestion_institucional.exception.ResourceNotFoundException;
import com.ayuntamiento.gestion_institucional.mapper.CuadrillaMapper;
import com.ayuntamiento.gestion_institucional.model.Cuadrilla;
import com.ayuntamiento.gestion_institucional.model.Departamento;
import com.ayuntamiento.gestion_institucional.repository.CuadrillaRepository;
import com.ayuntamiento.gestion_institucional.repository.DepartamentoRepository;
import com.ayuntamiento.gestion_institucional.service.CuadrillaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CuadrillaServiceImpl implements CuadrillaService {

    private final CuadrillaRepository cuadrillaRepository;
    private final DepartamentoRepository departamentoRepository;
    private final CuadrillaMapper cuadrillaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CuadrillaDTO> findAll() {
        return cuadrillaRepository.findAll()
                .stream()
                .map(cuadrillaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CuadrillaDTO findById(Long id) {
        return cuadrillaMapper.toDTO(cuadrillaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cuadrilla no encontrada con id: " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CuadrillaDTO> findByDepartamento(Long departamentoId) {
        return cuadrillaRepository.findByDepartamentoId(departamentoId)
                .stream()
                .map(cuadrillaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CuadrillaDTO save(CuadrillaDTO dto) {
        Departamento dep = departamentoRepository.findById(dto.getDepartamentoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Departamento no encontrado con id: " + dto.getDepartamentoId()));
        Cuadrilla cuadrilla = cuadrillaMapper.toModel(dto);
        cuadrilla.setDepartamento(dep);
        return cuadrillaMapper.toDTO(cuadrillaRepository.save(cuadrilla));
    }

    @Override
    public CuadrillaDTO update(Long id, CuadrillaDTO dto) {
        Cuadrilla cuadrilla = cuadrillaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cuadrilla no encontrada con id: " + id));
        Departamento dep = departamentoRepository.findById(dto.getDepartamentoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Departamento no encontrado"));
        cuadrilla.setNombre(dto.getNombre());
        cuadrilla.setDepartamento(dep);
        return cuadrillaMapper.toDTO(cuadrillaRepository.save(cuadrilla));
    }

    @Override
    public void delete(Long id) {
        if (!cuadrillaRepository.existsById(id))
            throw new ResourceNotFoundException(
                    "Cuadrilla no encontrada con id: " + id);
        cuadrillaRepository.deleteById(id);
    }
}