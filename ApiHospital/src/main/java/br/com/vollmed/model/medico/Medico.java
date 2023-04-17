package br.com.vollmed.model.medico;

import br.com.vollmed.model.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medico")
@Entity(name = "Medico")

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String crm;
	private String email;
	private String telefone;
	private boolean ativo;

	@Embedded
	private Endereco endereco;

	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;

	public Medico(DadosCadastroMedico dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.crm = dados.crm();
		this.email = dados.email();
		this.endereco = new Endereco(dados.endereco());
		this.especialidade = dados.especialidade();
		this.telefone = dados.telefone();
	}

	public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
		if (dados.nome() != null)
			this.nome = dados.nome();
		if (dados.telefone() != null)
			this.telefone = dados.telefone();
		if (dados.endereco() != dados.endereco())
			this.endereco.atualizarEndereco(dados.endereco());
	}
	
	public void excluir() {
		this.ativo = false;
	}
}
