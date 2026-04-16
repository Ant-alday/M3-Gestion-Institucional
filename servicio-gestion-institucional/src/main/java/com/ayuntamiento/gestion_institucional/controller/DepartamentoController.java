package com.ayuntamiento.gestion_institucional.controller;

import com.ayuntamiento.gestion_institucional.dto.DepartamentoDTO;
import com.ayuntamiento.gestion_institucional.service.DepartamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

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
        return ResponseEntity.noContent().build();
    }
}