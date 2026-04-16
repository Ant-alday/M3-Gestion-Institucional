package com.ayuntamiento.gestion_institucional.service;

import com.ayuntamiento.gestion_institucional.dto.DepartamentoDTO;
import java.util.List;

// INTERFACE — define que metodos existen
// La implementacion real esta en service/impl/DepartamentoServiceImpl.java

public interface DepartamentoService {

    List<DepartamentoDTO> listarTodos();

    DepartamentoDTO buscarPorId(Long id);

    DepartamentoDTO guardar(DepartamentoDTO dto);

    DepartamentoDTO actualizar(Long id, DepartamentoDTO dto);

    void eliminar(Long id);
}