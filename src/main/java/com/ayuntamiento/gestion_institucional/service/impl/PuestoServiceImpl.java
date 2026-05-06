package com.ayuntamiento.gestion_institucional.service.impl;

import com.ayuntamiento.gestion_institucional.dto.PuestoDTO;
import com.ayuntamiento.gestion_institucional.exception.ResourceNotFoundException;
import com.ayuntamiento.gestion_institucional.mapper.PuestoMapper;
import com.ayuntamiento.gestion_institucional.model.Puesto;
import com.ayuntamiento.gestion_institucional.repository.PuestoRepository;
import com.ayuntamiento.gestion_institucional.service.PuestoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PuestoServiceImpl implements PuestoService {

    private final PuestoRepository puestoRepository;
    private final PuestoMapper puestoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PuestoDTO> findAll() {
        return puestoRepository.findAll()
                .stream()
                .map(puestoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PuestoDTO findById(Long id) {
        Puesto p = puestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Puesto no encontrado con id: " + id));
        return puestoMapper.toDTO(p);
    }

    @Override
    public PuestoDTO save(PuestoDTO dto) {
        Puesto p = puestoMapper.toModel(dto);
        return puestoMapper.toDTO(puestoRepository.save(p));
    }

    @Override
    public PuestoDTO update(Long id, PuestoDTO dto) {
        Puesto p = puestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Puesto no encontrado con id: " + id));
        p.setNombre(dto.getNombre());
        p.setPermisos(dto.getPermisos());
        return puestoMapper.toDTO(puestoRepository.save(p));
    }

    @Override
    public void delete(Long id) {
        if (!puestoRepository.existsById(id))
            throw new ResourceNotFoundException(
                    "Puesto no encontrado con id: " + id);
        puestoRepository.deleteById(id);
    }

	@Override
	public PuestoDTO findAuthById(Long id) {
        Puesto p = puestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Puesto no encontrado con id: " + id));
        return puestoMapper.toDTO(p);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<String> getPermisos(Long id) {
	    Puesto p = puestoRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException(
	                    "Puesto no encontrado con id: " + id));
	    return p.getPermisos();
	}
	
	
}