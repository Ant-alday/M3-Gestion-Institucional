package com.ayuntamiento.gestion_institucional.controller;

import com.ayuntamiento.gestion_institucional.dto.PersonalDTO;
import com.ayuntamiento.gestion_institucional.service.PersonalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gestion/personal")
@RequiredArgsConstructor
public class PersonalController {

    private final PersonalService personalService;

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

    @PatchMapping("/{id}/disponibilidad")
    public ResponseEntity<PersonalDTO> cambiarDisponibilidad(
            @PathVariable Long id,
            @RequestParam Boolean disponible) {
        return ResponseEntity.ok(personalService.cambiarDisponibilidad(id, disponible));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}