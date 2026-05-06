package com.ayuntamiento.gestion_institucional.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PuestoDTO {

    private Long id;

    @NotBlank(message = "El nombre del puesto no puede estar vacio")
    @Size(max = 100)
    private String nombre;
    
    List<String> permisos;
}