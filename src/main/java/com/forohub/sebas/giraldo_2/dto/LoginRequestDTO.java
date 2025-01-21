package com.forohub.sebas.giraldo_2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para manejar los datos de inicio de sesión de los usuarios.
 */
@Getter
@Setter
public class LoginRequestDTO {

    @NotBlank(message = "El correo electrónico no debe estar vacío")
    private String correoElectronico;

    @NotBlank(message = "La contraseña no debe estar vacía")
    private String contrasena;

    public @NotBlank(message = "El correo electrónico no debe estar vacío") String getCorreoElectronico() {
        return correoElectronico;
    }

    public @NotBlank(message = "La contraseña no debe estar vacía") String getContrasena() {
        return contrasena;
    }
}
