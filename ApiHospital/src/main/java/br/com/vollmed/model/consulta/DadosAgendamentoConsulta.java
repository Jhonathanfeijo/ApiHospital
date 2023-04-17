package br.com.vollmed.model.consulta;

import java.time.LocalDateTime;

import br.com.vollmed.model.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamentoConsulta(@NotNull Long idPaciente, Long idMedico, @NotNull @Future LocalDateTime data, @NotNull Especialidade especialidade) {

}
