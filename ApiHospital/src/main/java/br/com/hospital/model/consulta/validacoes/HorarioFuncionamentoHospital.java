package br.com.hospital.model.consulta.validacoes;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import br.com.hospital.model.consulta.DadosAgendamentoConsulta;

@Component
public class HorarioFuncionamentoHospital implements ValidadorAgendamentoDeConsulta {

	public void validar(DadosAgendamentoConsulta dados) {

		var dataConsulta = dados.data();
		var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		var antesDaAbertura = dataConsulta.getHour() < 7;
		var depoisDoFechamento = dataConsulta.getHour() > 18;
		if (domingo || antesDaAbertura || depoisDoFechamento)
			throw new RuntimeException("Consulta fora do horário de funcionamento da clinica");
	}
}
