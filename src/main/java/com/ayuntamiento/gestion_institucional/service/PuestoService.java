package com.ayuntamiento.gestion_institucional.service;

import com.ayuntamiento.gestion_institucional.dto.PuestoDTO;
import java.util.List;

public interface PuestoService {
    List<PuestoDTO> findAll();
    PuestoDTO findById(Long id);
    PuestoDTO save(PuestoDTO dto);
    PuestoDTO update(Long id, PuestoDTO dto);
    void delete(Long id);
    PuestoDTO findAuthById(Long id);
    
    List<String> getPermisos(Long id);
    
}