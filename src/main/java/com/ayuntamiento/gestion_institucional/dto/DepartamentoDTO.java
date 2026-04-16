package com.ayuntamiento.gestion_institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Sirve tanto para recibir datos del cliente (POST/PUT)
// como para devolver la respuesta (GET)
//
// Ejemplo POST body:
// { "nombre": "CAPACH", "descripcion": "Agua potable" }
//
// Ejemplo GET respuesta:
// { "id": 1, "nombre": "CAPACH", "descripcion": "Agua potable" }

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartamentoDTO {

    private Long id; // null al crear, lo genera la BD

    @NotBlank(message = "El nombre del departamento es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    private String nombre;

    private String descripcion; // opcional
}