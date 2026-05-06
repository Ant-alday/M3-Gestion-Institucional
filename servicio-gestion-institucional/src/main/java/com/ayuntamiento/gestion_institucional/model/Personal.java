package com.ayuntamiento.gestion_institucional.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "personal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String puesto;

    // FK INTERNA — Personal y Departamento estan en la misma BD (db_gestion)
    // Por eso SI usamos @ManyToOne con @JoinColumn
    // Crea la columna departamento_id en la tabla personal
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    // TINYINT(1) en MySQL — true = disponible, false = ocupado
    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    @Builder.Default
=======
    @Column(nullable = false, length = 150)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_puesto", nullable = false)
    private Puesto puesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuadrilla", nullable = false)
    private Cuadrilla cuadrilla;

    @Column(nullable = false)
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
    private Boolean disponible = true;
}