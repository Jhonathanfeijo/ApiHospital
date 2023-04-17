package br.com.vollmed.model.medico;

import br.com.vollmed.model.endereco.DadosCadastroEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
		@NotNull 
		Long id,
		String nome,
		String telefone,
		DadosCadastroEndereco endereco) {
}
