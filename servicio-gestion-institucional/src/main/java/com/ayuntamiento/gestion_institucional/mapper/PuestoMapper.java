package com.ayuntamiento.gestion_institucional.mapper;

import com.ayuntamiento.gestion_institucional.dto.PuestoDTO;
import com.ayuntamiento.gestion_institucional.model.Puesto;
import org.springframework.stereotype.Component;

@Component
public class PuestoMapper {

    public PuestoDTO toDTO(Puesto model) {
        if (model == null) return null;
        return PuestoDTO.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .build();
    }

    public Puesto toModel(PuestoDTO dto) {
        if (dto == null) return null;
        return Puesto.builder()
                .nombre(dto.getNombre())
                .build();
    }
}