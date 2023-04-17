package br.com.vollmed.model.consulta;

import java.time.LocalDateTime;

import br.com.vollmed.model.medico.Especialidade;

public record DadosDetalhamentoConsulta(LocalDateTime dataConsulta, String nomePaciente, String nomeMedico, Especialidade especialidade) {
	
	public DadosDetalhamentoConsulta(Consulta consulta) {
		this(consulta.getData(),consulta.getPaciente().getNome(), consulta.getMedico().getNome(), consulta.getMedico().getEspecialidade());
	}
}
