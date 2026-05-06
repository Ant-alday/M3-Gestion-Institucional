package com.ayuntamiento.gestion_institucional.repository;

import com.ayuntamiento.gestion_institucional.model.Cuadrilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CuadrillaRepository extends JpaRepository<Cuadrilla, Long> {
    List<Cuadrilla> findByDepartamentoId(Long departamentoId);
}