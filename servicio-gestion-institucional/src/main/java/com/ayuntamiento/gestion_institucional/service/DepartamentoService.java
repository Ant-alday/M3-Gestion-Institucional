package com.ayuntamiento.gestion_institucional.service;

import com.ayuntamiento.gestion_institucional.dto.DepartamentoDTO;
import java.util.List;

<<<<<<< HEAD
// INTERFACE — define que metodos existen
// La implementacion real esta en service/impl/DepartamentoServiceImpl.java

public interface DepartamentoService {

    List<DepartamentoDTO> listarTodos();

    DepartamentoDTO buscarPorId(Long id);

    DepartamentoDTO guardar(DepartamentoDTO dto);

    DepartamentoDTO actualizar(Long id, DepartamentoDTO dto);

    void eliminar(Long id);
=======
public interface DepartamentoService {
    List<DepartamentoDTO> findAll();
    DepartamentoDTO findById(Long id);
    DepartamentoDTO save(DepartamentoDTO dto);
    DepartamentoDTO update(Long id, DepartamentoDTO dto);
    void delete(Long id);
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
}