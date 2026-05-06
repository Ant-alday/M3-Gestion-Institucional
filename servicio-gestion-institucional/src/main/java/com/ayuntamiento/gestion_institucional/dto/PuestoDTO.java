package com.ayuntamiento.gestion_institucional.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PuestoDTO {

    private Long id;

    @NotBlank(message = "El nombre del puesto no puede estar vacio")
    @Size(max = 100)
    private String nombre;
}