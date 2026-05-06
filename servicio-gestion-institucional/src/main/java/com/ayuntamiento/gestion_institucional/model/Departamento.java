package com.ayuntamiento.gestion_institucional.model;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
=======
import jakarta.persistence.*;
import lombok.*;
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320

@Entity
@Table(name = "departamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;
<<<<<<< HEAD

    // Un departamento tiene muchos empleados (1:N)
    // @JsonIgnore evita bucle infinito al convertir a JSON
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Personal> personal;
=======
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
}