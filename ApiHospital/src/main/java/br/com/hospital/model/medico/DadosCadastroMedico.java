package br.com.hospital.model.medico;

import br.com.hospital.model.endereco.DadosCadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
		@NotBlank
		String nome,
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String crm,
		@NotBlank
		String email,
		@NotNull
		Especialidade especialidade,
		@NotBlank
		String telefone,
		@NotNull
		@Valid
		DadosCadastroEndereco endereco) {

}
