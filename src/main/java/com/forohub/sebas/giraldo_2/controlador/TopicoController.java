package com.forohub.sebas.giraldo_2.controlador;

import com.forohub.sebas.giraldo_2.dto.TopicoRegistroDTO;
import com.forohub.sebas.giraldo_2.modelo.Topico;
import com.forohub.sebas.giraldo_2.repositorio.TopicoRepositorio;
import com.forohub.sebas.giraldo_2.servicio.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con los tópicos.
 */
@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepositorio topicoRepositorio;
    private final TopicoService topicoService;

    public TopicoController(TopicoRepositorio topicoRepositorio, TopicoService topicoService) {
        this.topicoRepositorio = topicoRepositorio;
        this.topicoService = topicoService;
    }

    /**
     * Lista todos los tópicos registrados.
     *
     * @return Lista de tópicos.
     */
    @GetMapping
    public ResponseEntity<List<Topico>> listarTopicos() {
        return ResponseEntity.ok(topicoRepositorio.findAll());
    }

    /**
     * Obtiene el detalle de un tópico por su ID.
     *
     * @param id ID del tópico.
     * @return Detalle del tópico o un estado 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerDetalleTopico(@PathVariable Long id) {
        return topicoRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Registra un nuevo tópico.
     *
     * @param dto DTO con los datos del tópico.
     * @return Tópico creado.
     */
    @PostMapping
    public ResponseEntity<?> registrarTopico(@RequestBody @Valid TopicoRegistroDTO dto) {
        var topicoGuardado = topicoService.registrarTopico(dto);
        return ResponseEntity.status(201).body(topicoGuardado);
    }

    /**
     * Actualiza un tópico existente.
     *
     * @param id  ID del tópico.
     * @param dto DTO con los datos actualizados.
     * @return Tópico actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid TopicoRegistroDTO dto) {
        var topicoActualizado = topicoService.actualizarTopico(id, dto);
        return ResponseEntity.ok(topicoActualizado);
    }

    /**
     * Elimina un tópico por su ID.
     *
     * @param id ID del tópico.
     * @return Estado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        var topicoOptional = topicoRepositorio.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        topicoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
