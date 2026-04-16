package com.ayuntamiento.gestion_institucional.service;

import com.ayuntamiento.gestion_institucional.dto.PersonalDTO;
import java.util.List;

<<<<<<< HEAD
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
=======
public interface PersonalService {
    List<PersonalDTO> findAll();
    
    PersonalDTO findById(Long id);
    
    List<PersonalDTO> findByDisponibilidad(Boolean disponible);
    
    List<PersonalDTO> findByCuadrilla(Long cuadrillaId);
    
    PersonalDTO save(PersonalDTO dto);
    
    PersonalDTO update(Long id, PersonalDTO dto);
    
    PersonalDTO cambiarDisponibilidad(Long id, Boolean disponible);
    
    void delete(Long id);
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
}