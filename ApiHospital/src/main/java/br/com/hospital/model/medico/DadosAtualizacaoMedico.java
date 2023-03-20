package br.com.hospital.model.medico;

import br.com.hospital.model.endereco.DadosCadastroEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
		@NotNull 
		Long id,
		String nome,
		String telefone,
		DadosCadastroEndereco endereco) {
}
