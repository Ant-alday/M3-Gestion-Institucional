package com.ayuntamiento.gestion_institucional.service;

import com.ayuntamiento.gestion_institucional.dto.DepartamentoDTO;
import java.util.List;

public interface DepartamentoService {
    List<DepartamentoDTO> findAll();
    DepartamentoDTO findById(Long id);
    DepartamentoDTO save(DepartamentoDTO dto);
    DepartamentoDTO update(Long id, DepartamentoDTO dto);
    void delete(Long id);
}