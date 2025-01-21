package com.forohub.sebas.giraldo_2.servicio;

import com.forohub.sebas.giraldo_2.dto.LoginRequestDTO;
import com.forohub.sebas.giraldo_2.modelo.Usuario;
import com.forohub.sebas.giraldo_2.repositorio.UsuarioRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio para manejar la lógica de autenticación de usuarios.
 */
@Service
public class AutenticacionService {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;

    public AutenticacionService(UsuarioRepositorio usuarioRepositorio, PasswordEncoder passwordEncoder) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Autentica a un usuario verificando su correo electrónico y contraseña.
     *
     * @param loginRequest Datos de inicio de sesión (correo y contraseña).
     * @return Usuario autenticado.
     */
    public Usuario autenticar(LoginRequestDTO loginRequest) {
        var usuario = usuarioRepositorio.findByCorreoElectronico(loginRequest.getCorreoElectronico())
                .orElseThrow(() -> new IllegalArgumentException("Correo electrónico no encontrado"));

        if (!passwordEncoder.matches(loginRequest.getContrasena(), usuario.getContrasena())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        return usuario;
    }
}
