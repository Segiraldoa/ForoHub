package com.forohub.sebas.giraldo_2.config;

import com.forohub.sebas.giraldo_2.filtro.JwtAuthenticationFilter;
import com.forohub.sebas.giraldo_2.servicio.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de seguridad de la aplicación utilizando Spring Security.
 * Incluye autenticación basada en JWT y protección de rutas.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final TokenService tokenService;

    public SecurityConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * Configura la cadena de seguridad.
     * - Deshabilita CSRF.
     * - Protege las rutas de tópicos con autenticación JWT.
     * - Permite la autenticación básica deshabilitada.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll() // Permitir acceso al endpoint de inicio de sesión
                        .requestMatchers(HttpMethod.PUT, "/topicos/**").authenticated() // Autenticación requerida para PUT
                        .requestMatchers("/topicos/**").authenticated() // Autenticación requerida para tópicos
                )
                .addFilterBefore(new JwtAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter.class) // Agregar filtro JWT
                .httpBasic(httpBasic -> httpBasic.disable()); // Deshabilitar autenticación básica
        return http.build();
    }

    /**
     * Proporciona el administrador de autenticación.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Codificador de contraseñas utilizando BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
