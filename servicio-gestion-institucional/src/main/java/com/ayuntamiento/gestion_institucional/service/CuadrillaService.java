package com.ayuntamiento.gestion_institucional.service;

import com.ayuntamiento.gestion_institucional.dto.CuadrillaDTO;
import java.util.List;

public interface CuadrillaService {
    List<CuadrillaDTO> findAll();
    CuadrillaDTO findById(Long id);
    List<CuadrillaDTO> findByDepartamento(Long departamentoId);
    CuadrillaDTO save(CuadrillaDTO dto);
    CuadrillaDTO update(Long id, CuadrillaDTO dto);
    void delete(Long id);
}