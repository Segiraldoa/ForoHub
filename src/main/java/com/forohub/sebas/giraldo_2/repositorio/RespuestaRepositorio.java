package com.forohub.sebas.giraldo_2.repositorio;

import com.forohub.sebas.giraldo_2.modelo.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para manejar operaciones relacionadas con la entidad Respuesta.
 */
@Repository
public interface RespuestaRepositorio extends JpaRepository<Respuesta, Long> {

    /**
     * Elimina todas las respuestas asociadas a un tópico específico.
     *
     * @param topicoId ID del tópico cuyas respuestas se eliminarán.
     */
    @Modifying
    @Query("DELETE FROM Respuesta r WHERE r.topico.id = :topicoId")
    void deleteAllByTopicoId(@Param("topicoId") Long topicoId);
}
