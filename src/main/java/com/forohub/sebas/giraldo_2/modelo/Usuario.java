package com.forohub.sebas.giraldo_2.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa un usuario en el sistema.
 */
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío.")
    private String nombre;

    @NotBlank(message = "El correo electrónico no puede estar vacío.")
    @Column(unique = true)
    private String correoElectronico;

    @NotBlank(message = "La contraseña no puede estar vacía.")
    private String contrasena;

    public @NotBlank(message = "El correo electrónico no puede estar vacío.") String getCorreoElectronico() {
        return correoElectronico;
    }

    public @NotBlank(message = "La contraseña no puede estar vacía.") String getContrasena() {
        return contrasena;
    }
}
