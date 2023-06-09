package br.com.vollmed.model.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vollmed.model.consulta.DadosAgendamentoConsulta;
import br.com.vollmed.model.paciente.PacienteRepository;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private PacienteRepository pacienteRepository;

	public void validar(DadosAgendamentoConsulta dados) {

		var pacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
		if (!pacienteAtivo)
			throw new RuntimeException("Consulta não pode ser agendada com paciente inativo");
	}
}
