package com.ayuntamiento.gestion_institucional.dto;

<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Sirve tanto para recibir datos del cliente (POST/PUT)
// como para devolver la respuesta (GET)
//
// Ejemplo POST body:
// { "nombre": "Juan Perez", "puesto": "Tecnico", "departamentoId": 1 }
//
// Ejemplo GET respuesta:
// { "id": 1, "nombre": "Juan Perez", "puesto": "Tecnico",
//   "departamentoId": 1, "departamentoNombre": "CAPACH", "disponible": true }
=======
import jakarta.validation.constraints.*;
import lombok.*;
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalDTO {

<<<<<<< HEAD
    private Long id; // null al crear, lo genera la BD

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    private String nombre;

    @NotBlank(message = "El puesto es obligatorio")
    @Size(max = 100, message = "El puesto no puede superar 100 caracteres")
    private String puesto;

    @NotNull(message = "El id del departamento es obligatorio")
    private Long departamentoId;

    private String departamentoNombre; // solo viene en la respuesta GET

    private Boolean disponible = true; // true por defecto al crear
=======
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
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
}