package com.forohub.sebas.giraldo_2.filtro;

import com.forohub.sebas.giraldo_2.servicio.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filtro para manejar la autenticación basada en JWT en cada solicitud.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    /**
     * Constructor para inyectar el servicio de token.
     *
     * @param tokenService Servicio encargado de la gestión de tokens.
     */
    public JwtAuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * Filtra cada solicitud para validar el token JWT y establecer el contexto de seguridad.
     *
     * @param request     Solicitud HTTP.
     * @param response    Respuesta HTTP.
     * @param filterChain Cadena de filtros.
     * @throws ServletException Si ocurre un error en el procesamiento del filtro.
     * @throws IOException      Si ocurre un error de E/S.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Obtener el token del encabezado Authorization
        String token = getTokenFromHeader(request);

        if (token != null && tokenService.validarToken(token)) {
            // Obtener el usuario asociado al token
            String username = tokenService.obtenerSubject(token);

            System.out.println("Token recibido: " + token);
            System.out.println("Usuario extraído del token: " + username);

            // Crear objeto de autenticación
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Establecer el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }

    /**
     * Extrae el token del encabezado Authorization.
     *
     * @param request Solicitud HTTP.
     * @return Token JWT si existe, de lo contrario null.
     */
    private String getTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
