package com.forohub.sebas.giraldo_2.repositorio;

import com.forohub.sebas.giraldo_2.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para manejar operaciones relacionadas con la entidad Curso.
 */
@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Long> {}
