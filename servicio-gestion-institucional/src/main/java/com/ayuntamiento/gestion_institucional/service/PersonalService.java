package com.ayuntamiento.gestion_institucional.service;

import com.ayuntamiento.gestion_institucional.dto.PersonalDTO;
import java.util.List;

public interface PersonalService {
    List<PersonalDTO> findAll();
    
    PersonalDTO findById(Long id);
    
    List<PersonalDTO> findByDisponibilidad(Boolean disponible);
    
    List<PersonalDTO> findByCuadrilla(Long cuadrillaId);
    
    PersonalDTO save(PersonalDTO dto);
    
    PersonalDTO update(Long id, PersonalDTO dto);
    
    PersonalDTO cambiarDisponibilidad(Long id, Boolean disponible);
    
    void delete(Long id);
}