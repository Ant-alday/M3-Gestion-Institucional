package com.ayuntamiento.gestion_institucional.mapper;

import com.auth.client_sdk.dto.UsuarioAuthDto;
import com.ayuntamiento.gestion_institucional.dto.PersonalDTO;
import com.ayuntamiento.gestion_institucional.model.Personal;
import org.springframework.stereotype.Component;

@Component
public class PersonalMapper {

    /**
     * Convierte Personal a DTO enriquecido.
     * Recibe UsuarioAuthDto de la libreria de MS1 para agregar
     * nombre, email, rol y telefono.
     *
     * @param model   entidad Personal de la BD
     * @param usuario datos del usuario desde la libreria (puede ser null si MS1 no responde)
     */
    public PersonalDTO toDTO(Personal model, UsuarioAuthDto usuario) {
        if (model == null) return null;

        PersonalDTO dto = PersonalDTO.builder()
                .id(model.getId())
                .usuarioId(model.getUsuarioId())
                .puestoId(model.getPuesto().getId())
                .puestoNombre(model.getPuesto().getNombre())
                .cuadrillaId(model.getCuadrilla().getId())
                .cuadrillaNombre(model.getCuadrilla().getNombre())
                .departamentoNombre(model.getCuadrilla().getDepartamento().getNombre())
                .disponible(model.getDisponible())
                .build();

        // Si la libreria de MS1 respondio, enriquecer con datos del usuario
        if (usuario != null) {
            dto.setNombre(usuario.nombre());
            dto.setEmail(usuario.email());
            dto.setTelefono(usuario.telefono());
        } else {
            dto.setNombre("Usuario ID: " + model.getUsuarioId() + " (MS1 no disponible)");
        }

        return dto;
    }

    public Personal toModel(PersonalDTO dto) {
        if (dto == null) return null;
        return Personal.builder()
                .usuarioId(dto.getUsuarioId())
                .disponible(dto.getDisponible() != null ? dto.getDisponible() : Boolean.TRUE)
                .build();
    }
}