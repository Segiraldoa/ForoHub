package com.forohub.sebas.giraldo_2.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa un curso dentro del sistema.
 */
@Getter
@Setter
@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del curso no puede estar vacío.")
    private String nombre;

    @NotBlank(message = "La categoría del curso no puede estar vacía.")
    private String categoria;
}
