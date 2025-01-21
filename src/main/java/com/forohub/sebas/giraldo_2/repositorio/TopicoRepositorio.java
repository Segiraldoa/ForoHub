package com.forohub.sebas.giraldo_2.repositorio;

import com.forohub.sebas.giraldo_2.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para manejar operaciones relacionadas con la entidad Topico.
 */
@Repository
public interface TopicoRepositorio extends JpaRepository<Topico, Long> {

    /**
     * Verifica si existe un tópico con el mismo título y mensaje.
     *
     * @param titulo  Título del tópico.
     * @param mensaje Mensaje del tópico.
     * @return True si existe un tópico duplicado, False en caso contrario.
     */
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}
