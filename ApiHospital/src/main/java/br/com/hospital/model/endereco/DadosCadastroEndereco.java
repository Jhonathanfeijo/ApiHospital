package br.com.hospital.model.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroEndereco(
		@NotBlank
		String logradouro, 
		@NotBlank
		String bairro, 
		String numero, 
		@NotBlank
		@Pattern(regexp = "\\d{8}")
		String cep, 
		@NotBlank
		String cidade, 
		@NotBlank
		String uf,
		String complemento) {
}
