package br.com.hospital.model.consulta;

import java.time.LocalDateTime;

import br.com.hospital.model.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamentoConsulta(@NotNull Long idPaciente, Long idMedico, @NotNull @Future LocalDateTime data, @NotNull Especialidade especialidade) {

}
