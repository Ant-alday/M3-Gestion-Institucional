package com.ayuntamiento.gestion_institucional.repository;

import com.ayuntamiento.gestion_institucional.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {

    List<Personal> findByDisponible(Boolean disponible);

    List<Personal> findByCuadrillaId(Long cuadrillaId);

    /** Verifica si un usuario de MS1 ya esta registrado como personal. */
    boolean existsByUsuarioId(Long usuarioId);

    /** Busca el registro de personal por su ID de usuario en MS1. */
    Optional<Personal> findByUsuarioId(Long usuarioId);
}

//hacer endpoint de mostrar todos  los usuarios que estan activos y que estan inactivos,
