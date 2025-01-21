package com.forohub.sebas.giraldo_2;

import com.forohub.sebas.giraldo_2.config.TestConfig;
import com.forohub.sebas.giraldo_2.controlador.TopicoController;
import com.forohub.sebas.giraldo_2.dto.TopicoRegistroDTO;
import com.forohub.sebas.giraldo_2.modelo.Topico;
import com.forohub.sebas.giraldo_2.servicio.TopicoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TopicoController.class)
@Import(TestConfig.class)
class TopicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TopicoService topicoService;

    @Test
    void listarTopicos_debeRetornarLista() throws Exception {
        mockMvc.perform(get("/topicos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void obtenerDetalleTopico_debeRetornarDetalle() throws Exception {
        var topico = new Topico();
        topico.setId(1L);
        topico.setTitulo("Ejemplo");
        topico.setMensaje("Mensaje de ejemplo");

        when(topicoService.obtenerDetalleTopico(1L)).thenReturn(Optional.of(topico));

        mockMvc.perform(get("/topicos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Ejemplo"))
                .andExpect(jsonPath("$.mensaje").value("Mensaje de ejemplo"));

        verify(topicoService).obtenerDetalleTopico(1L);
    }

    @Test
    void registrarTopico_debeCrearNuevoTopico() throws Exception {
        var dto = new TopicoRegistroDTO("Título", "Mensaje", 1L, 1L);
        var topico = new Topico();
        topico.setId(1L);
        topico.setTitulo("Título");
        topico.setMensaje("Mensaje");

        when(topicoService.registrarTopico(dto)).thenReturn(topico);

        mockMvc.perform(post("/topicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "titulo": "Título",
                                    "mensaje": "Mensaje",
                                    "autorId": 1,
                                    "cursoId": 1
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Título"))
                .andExpect(jsonPath("$.mensaje").value("Mensaje"));

        verify(topicoService).registrarTopico(dto);
    }
    @Test
    void actualizarTopico_debeActualizarTópicoExistente() throws Exception {
        var dto = new TopicoRegistroDTO("Nuevo Título", "Nuevo Mensaje", 1L, 1L);
        var topicoActualizado = new Topico();
        topicoActualizado.setId(1L);
        topicoActualizado.setTitulo("Nuevo Título");
        topicoActualizado.setMensaje("Nuevo Mensaje");

        when(topicoService.actualizarTopico(eq(1L), eq(dto))).thenReturn(topicoActualizado);

        mockMvc.perform(put("/topicos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "titulo": "Nuevo Título",
                                "mensaje": "Nuevo Mensaje",
                                "autorId": 1,
                                "cursoId": 1
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Nuevo Título"))
                .andExpect(jsonPath("$.mensaje").value("Nuevo Mensaje"));

        verify(topicoService).actualizarTopico(1L, dto);
    }

    @Test
    void obtenerDetalleTopico_debeRetornarNotFoundCuandoNoExiste() throws Exception {
        when(topicoService.obtenerDetalleTopico(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/topicos/999"))
                .andExpect(status().isNotFound());

        verify(topicoService).obtenerDetalleTopico(999L);
    }


}
