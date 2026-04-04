package com.ayuntamiento.gestion_institucional.mapper;

import com.ayuntamiento.gestion_institucional.dto.PersonalDTO;
import com.ayuntamiento.gestion_institucional.model.Personal;
import org.springframework.stereotype.Component;

@Component
public class PersonalMapper {

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
                .build();
    }
}