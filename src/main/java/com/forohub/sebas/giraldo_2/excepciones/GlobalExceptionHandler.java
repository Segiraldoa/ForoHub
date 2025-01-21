package com.forohub.sebas.giraldo_2.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador global de excepciones para manejar errores en toda la aplicación.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Manejo de excepciones de validación (e.g., campos obligatorios no proporcionados).
     *
     * @param ex Excepción lanzada durante la validación.
     * @return Respuesta con los errores específicos de los campos.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Manejo de excepciones tipo IllegalArgumentException.
     *
     * @param ex Excepción lanzada.
     * @return Respuesta con el mensaje del error.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * Manejo de excepciones personalizadas para recursos no encontrados.
     *
     * @param ex Excepción lanzada cuando un recurso no existe.
     * @return Respuesta con el mensaje de error.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Manejo genérico de excepciones no controladas.
     *
     * @param ex Excepción no controlada.
     * @return Respuesta con el mensaje genérico del error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + ex.getMessage());
    }
}
