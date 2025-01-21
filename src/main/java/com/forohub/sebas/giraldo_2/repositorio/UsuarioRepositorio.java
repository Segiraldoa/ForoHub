package com.forohub.sebas.giraldo_2.repositorio;

import com.forohub.sebas.giraldo_2.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para manejar operaciones relacionadas con la entidad Usuario.
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param correoElectronico Correo electrónico del usuario.
     * @return Un Optional con el usuario si se encuentra, o vacío en caso contrario.
     */
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);
}
