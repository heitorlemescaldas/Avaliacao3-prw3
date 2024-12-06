package br.edu.ifsp.prw3.api_2024_2.util.security;

import br.edu.ifsp.prw3.api_2024_2.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class PW3TokenService {
    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256("12345678");
            String token = JWT.create()
                    .withIssuer("DISCIPLINA PW3")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
