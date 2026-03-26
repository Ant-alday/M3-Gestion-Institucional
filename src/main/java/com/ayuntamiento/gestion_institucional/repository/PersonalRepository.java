package com.ayuntamiento.gestion_institucional.repository;

import com.ayuntamiento.gestion_institucional.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// INTERFACE — Spring genera el CRUD completo automaticamente

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {

    // Todo el personal de un departamento
    List<Personal> findByDepartamentoId(Long departamentoId);

    // Solo los disponibles (MS2 usa esto para asignar tecnicos)
    List<Personal> findByDisponibleTrue();

    // Disponibles de un departamento especifico
    List<Personal> findByDepartamentoIdAndDisponibleTrue(Long departamentoId);
}