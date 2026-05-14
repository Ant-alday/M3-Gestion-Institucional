package com.ayuntamiento.gestion_institucional.controller;

import com.auth.client_sdk.dto.UsuarioAuthDto;
import com.ayuntamiento.gestion_institucional.dto.PersonalDTO;
import com.ayuntamiento.gestion_institucional.service.PersonalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gestion/personal")
@RequiredArgsConstructor
public class PersonalController {

    private final PersonalService personalService;

    // ── Endpoints que consultan MS1 via libreria ──────────────────

    /**
     * Lista TODOS los usuarios de MS1.
     * Solo ADMIN — es información sensible de todos los usuarios del sistema.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuarios-ms1")
    public ResponseEntity<List<UsuarioAuthDto>> getTodosUsuariosDeMS1() {
        return ResponseEntity.ok(personalService.getTodosUsuariosDeMS1());
    }

    /**
     * Lista usuarios con rol EMPLEADO o ADMIN de MS1.
     * ADMIN y SISTEMA — MS2 puede necesitar consultar esto para validar asignaciones.
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('SISTEMA')")
    @GetMapping("/empleados-ms1")
    public ResponseEntity<List<UsuarioAuthDto>> getEmpleadosDeMS1() {
        return ResponseEntity.ok(personalService.getEmpleadosDeMS1());
    }

    // ── Endpoints de disponibilidad ───────────────────────────────

    /**
     * Personal disponible — ADMIN y SISTEMA lo consultan
     * (MS2 necesita saber quién está libre para asignar).
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('SISTEMA')")
    @GetMapping("/disponibles")
    public ResponseEntity<List<PersonalDTO>> findDisponibles() {
        return ResponseEntity.ok(personalService.findByDisponibilidad(true));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SISTEMA')")
    @GetMapping("/no-disponibles")
    public ResponseEntity<List<PersonalDTO>> findNoDisponibles() {
        return ResponseEntity.ok(personalService.findByDisponibilidad(false));
    }

    // ── CRUD de Personal ─────────────────────────────────────────

    @PreAuthorize("hasRole('ADMIN') or hasRole('SISTEMA')")
    @GetMapping
    public ResponseEntity<List<PersonalDTO>> findAll() {
        return ResponseEntity.ok(personalService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SISTEMA')")
    @GetMapping("/{id}")
    public ResponseEntity<PersonalDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personalService.findById(id));
    }

    /**
     * Obtener el nombre de un empleado por su ID de personal.
     * MS5 y otros microservicios usan este endpoint (rol SISTEMA).
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('SISTEMA')")
    @GetMapping("/{id}/nombre")
    public ResponseEntity<String> obtenerNombre(@PathVariable Long id) {
        return ResponseEntity.ok(personalService.getNombreUsuarioDesdeMS1(id));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SISTEMA')")
    @GetMapping("/cuadrilla/{cuadrillaId}")
    public ResponseEntity<List<PersonalDTO>> findByCuadrilla(
            @PathVariable Long cuadrillaId) {
        return ResponseEntity.ok(personalService.findByCuadrilla(cuadrillaId));
    }

    /**
     * Permisos del personal — ADMIN, SISTEMA y EMPLEADO pueden consultarlo.
     * Un empleado puede necesitar saber sus propios permisos.
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('SISTEMA') or hasRole('EMPLEADO')")
    @GetMapping("/{id}/permisos")
    public ResponseEntity<List<String>> getPermisosByPersonal(@PathVariable Long id) {
        return ResponseEntity.ok(personalService.getPermisosByPersonal(id));
    }

    // ── Escritura — solo ADMIN puede crear y modificar personal ──

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PersonalDTO> save(@Valid @RequestBody PersonalDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personalService.save(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PersonalDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PersonalDTO dto) {
        return ResponseEntity.ok(personalService.update(id, dto));
    }

    /**
     * Cambiar disponibilidad — ADMIN y SISTEMA.
     * MS2 necesita marcar al personal como ocupado al asignarle una incidencia.
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('SISTEMA')")
    @PatchMapping("/{id}/disponibilidad")
    public ResponseEntity<PersonalDTO> cambiarDisponibilidad(
            @PathVariable Long id,
            @RequestParam Boolean disponible) {
        return ResponseEntity.ok(personalService.cambiarDisponibilidad(id, disponible));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}