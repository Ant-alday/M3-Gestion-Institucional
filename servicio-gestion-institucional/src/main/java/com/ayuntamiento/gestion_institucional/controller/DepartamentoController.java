package com.ayuntamiento.gestion_institucional.controller;

import com.ayuntamiento.gestion_institucional.dto.DepartamentoDTO;
import com.ayuntamiento.gestion_institucional.service.DepartamentoService;
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
@RequestMapping("/api/departamentos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
=======
@RequestMapping("/api/gestion/departamentos")
@RequiredArgsConstructor
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
public class DepartamentoController {

    private final DepartamentoService departamentoService;

<<<<<<< HEAD
    // GET http://localhost:8083/api/departamentos
    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> listarTodos() {
        return ResponseEntity.ok(departamentoService.listarTodos());
    }

    // GET http://localhost:8083/api/departamentos/1
    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(departamentoService.buscarPorId(id));
    }

    // POST http://localhost:8083/api/departamentos
    // Body: { "nombre": "CAPACH", "descripcion": "Agua potable" }
    @PostMapping
    public ResponseEntity<DepartamentoDTO> guardar(
            @Valid @RequestBody DepartamentoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departamentoService.guardar(dto));
    }

    // PUT http://localhost:8083/api/departamentos/1
    // Body: { "nombre": "CAPACH", "descripcion": "Nueva descripcion" }
    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody DepartamentoDTO dto) {
        return ResponseEntity.ok(departamentoService.actualizar(id, dto));
    }

    // DELETE http://localhost:8083/api/departamentos/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        departamentoService.eliminar(id);
=======
    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> findAll() {
        return ResponseEntity.ok(departamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(departamentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DepartamentoDTO> save(@Valid @RequestBody DepartamentoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departamentoService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody DepartamentoDTO dto) {
        return ResponseEntity.ok(departamentoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departamentoService.delete(id);
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
        return ResponseEntity.noContent().build();
    }
}