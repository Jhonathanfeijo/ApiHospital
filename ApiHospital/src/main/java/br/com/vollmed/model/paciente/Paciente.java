package br.com.vollmed.model.paciente;

import br.com.vollmed.model.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Paciente")
@Table(name = "paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private boolean ativo;

	@Embedded
	private Endereco endereco;

	public Paciente(DadosCadastroPaciente paciente) {
		this.ativo = true;
		this.nome = paciente.nome();
		this.email = paciente.email();
		this.cpf = paciente.cpf();
		this.telefone = paciente.telefone();
		this.endereco = new Endereco(paciente.endereco());
	}

	public void atualizar(DadosAtualizacaoPaciente dados) {
		if (dados.nome() != null)
			this.nome = dados.nome();
		if (dados.telefone() != null)
			this.telefone = dados.telefone();
		if (dados.endereco() != null)
			this.endereco.atualizarEndereco(dados.endereco());
	}

	public void excluir() {
		this.ativo = false;
	}
}
