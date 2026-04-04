package com.ayuntamiento.gestion_institucional.mapper;

import com.ayuntamiento.gestion_institucional.dto.DepartamentoDTO;
import com.ayuntamiento.gestion_institucional.model.Departamento;
import org.springframework.stereotype.Component;

@Component
public class DepartamentoMapper {

    public DepartamentoDTO toDTO(Departamento model) {
        if (model == null) return null;
        return DepartamentoDTO.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .descripcion(model.getDescripcion())
                .build();
    }

    public Departamento toModel(DepartamentoDTO dto) {
        if (dto == null) return null;
        return Departamento.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }
}