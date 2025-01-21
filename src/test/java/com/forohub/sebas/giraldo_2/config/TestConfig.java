package com.forohub.sebas.giraldo_2.config;

import com.forohub.sebas.giraldo_2.servicio.TopicoService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public TopicoService mockTopicoService() {
        return Mockito.mock(TopicoService.class);
    }
}
