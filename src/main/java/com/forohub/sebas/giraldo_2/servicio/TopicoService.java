package com.forohub.sebas.giraldo_2.servicio;


import com.forohub.sebas.giraldo_2.dto.TopicoRegistroDTO;
import com.forohub.sebas.giraldo_2.excepciones.ResourceNotFoundException;
import com.forohub.sebas.giraldo_2.modelo.Topico;
import com.forohub.sebas.giraldo_2.repositorio.TopicoRepositorio;
import com.forohub.sebas.giraldo_2.repositorio.UsuarioRepositorio;
import com.forohub.sebas.giraldo_2.repositorio.CursoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio para manejar la lógica de negocio relacionada con los tópicos.
 */
@Service
public class TopicoService {

    private final TopicoRepositorio topicoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final CursoRepositorio cursoRepositorio;

    public Optional<Topico> obtenerDetalleTopico(Long id) {
        return topicoRepositorio.findById(id)
                .or(() -> {
                    throw new ResourceNotFoundException("Tópico no encontrado con ID: " + id);
                });
    }
    public TopicoService(
            TopicoRepositorio topicoRepositorio,
            UsuarioRepositorio usuarioRepositorio,
            CursoRepositorio cursoRepositorio) {
        this.topicoRepositorio = topicoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.cursoRepositorio = cursoRepositorio;
    }

    /**
     * Actualiza los datos de un tópico existente.
     *
     * @param id  ID del tópico a actualizar.
     * @param dto Datos del tópico actualizados.
     * @return Tópico actualizado.
     */
    public Topico actualizarTopico(Long id, TopicoRegistroDTO dto) {
        var topico = topicoRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado con ID: " + id));

        var autor = usuarioRepositorio.findById(dto.autorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado con ID: " + dto.autorId()));

        var curso = cursoRepositorio.findById(dto.cursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + dto.cursoId()));

        topico.setTitulo(dto.titulo());
        topico.setMensaje(dto.mensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        return topicoRepositorio.save(topico);
    }

    /**
     * Registra un nuevo tópico.
     *
     * @param dto Datos del nuevo tópico.
     * @return Tópico registrado.
     */
    @Transactional
    public Topico registrarTopico(TopicoRegistroDTO dto) {
        if (topicoRepositorio.existsByTituloAndMensaje(dto.titulo(), dto.mensaje())) {
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje.");
        }

        var autor = usuarioRepositorio.findById(dto.autorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado con ID: " + dto.autorId()));

        var curso = cursoRepositorio.findById(dto.cursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + dto.cursoId()));

        var topico = new Topico();
        topico.setTitulo(dto.titulo());
        topico.setMensaje(dto.mensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);
        topico.setStatus("ABIERTO");

        return topicoRepositorio.save(topico);
    }
}
