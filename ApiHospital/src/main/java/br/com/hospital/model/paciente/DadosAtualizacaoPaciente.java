package br.com.hospital.model.paciente;

import br.com.hospital.model.endereco.DadosCadastroEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(@NotNull Long id, String nome, String telefone, DadosCadastroEndereco endereco) {

}
