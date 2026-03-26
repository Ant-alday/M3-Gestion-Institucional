package com.ayuntamiento.gestion_institucional.mapper;

import com.ayuntamiento.gestion_institucional.dto.PersonalDTO;
import com.ayuntamiento.gestion_institucional.model.Departamento;
import com.ayuntamiento.gestion_institucional.model.Personal;
import org.springframework.stereotype.Component;

@Component
public class PersonalMapper {

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
                .build();
    }
}