package com.ayuntamiento.gestion_institucional.controller;

import com.auth.client_sdk.dto.UsuarioAuthDto;
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

    // ── Endpoints que consultan MS1 via libreria ──────────────────

    /**
     * Lista TODOS los usuarios registrados en MS1.
     * GET /api/gestion/personal/usuarios-ms1
     */
    @GetMapping("/usuarios-ms1")
    public ResponseEntity<List<UsuarioAuthDto>> getTodosUsuariosDeMS1() {
        return ResponseEntity.ok(personalService.getTodosUsuariosDeMS1());
    }

    /**
     * Lista usuarios de MS1 con rol EMPLEADO o ADMIN.
     * Usa este endpoint para saber que usuarios puedes agregar como personal.
     * GET /api/gestion/personal/empleados-ms1
     */
    @GetMapping("/empleados-ms1")
    public ResponseEntity<List<UsuarioAuthDto>> getEmpleadosDeMS1() {
        return ResponseEntity.ok(personalService.getEmpleadosDeMS1());
    }

    // ── Endpoints de disponibilidad ───────────────────────────────

    /**
     * Personal con disponible = TRUE (activos, listos para asignacion).
     * GET /api/gestion/personal/disponibles
     */
    @GetMapping("/disponibles")
    public ResponseEntity<List<PersonalDTO>> findDisponibles() {
        return ResponseEntity.ok(personalService.findByDisponibilidad(true));
    }

    /**
     * Personal con disponible = FALSE (ocupados, ya asignados).
     * GET /api/gestion/personal/no-disponibles
     */
    @GetMapping("/no-disponibles")
    public ResponseEntity<List<PersonalDTO>> findNoDisponibles() {
        return ResponseEntity.ok(personalService.findByDisponibilidad(false));
    }

    // ── CRUD de Personal ─────────────────────────────────────────

    @GetMapping
    public ResponseEntity<List<PersonalDTO>> findAll() {
        return ResponseEntity.ok(personalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personalService.findById(id));
    }
    
    @GetMapping("/{id}/nombre")
    public ResponseEntity<String> obetnernombre(@PathVariable Long id) {
    	
        return ResponseEntity.ok(personalService.getNombreUsuarioDesdeMS1(id));
    }

    @GetMapping("/cuadrilla/{cuadrillaId}")
    public ResponseEntity<List<PersonalDTO>> findByCuadrilla(
            @PathVariable Long cuadrillaId) {
        return ResponseEntity.ok(personalService.findByCuadrilla(cuadrillaId));
    }

    /**
     * Crea un registro de personal vinculando un usuario de MS1
     * con un puesto y cuadrilla de MS3.
     *
     * Body:
     * {
     *   "usuarioId":   2,
     *   "puestoId":    1,
     *   "cuadrillaId": 1,
     *   "disponible":  true
     * }
     */
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

    /**
     * Cambia solo la disponibilidad del personal.
     * PATCH /api/gestion/personal/1/disponibilidad?disponible=false
     */
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
    
    
    
    @GetMapping("/{id}/permisos")
    public ResponseEntity<List<String>> getPermisosByPersonal(@PathVariable Long id) {
        return ResponseEntity.ok(personalService.getPermisosByPersonal(id));
    }
    
    
    
}