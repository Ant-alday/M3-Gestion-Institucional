package com.ayuntamiento.gestion_institucional.controller;

import com.ayuntamiento.gestion_institucional.dto.CuadrillaDTO;
import com.ayuntamiento.gestion_institucional.service.CuadrillaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gestion/cuadrillas")
@RequiredArgsConstructor
public class CuadrillaController {

    private final CuadrillaService cuadrillaService;

    @GetMapping
    public ResponseEntity<List<CuadrillaDTO>> findAll() {
        return ResponseEntity.ok(cuadrillaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuadrillaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cuadrillaService.findById(id));
    }

    @GetMapping("/departamento/{departamentoId}")
    public ResponseEntity<List<CuadrillaDTO>> findByDepartamento(
            @PathVariable Long departamentoId) {
        return ResponseEntity.ok(cuadrillaService.findByDepartamento(departamentoId));
    }

    @PostMapping
    public ResponseEntity<CuadrillaDTO> save(@Valid @RequestBody CuadrillaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cuadrillaService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuadrillaDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody CuadrillaDTO dto) {
        return ResponseEntity.ok(cuadrillaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cuadrillaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}