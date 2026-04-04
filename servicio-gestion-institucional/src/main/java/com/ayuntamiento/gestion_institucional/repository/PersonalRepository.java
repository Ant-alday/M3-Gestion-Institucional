package com.ayuntamiento.gestion_institucional.repository;

import com.ayuntamiento.gestion_institucional.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
    List<Personal> findByDisponible(Boolean disponible);
    List<Personal> findByCuadrillaId(Long cuadrillaId);
}