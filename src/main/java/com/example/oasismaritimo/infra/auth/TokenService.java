package com.example.oasismaritimo.infra.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.oasismaritimo.domain.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * A classe TokenService é responsável por gerar e validar tokens JWT.
 * Ela usa a biblioteca Auth0 JWT para criar tokens com um segredo e um tempo de expiração.
 */
@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;


    /**
     * Gera um token JWT para o usuário fornecido.
     * O token é assinado com um segredo e tem um tempo de expiração.
     *
     * @param user o usuário para o qual o token será gerado
     * @return o token JWT gerado
     * @throws RuntimeException se ocorrer um erro durante a criação do token
     */
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("oasis-maritimo")
                    .withSubject(user.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token.", exception);
        }
    }

    /**
     * Valida o token JWT fornecido.
     *
     * @param token o token JWT a ser validado
     * @return o login do usuário se o token for válido, ou uma string vazia se o token for inválido
     */
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("oasis-maritimo")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    /**
     * Gera a data de expiração para o token JWT.
     * A data de expiração é 8 horas a partir do momento atual.
     *
     * @return a data de expiração do token
     */
    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }
}
