package com.forohub.sebas.giraldo_2.excepciones;

/**
 * Excepción personalizada para manejar casos en los que un recurso no se encuentra.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor con un mensaje específico.
     *
     * @param message Mensaje que describe la causa del error.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
