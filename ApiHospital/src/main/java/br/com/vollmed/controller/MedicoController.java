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

import br.com.vollmed.model.medico.DadosAtualizacaoMedico;
import br.com.vollmed.model.medico.DadosCadastroMedico;
import br.com.vollmed.model.medico.DadosMedico;
import br.com.vollmed.model.medico.Medico;
import br.com.vollmed.model.medico.MedicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;

	@PostMapping
	@Transactional
	public ResponseEntity CadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados,
			UriComponentsBuilder uriBuilder) {
		Medico medico = medicoRepository.save(new Medico(dados));
		URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosMedico(medico));
	}

	@GetMapping
	public ResponseEntity<Page<DadosMedico>> listar(
			@PageableDefault(size = 10, page = 0, sort = "nome") Pageable paginacao) {
		Page<DadosMedico> page = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosMedico::new);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity atualizarMedico(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		Medico medico = medicoRepository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosMedico(medico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity atualizarMedico(@PathVariable Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		medico.excluir();
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity detalharMedico(@PathVariable("id") Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		return ResponseEntity.ok().body(new DadosMedico(medico));
	}
}
