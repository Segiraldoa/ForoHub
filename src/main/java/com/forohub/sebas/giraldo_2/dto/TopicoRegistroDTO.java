package com.forohub.sebas.giraldo_2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para manejar los datos relacionados con el registro de tópicos.
 *
 * @param titulo   Título del tópico.
 * @param mensaje  Mensaje del tópico.
 * @param autorId  ID del autor asociado al tópico.
 * @param cursoId  ID del curso asociado al tópico.
 */
public record TopicoRegistroDTO(
        @NotBlank(message = "El título es obligatorio.") String titulo,
        @NotBlank(message = "El mensaje es obligatorio.") String mensaje,
        @NotNull(message = "El ID del autor es obligatorio.") Long autorId,
        @NotNull(message = "El ID del curso es obligatorio.") Long cursoId
) {}
