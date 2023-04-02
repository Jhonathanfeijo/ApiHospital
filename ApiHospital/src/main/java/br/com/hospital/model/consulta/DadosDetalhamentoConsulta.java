package br.com.hospital.model.consulta;

import java.time.LocalDateTime;

import br.com.hospital.model.medico.Especialidade;

public record DadosDetalhamentoConsulta(LocalDateTime dataConsulta, String nomePaciente, String nomeMedico, Especialidade especialidade) {
	
	public DadosDetalhamentoConsulta(Consulta consulta) {
		this(consulta.getData(),consulta.getPaciente().getNome(), consulta.getMedico().getNome(), consulta.getMedico().getEspecialidade());
	}
}
