package br.com.vollmed.model.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vollmed.model.consulta.ConsultaRepository;
import br.com.vollmed.model.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadoMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private ConsultaRepository consultaRepository;

	public void validar(DadosAgendamentoConsulta dados) {

		var medicoOcupado = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
		if (medicoOcupado)
			throw new RuntimeException("Médico já tem consulta marcada nesse horário");

	}
}
