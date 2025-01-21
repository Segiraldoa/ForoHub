package com.forohub.sebas.giraldo_2.controlador;

import com.forohub.sebas.giraldo_2.dto.LoginRequestDTO;
import com.forohub.sebas.giraldo_2.dto.TokenResponseDTO;
import com.forohub.sebas.giraldo_2.servicio.AutenticacionService;
import com.forohub.sebas.giraldo_2.servicio.TokenService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para manejar la autenticación de usuarios.
 */
@Getter
@Setter
@RestController
@RequestMapping("/login")
public class AutenticacionController {

    private final AutenticacionService autenticacionService;
    private final TokenService tokenService;

    public AutenticacionController(AutenticacionService autenticacionService, TokenService tokenService) {
        this.autenticacionService = autenticacionService;
        this.tokenService = tokenService;
    }

    /**
     * Inicia sesión con un usuario dado y genera un token JWT.
     *
     * @param loginRequest DTO que contiene el correo electrónico y la contraseña.
     * @return Token JWT si las credenciales son válidas.
     */
    @PostMapping
    public ResponseEntity<?> iniciarSesion(@RequestBody @Valid LoginRequestDTO loginRequest) {
        var usuario = autenticacionService.autenticar(loginRequest);
        var token = tokenService.generarToken(usuario.getCorreoElectronico());
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
}
