package br.com.vollmed.model.paciente;

import br.com.vollmed.model.endereco.DadosCadastroEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(@NotNull Long id, String nome, String telefone, DadosCadastroEndereco endereco) {

}
