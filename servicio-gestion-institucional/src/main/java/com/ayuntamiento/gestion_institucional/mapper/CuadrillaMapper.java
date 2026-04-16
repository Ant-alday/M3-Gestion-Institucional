package com.ayuntamiento.gestion_institucional.mapper;

import com.ayuntamiento.gestion_institucional.dto.CuadrillaDTO;
import com.ayuntamiento.gestion_institucional.model.Cuadrilla;
import org.springframework.stereotype.Component;

@Component
public class CuadrillaMapper {

    public CuadrillaDTO toDTO(Cuadrilla model) {
        if (model == null) return null;
        return CuadrillaDTO.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .departamentoId(model.getDepartamento().getId())
                .departamentoNombre(model.getDepartamento().getNombre())
                .build();
    }

    public Cuadrilla toModel(CuadrillaDTO dto) {
        if (dto == null) return null;
        // departamento se asigna en el Service
        return Cuadrilla.builder()
                .nombre(dto.getNombre())
                .build();
    }
}