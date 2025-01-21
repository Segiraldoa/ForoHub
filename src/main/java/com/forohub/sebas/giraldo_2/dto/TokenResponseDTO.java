package com.forohub.sebas.giraldo_2.dto;

/**
 * DTO para retornar el token JWT generado tras la autenticación.
 *
 * @param token Token JWT.
 */
public record TokenResponseDTO(String token) {}
