package com.ayuntamiento.gestion_institucional.dto;

<<<<<<< HEAD
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
=======
import jakarta.validation.constraints.*;
import lombok.*;
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartamentoDTO {

<<<<<<< HEAD
    private Long id; // null al crear, lo genera la BD

    @NotBlank(message = "El nombre del departamento es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    private String nombre;

    private String descripcion; // opcional
=======
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    private String descripcion;
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
}