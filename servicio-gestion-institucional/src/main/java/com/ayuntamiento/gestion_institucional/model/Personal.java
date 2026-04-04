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

    @Column(nullable = false, length = 150)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_puesto", nullable = false)
    private Puesto puesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuadrilla", nullable = false)
    private Cuadrilla cuadrilla;

    @Column(nullable = false)
    private Boolean disponible = true;
}