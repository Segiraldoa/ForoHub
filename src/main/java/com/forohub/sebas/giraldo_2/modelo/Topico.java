package com.forohub.sebas.giraldo_2.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa un tópico en el sistema.
 */
@Getter
@Setter
@Entity
@Table(name = "topico", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"titulo", "mensaje"})
})
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título no puede estar vacío.")
    private String titulo;

    @NotBlank(message = "El mensaje no puede estar vacío.")
    private String mensaje;

    @NotNull(message = "El autor es obligatorio.")
    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false)
    @JsonIgnore
    private Usuario autor;

    @NotNull(message = "El curso es obligatorio.")
    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    @JsonIgnore
    private Curso curso;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @NotBlank(message = "El estado es obligatorio.")
    private String status;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas;

    public void setTitulo(@NotBlank(message = "El título no puede estar vacío.") String titulo) {
        this.titulo = titulo;
    }

    public void setMensaje(@NotBlank(message = "El mensaje no puede estar vacío.") String mensaje) {
        this.mensaje = mensaje;
    }

    public void setAutor(@NotNull(message = "El autor es obligatorio.") Usuario autor) {
        this.autor = autor;
    }

    public void setCurso(@NotNull(message = "El curso es obligatorio.") Curso curso) {
        this.curso = curso;
    }

    public void setStatus(@NotBlank(message = "El estado es obligatorio.") String status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
