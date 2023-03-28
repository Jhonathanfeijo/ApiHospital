package br.com.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hospital.infra.secutiry.TokenService;
import br.com.hospital.model.usuario.DadosAutenticacao;
import br.com.hospital.model.usuario.Usuario;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity efetuarLogin(DadosAutenticacao dados) {
		var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		var authentication = manager.authenticate(token);
		return ResponseEntity.ok(tokenService.gerarToken((Usuario)authentication.getPrincipal()));
	}
}
