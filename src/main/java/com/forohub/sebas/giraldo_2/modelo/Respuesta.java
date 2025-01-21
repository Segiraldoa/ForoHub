package com.forohub.sebas.giraldo_2.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Representa una respuesta asociada a un tópico en el sistema.
 */
@Getter
@Setter
@Entity
@Table(name = "respuesta")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El mensaje no puede estar vacío.")
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "id_topico", nullable = false)
    @JsonBackReference
    private Topico topico;

    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false)
    @JsonIgnore
    private Usuario autor;

    @Column(name = "solucion", nullable = false)
    private boolean solucion = false;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}
