package com.ayuntamiento.gestion_institucional.controller;

import com.ayuntamiento.gestion_institucional.dto.PersonalDTO;
import com.ayuntamiento.gestion_institucional.service.PersonalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
=======
import org.springframework.http.*;
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
<<<<<<< HEAD
@RequestMapping("/api/personal")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
=======
@RequestMapping("/api/gestion/personal")
@RequiredArgsConstructor
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
public class PersonalController {

    private final PersonalService personalService;

<<<<<<< HEAD
    // GET http://localhost:8083/api/personal
    @GetMapping
    public ResponseEntity<List<PersonalDTO>> listarTodos() {
        return ResponseEntity.ok(personalService.listarTodos());
    }

    // GET http://localhost:8083/api/personal/1
    @GetMapping("/{id}")
    public ResponseEntity<PersonalDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(personalService.buscarPorId(id));
    }

    // GET http://localhost:8083/api/personal/departamento/1
    @GetMapping("/departamento/{departamentoId}")
    public ResponseEntity<List<PersonalDTO>> listarPorDepartamento(
            @PathVariable Long departamentoId) {
        return ResponseEntity.ok(personalService.listarPorDepartamento(departamentoId));
    }

    // GET http://localhost:8083/api/personal/disponibles
    // El MS2 usa esto para ver quien puede atender una incidencia
    @GetMapping("/disponibles")
    public ResponseEntity<List<PersonalDTO>> listarDisponibles() {
        return ResponseEntity.ok(personalService.listarDisponibles());
    }

    // GET http://localhost:8083/api/personal/disponibles/departamento/1
    @GetMapping("/disponibles/departamento/{departamentoId}")
    public ResponseEntity<List<PersonalDTO>> listarDisponiblesPorDepartamento(
            @PathVariable Long departamentoId) {
        return ResponseEntity.ok(
                personalService.listarDisponiblesPorDepartamento(departamentoId));
    }

    // POST http://localhost:8083/api/personal
    // Body: { "nombre":"Juan Perez", "puesto":"Tecnico", "departamentoId": 1 }
    @PostMapping
    public ResponseEntity<PersonalDTO> guardar(
            @Valid @RequestBody PersonalDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personalService.guardar(dto));
    }

    // PUT http://localhost:8083/api/personal/1
    @PutMapping("/{id}")
    public ResponseEntity<PersonalDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PersonalDTO dto) {
        return ResponseEntity.ok(personalService.actualizar(id, dto));
    }

    // PATCH http://localhost:8083/api/personal/1/disponibilidad?disponible=false
    // MS2 llama esto al asignar un tecnico (false) o liberarlo (true)
=======
    @GetMapping
    public ResponseEntity<List<PersonalDTO>> findAll() {
        return ResponseEntity.ok(personalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personalService.findById(id));
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<PersonalDTO>> findDisponibles() {
        return ResponseEntity.ok(personalService.findByDisponibilidad(true));
    }

    @GetMapping("/cuadrilla/{cuadrillaId}")
    public ResponseEntity<List<PersonalDTO>> findByCuadrilla(
            @PathVariable Long cuadrillaId) {
        return ResponseEntity.ok(personalService.findByCuadrilla(cuadrillaId));
    }

    @PostMapping
    public ResponseEntity<PersonalDTO> save(@Valid @RequestBody PersonalDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personalService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PersonalDTO dto) {
        return ResponseEntity.ok(personalService.update(id, dto));
    }

>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
    @PatchMapping("/{id}/disponibilidad")
    public ResponseEntity<PersonalDTO> cambiarDisponibilidad(
            @PathVariable Long id,
            @RequestParam Boolean disponible) {
        return ResponseEntity.ok(personalService.cambiarDisponibilidad(id, disponible));
    }

<<<<<<< HEAD
    // DELETE http://localhost:8083/api/personal/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        personalService.eliminar(id);
=======
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personalService.delete(id);
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
        return ResponseEntity.noContent().build();
    }
}