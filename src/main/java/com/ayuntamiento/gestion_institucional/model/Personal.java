package com.ayuntamiento.gestion_institucional.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "personal",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_personal_usuario",
        columnNames = "usuario_id"
    )
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false, unique = true)
    private Long usuarioId;

    @Column(length = 250)
    private String nombre;

    @Column(length = 250)
    private String email;

    @Column(length = 50)
    private String rol;

    @Column(length = 15)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_puesto", nullable = false)
    private Puesto puesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuadrilla", nullable = false)
    private Cuadrilla cuadrilla;

    @Column(nullable = false)
    private Boolean disponible = true;
}