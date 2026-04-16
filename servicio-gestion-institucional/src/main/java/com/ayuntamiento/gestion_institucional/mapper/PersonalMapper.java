package com.ayuntamiento.gestion_institucional.mapper;

import com.ayuntamiento.gestion_institucional.dto.PersonalDTO;
<<<<<<< HEAD
import com.ayuntamiento.gestion_institucional.model.Departamento;
=======
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
import com.ayuntamiento.gestion_institucional.model.Personal;
import org.springframework.stereotype.Component;

@Component
public class PersonalMapper {

<<<<<<< HEAD
    // Entity → DTO  (para devolver al cliente)
    public PersonalDTO toDTO(Personal personal) {
        return PersonalDTO.builder()
                .id(personal.getId())
                .nombre(personal.getNombre())
                .puesto(personal.getPuesto())
                .disponible(personal.getDisponible())
                .departamentoId(personal.getDepartamento().getId())
                .departamentoNombre(personal.getDepartamento().getNombre())
                .build();
    }

    // DTO → Entity  (para guardar en BD)
    // El departamento ya viene buscado desde el Service
    public Personal toEntity(PersonalDTO dto, Departamento departamento) {
        return Personal.builder()
                .nombre(dto.getNombre())
                .puesto(dto.getPuesto())
                .departamento(departamento)
                .disponible(dto.getDisponible() != null ? dto.getDisponible() : true)
=======
    public PersonalDTO toDTO(Personal model) {
        if (model == null) return null;
        return PersonalDTO.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .puestoId(model.getPuesto().getId())
                .puestoNombre(model.getPuesto().getNombre())
                .cuadrillaId(model.getCuadrilla().getId())
                .cuadrillaNombre(model.getCuadrilla().getNombre())
                .departamentoNombre(model.getCuadrilla().getDepartamento().getNombre())
                .disponible(model.getDisponible())
                .build();
    }

    public Personal toModel(PersonalDTO dto) {
        if (dto == null) return null;
        // puesto y cuadrilla se asignan en el Service
        return Personal.builder()
                .nombre(dto.getNombre())
                .disponible(dto.getDisponible() != null ? dto.getDisponible() : Boolean.TRUE)
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
                .build();
    }
}