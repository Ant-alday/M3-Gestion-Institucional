package com.ayuntamiento.gestion_institucional.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalDTO {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 150)
    private String nombre;

    @NotNull(message = "El puesto es obligatorio")
    private Long puestoId;

    @NotNull(message = "La cuadrilla es obligatoria")
    private Long cuadrillaId;

    private Boolean disponible;

    
    private String puestoNombre;
    private String cuadrillaNombre;
    private String departamentoNombre;
}