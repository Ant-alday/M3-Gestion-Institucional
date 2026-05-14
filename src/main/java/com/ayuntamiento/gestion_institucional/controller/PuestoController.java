package com.ayuntamiento.gestion_institucional.controller;

import com.ayuntamiento.gestion_institucional.dto.PuestoDTO;
import com.ayuntamiento.gestion_institucional.service.PuestoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gestion/puestos")
@RequiredArgsConstructor
public class PuestoController {

    private final PuestoService puestoService;

    // ── Consultas — ADMIN, SISTEMA y EMPLEADO pueden leer ────────

    @PreAuthorize("hasRole('ADMIN') or hasRole('SISTEMA') or hasRole('EMPLEADO')")
    @GetMapping
    public ResponseEntity<List<PuestoDTO>> findAll() {
        return ResponseEntity.ok(puestoService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SISTEMA') or hasRole('EMPLEADO')")
    @GetMapping("/{id}")
    public ResponseEntity<PuestoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(puestoService.findById(id));
    }

    // Los permisos de un puesto los pueden consultar ADMIN, SISTEMA y EMPLEADO
    @PreAuthorize("hasRole('ADMIN') or hasRole('SISTEMA') or hasRole('EMPLEADO')")
    @GetMapping("/{id}/permisos")
    public ResponseEntity<List<String>> getPermisos(@PathVariable Long id) {
        return ResponseEntity.ok(puestoService.getPermisos(id));
    }

    // ── Escritura — solo ADMIN ────────────────────────────────────

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PuestoDTO> save(@Valid @RequestBody PuestoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(puestoService.save(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PuestoDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PuestoDTO dto) {
        return ResponseEntity.ok(puestoService.update(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        puestoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}