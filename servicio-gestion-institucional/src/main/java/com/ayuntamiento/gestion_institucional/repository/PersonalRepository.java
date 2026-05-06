package com.ayuntamiento.gestion_institucional.repository;

import com.ayuntamiento.gestion_institucional.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

<<<<<<< HEAD
// INTERFACE — Spring genera el CRUD completo automaticamente

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {

    // Todo el personal de un departamento
    List<Personal> findByDepartamentoId(Long departamentoId);

    // Solo los disponibles (MS2 usa esto para asignar tecnicos)
    List<Personal> findByDisponibleTrue();

    // Disponibles de un departamento especifico
    List<Personal> findByDepartamentoIdAndDisponibleTrue(Long departamentoId);
=======
@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
    List<Personal> findByDisponible(Boolean disponible);
    List<Personal> findByCuadrillaId(Long cuadrillaId);
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
}