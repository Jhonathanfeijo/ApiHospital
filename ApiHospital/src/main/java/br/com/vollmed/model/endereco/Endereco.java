package br.com.vollmed.model.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter
public class Endereco {

	private String logradouro;
	private String bairro;
	private String numero;
	private String cep;
	private String cidade;
	private String uf;
	private String complemento;

	public Endereco(DadosCadastroEndereco endereco) {
		this.logradouro = endereco.logradouro();
		this.bairro = endereco.bairro();
		this.numero = endereco.numero();
		this.cep = endereco.cep();
		this.cidade = endereco.cidade();
		this.uf = endereco.uf();
		this.complemento = endereco.complemento();
	}

	public void atualizarEndereco(DadosCadastroEndereco endereco) {
		if (endereco.logradouro() != null)
			this.logradouro = endereco.logradouro();
		if (endereco.bairro() != null)
			this.bairro = endereco.bairro();
		if (endereco.numero() != null)
			this.numero = endereco.numero();
		if (endereco.cep() != null)
			this.cep = endereco.cep();
		if (endereco.cidade() != null)
			this.cidade = endereco.cidade();
		if (endereco.uf() != null)
			this.uf = endereco.uf();
		if (endereco.complemento() != null)
			this.complemento = endereco.complemento();
	}
}
