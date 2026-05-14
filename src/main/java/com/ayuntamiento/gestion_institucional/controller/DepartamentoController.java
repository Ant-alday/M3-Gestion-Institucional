package com.ayuntamiento.gestion_institucional.controller;

import com.ayuntamiento.gestion_institucional.dto.DepartamentoDTO;
import com.ayuntamiento.gestion_institucional.service.DepartamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gestion/departamentos")
@RequiredArgsConstructor
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    // ── Consultas — ADMIN, SISTEMA y EMPLEADO pueden leer ────────
    // SISTEMA permite que otros microservicios consulten estos datos.

    @PreAuthorize("hasRole('ADMIN') or hasRole('SISTEMA') or hasRole('EMPLEADO')")
    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> findAll() {
        return ResponseEntity.ok(departamentoService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SISTEMA') or hasRole('EMPLEADO')")
    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(departamentoService.findById(id));
    }

    // ── Escritura — solo ADMIN puede crear, editar y eliminar ─────

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DepartamentoDTO> save(@Valid @RequestBody DepartamentoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departamentoService.save(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody DepartamentoDTO dto) {
        return ResponseEntity.ok(departamentoService.update(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}