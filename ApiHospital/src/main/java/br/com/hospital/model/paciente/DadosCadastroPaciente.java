package br.com.hospital.model.paciente;

import org.hibernate.validator.constraints.br.CPF;

import br.com.hospital.model.endereco.DadosCadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPaciente(
		@NotBlank
		String nome,
		@NotBlank
		@CPF
		String cpf,
		@NotBlank
		String email,
		@NotBlank
		String telefone,
		@NotNull
		@Valid
		DadosCadastroEndereco endereco) {

}
