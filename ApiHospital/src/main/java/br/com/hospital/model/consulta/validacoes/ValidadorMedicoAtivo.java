package br.com.hospital.model.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hospital.model.consulta.DadosAgendamentoConsulta;
import br.com.hospital.model.medico.MedicoRepository;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private MedicoRepository medicoRepository;

	public void validar(DadosAgendamentoConsulta dados) {

		if (dados.idMedico() == null) {
			return;
		}

		var medicoAtivo = medicoRepository.findAtivoById(dados.idMedico());
		if (!medicoAtivo) {
			throw new RuntimeException("Médico está inativo");
		}

	}
}
