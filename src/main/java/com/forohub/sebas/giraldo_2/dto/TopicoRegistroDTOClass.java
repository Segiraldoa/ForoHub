package com.forohub.sebas.giraldo_2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Clase para manejar datos relacionados con el registro de tópicos.
 * Incluye métodos para convertir entre clase y record.
 */
public class TopicoRegistroDTOClass {

    @NotBlank(message = "El título es obligatorio.")
    private String titulo;

    @NotBlank(message = "El mensaje es obligatorio.")
    private String mensaje;

    @NotNull(message = "El ID del curso es obligatorio.")
    private Long cursoId;

    @NotNull(message = "El ID del autor es obligatorio.")
    private Long autorId;

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    /**
     * Convierte la clase a un record de tipo TopicoRegistroDTO.
     *
     * @return Instancia de TopicoRegistroDTO.
     */
    public TopicoRegistroDTO toRecord() {
        return new TopicoRegistroDTO(titulo, mensaje, autorId, cursoId);
    }
}
