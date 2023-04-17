package br.com.vollmed.model.consulta.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.vollmed.model.consulta.DadosAgendamentoConsulta;


@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

	public void validar(DadosAgendamentoConsulta dados) {
		var dataConsulta = dados.data();
		var dataAgora = LocalDateTime.now();
		var diferencaMinutos = Duration.between(dataAgora, dataConsulta).toMinutes();
		if (diferencaMinutos < 30) {
			throw new RuntimeException("Consulta deve ser agendada no mínimo 30 minutos antes");
		}
	}
}
