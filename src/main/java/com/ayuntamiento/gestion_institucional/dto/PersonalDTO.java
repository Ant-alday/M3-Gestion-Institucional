package com.ayuntamiento.gestion_institucional.dto;

import jakarta.validation.constraints.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalDTO {

    private Long id;

    @NotNull(message = "El usuarioId es obligatorio. Debe ser el ID del empleado en MS1.")
    private Long usuarioId;

    @NotNull(message = "El puestoId es obligatorio")
    private Long puestoId;

    @NotNull(message = "La cuadrillaId es obligatoria")
    private Long cuadrillaId;

    private Boolean disponible;

    // Campos enriquecidos desde MS1 via UsuarioAuthDto (solo en respuesta)
    private String nombre;
    private String email;
    private String telefono;

    // Campos enriquecidos desde MS3 (solo en respuesta)
    private String puestoNombre;
    private String cuadrillaNombre;
    private String departamentoNombre;
}