package br.com.hospital.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.hospital.model.usuario.Usuario;

@Service
public class TokenService {
	@Value("${api.security.secret}")
	private String secret;

	public String gerarToken(Usuario usuario) {
		try {

			Algorithm algoritimo = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("Hospital Voll Med").withSubject(usuario.getLogin())
					.withExpiresAt(dataExpiracao()).withClaim("id", usuario.getId()).sign(algoritimo);
		} catch (JWTCreationException exception) {

			throw new RuntimeException("Não é possível gerar o token", exception);
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

	public String getSubject(String token) {
		try {

			Algorithm algoritimo = Algorithm.HMAC256(secret);
			return JWT.require(algoritimo).withIssuer("Hospital Voll Med").build().verify(token).getSubject();

		} catch (JWTVerificationException exception) {
			throw new RuntimeException("Token inválido ou nulo", exception);
		}
	}
}
