package com.ayuntamiento.gestion_institucional.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuadrillaDTO {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 100)
    private String nombre;

    @NotNull(message = "El departamento es obligatorio")
    private Long departamentoId;

    private String departamentoNombre;   // solo en respuesta
}