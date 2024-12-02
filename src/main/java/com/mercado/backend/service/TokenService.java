package com.mercado.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("mercado-auth-api")
                .withSubject(userDetails.getUsername())
                .withExpiresAt(calcExpirationDate())
                .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("mercado-auth-api")
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant calcExpirationDate() {
        return LocalDateTime.now()
            .plusHours(2)
            .toInstant(OffsetDateTime.now().getOffset());
    }
}
