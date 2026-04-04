package com.ayuntamiento.gestion_institucional.repository;

import com.ayuntamiento.gestion_institucional.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
}