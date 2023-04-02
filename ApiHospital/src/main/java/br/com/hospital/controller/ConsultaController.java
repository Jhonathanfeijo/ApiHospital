package br.com.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hospital.model.consulta.AgendaConsulta;
import br.com.hospital.model.consulta.DadosAgendamentoConsulta;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
	
	@Autowired
	private AgendaConsulta agendaConsulta;

	@PostMapping
	@Transactional
	public ResponseEntity agendar(DadosAgendamentoConsulta dados) {
		var dto = agendaConsulta.agendar(dados);
		return ResponseEntity.ok(dto);
	}
}
