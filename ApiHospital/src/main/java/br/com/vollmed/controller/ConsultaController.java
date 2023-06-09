package br.com.vollmed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vollmed.model.consulta.AgendaConsulta;
import br.com.vollmed.model.consulta.DadosAgendamentoConsulta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/consulta")
public class ConsultaController {

	@Autowired
	private AgendaConsulta agendaConsulta;

	@PostMapping
	@Transactional
	public ResponseEntity agendar(@RequestBody DadosAgendamentoConsulta dados) {
		var dto = agendaConsulta.agendar(dados);
		return ResponseEntity.ok(dto);
	}
}
