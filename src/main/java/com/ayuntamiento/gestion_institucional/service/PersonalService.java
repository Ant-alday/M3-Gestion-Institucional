package com.ayuntamiento.gestion_institucional.service;

import com.auth.client_sdk.dto.UsuarioAuthDto;
import com.ayuntamiento.gestion_institucional.dto.PersonalDTO;

import java.util.List;

public interface PersonalService {

    // CRUD de personal
    List<PersonalDTO> findAll();
    PersonalDTO findById(Long id);
    List<PersonalDTO> findByDisponibilidad(Boolean disponible);
    List<PersonalDTO> findByCuadrilla(Long cuadrillaId);
    PersonalDTO save(PersonalDTO dto);
    PersonalDTO update(Long id, PersonalDTO dto);
    PersonalDTO cambiarDisponibilidad(Long id, Boolean disponible);
    void delete(Long id);

    // Consultas a MS1 usando la libreria client-sdk
    List<UsuarioAuthDto> getTodosUsuariosDeMS1();
    List<UsuarioAuthDto> getEmpleadosDeMS1();
    
    String getNombreUsuarioDesdeMS1(Long usuarioId);
    
    List<String> getPermisosByPersonal(Long id);
}