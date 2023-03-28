package br.com.hospital.infra.secutiry;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.hospital.model.usuario.Usuario;

@Service
public class TokenService {
	
	public String gerarToken(Usuario usuario) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256("12345678");
		    return JWT.create()
		        .withIssuer("API HOSPITAL")
		        .withSubject(usuario.getLogin())
		        .withClaim("id", usuario.getId())
		        .withExpiresAt(dataExpericao())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
			throw new RuntimeException("Erro ao gerar o token", exception);
		}
	}

	private Instant dataExpericao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
