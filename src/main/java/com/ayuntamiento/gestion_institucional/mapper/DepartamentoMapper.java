package com.ayuntamiento.gestion_institucional.mapper;

import com.ayuntamiento.gestion_institucional.dto.DepartamentoDTO;
import com.ayuntamiento.gestion_institucional.model.Departamento;
import org.springframework.stereotype.Component;

@Component
public class DepartamentoMapper {

    // Entity → DTO  (para devolver al cliente)
    public DepartamentoDTO toDTO(Departamento departamento) {
        return DepartamentoDTO.builder()
                .id(departamento.getId())
                .nombre(departamento.getNombre())
                .descripcion(departamento.getDescripcion())
                .build();
    }

    // DTO → Entity  (para guardar en BD)
    public Departamento toEntity(DepartamentoDTO dto) {
        return Departamento.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }
}