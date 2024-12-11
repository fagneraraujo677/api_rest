package br.com.med.voll.clinica.api_rest.infra.security;

import br.com.med.voll.clinica.api_rest.domain.usuarios.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final static String WITHISSUER = "API Clinica.voll.med.com.br";

    @Value("${api.security.token.secret}")
    private String secret;
    public String gerarToken(UsuarioEntity usuario) {
        System.out.println(secret);
        try {
         var algoritmo = Algorithm.HMAC256(secret);
         return JWT.create()
                 .withIssuer(WITHISSUER)
                 .withSubject(usuario.getLogin())
                 .withClaim("id", usuario.getId())
                 .withExpiresAt(dataDeExpiracao())
                 .sign(algoritmo);

        } catch (JWTCreationException creationException) {
            throw new RuntimeException("Ops... Erro na tentativa de geração do token JWT! " + creationException);
        }
    }

    private Instant dataDeExpiracao() {

        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {

        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer(WITHISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException verificationException) {
            throw new RuntimeException("Ops... TokenJWT inválido ou expirado!");
        }
    }
}
