package com.ayuntamiento.gestion_institucional.mapper;

import com.ayuntamiento.gestion_institucional.dto.DepartamentoDTO;
import com.ayuntamiento.gestion_institucional.model.Departamento;
import org.springframework.stereotype.Component;

@Component
public class DepartamentoMapper {

<<<<<<< HEAD
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
=======
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
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
        return Departamento.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }
}