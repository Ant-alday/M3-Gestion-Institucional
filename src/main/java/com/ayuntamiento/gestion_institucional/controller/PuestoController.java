package com.ayuntamiento.gestion_institucional.controller;

import com.ayuntamiento.gestion_institucional.dto.PuestoDTO;
import com.ayuntamiento.gestion_institucional.service.PuestoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gestion/puestos")
@RequiredArgsConstructor
public class PuestoController {

    private final PuestoService puestoService;

    @GetMapping
    public ResponseEntity<List<PuestoDTO>> findAll() {
        return ResponseEntity.ok(puestoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuestoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(puestoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PuestoDTO> save(@Valid @RequestBody PuestoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(puestoService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PuestoDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PuestoDTO dto) {
        return ResponseEntity.ok(puestoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        puestoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @GetMapping("/{id}/permisos")
    public ResponseEntity<List<String>> getPermisos(@PathVariable Long id) {
        return ResponseEntity.ok(puestoService.getPermisos(id));
    }
    
    
}