package com.ayuntamiento.gestion_institucional.repository;

import com.ayuntamiento.gestion_institucional.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD
import java.util.Optional;

// INTERFACE — Spring genera el CRUD completo automaticamente
// No necesitas crear ninguna clase que implemente esto

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    Optional<Departamento> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
=======

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
}