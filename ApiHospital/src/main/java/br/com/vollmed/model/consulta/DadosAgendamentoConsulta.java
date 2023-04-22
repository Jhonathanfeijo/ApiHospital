package br.com.vollmed.model.consulta;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.vollmed.model.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamentoConsulta(
		@NotNull 
		Long idPaciente, 
		Long idMedico,
		@JsonFormat(pattern = ("dd-MM-yyyy HH:mm"))
		@NotNull 
		@Future 
		LocalDateTime data,
		@NotNull Especialidade especialidade) {

}
