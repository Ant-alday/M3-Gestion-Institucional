package com.ayuntamiento.gestion_institucional.service;

import com.ayuntamiento.gestion_institucional.dto.PersonalDTO;
import java.util.List;

// INTERFACE — define que metodos existen
// La implementacion real esta en service/impl/PersonalServiceImpl.java

public interface PersonalService {

    List<PersonalDTO> listarTodos();

    PersonalDTO buscarPorId(Long id);

    List<PersonalDTO> listarPorDepartamento(Long departamentoId);

    List<PersonalDTO> listarDisponibles();

    List<PersonalDTO> listarDisponiblesPorDepartamento(Long departamentoId);

    PersonalDTO guardar(PersonalDTO dto);

    PersonalDTO actualizar(Long id, PersonalDTO dto);

    // MS2 llama esto al asignar (false) o liberar (true) un tecnico
    PersonalDTO cambiarDisponibilidad(Long id, Boolean disponible);

    void eliminar(Long id);
}