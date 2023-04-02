package br.com.hospital.model.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hospital.model.consulta.ConsultaRepository;
import br.com.hospital.model.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorPacienteTemOutraConsultaNoMesmoDia implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private ConsultaRepository consultaRepository;

	public void validar(DadosAgendamentoConsulta dados) {
		var primeiroHorario = dados.data().withHour(7);
		var ultimoHorario = dados.data().withHour(18);
		var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario,
				ultimoHorario);
		if (pacientePossuiOutraConsultaNoDia)
			throw new RuntimeException("O paciente já possui uma consulta agendada para esse dia");
	}
}
