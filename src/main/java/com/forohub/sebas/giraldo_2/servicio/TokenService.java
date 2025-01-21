package com.forohub.sebas.giraldo_2.servicio;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Servicio para manejar la generación y validación de tokens JWT.
 */
@Service
public class TokenService {

    private final SecretKey secretKey;
    private final long expiration;

    public TokenService(@Value("${jwt.secret}") String secret,
                        @Value("${jwt.expiration}") long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
    }

    /**
     * Genera un token JWT.
     *
     * @param subject Identificador del usuario.
     * @return Token generado.
     */
    public String generarToken(String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Valida un token JWT.
     *
     * @param token Token a validar.
     * @return True si el token es válido, False en caso contrario.
     */
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extrae el subject de un token JWT.
     *
     * @param token Token del que se extraerá el subject.
     * @return Subject extraído.
     */
    public String obtenerSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
