package com.ayuntamiento.gestion_institucional.mapper;

import org.springframework.stereotype.Component;

import com.ayuntamiento.gestion_institucional.dto.PuestoDTO;
import com.ayuntamiento.gestion_institucional.model.Puesto;

@Component
public class PuestoMapper {

    public PuestoDTO toDTO(Puesto model) {
        if (model == null) return null;
        return PuestoDTO.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .permisos(model.getPermisos())
                .build();
    }

    public Puesto toModel(PuestoDTO dto) {
        if (dto == null) return null;
        return Puesto.builder()
                .nombre(dto.getNombre())
                .permisos(dto.getPermisos())
                .build();
    }
}