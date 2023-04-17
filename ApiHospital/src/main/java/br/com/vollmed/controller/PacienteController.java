package br.com.vollmed.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vollmed.model.paciente.DadosAtualizacaoPaciente;
import br.com.vollmed.model.paciente.DadosCadastroPaciente;
import br.com.vollmed.model.paciente.DadosPaciente;
import br.com.vollmed.model.paciente.Paciente;
import br.com.vollmed.model.paciente.PacienteRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository pacienteRepository;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
		Paciente paciente = pacienteRepository.save(new Paciente(dados));
		URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(paciente.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosPaciente(paciente));
	}

	@GetMapping
	public ResponseEntity<Page<DadosPaciente>> listar(@PageableDefault(size = 10, page = 0) Pageable paginacao) {
		Page<DadosPaciente> listagem = pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosPaciente::new);
		return ResponseEntity.ok(listagem);
	}

	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
		Paciente paciente = pacienteRepository.getReferenceById(dados.id());
		paciente.atualizar(dados);
		return ResponseEntity.ok().body(new DadosPaciente(paciente));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletar(@PathVariable("id") Long id) {
		Paciente paciente = pacienteRepository.getReferenceById(id);
		paciente.excluir();
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity detalharPaciente(@PathVariable("id") Long id) {
		Paciente paciente = pacienteRepository.getReferenceById(id);
		return ResponseEntity.ok().body(new DadosPaciente(paciente));
	}
}
