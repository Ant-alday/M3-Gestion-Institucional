package com.ayuntamiento.gestion_institucional.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

<<<<<<< HEAD
// Se lanza cuando no se encuentra un registro en la BD
// Spring devuelve automaticamente HTTP 404

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
=======
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
    }
}