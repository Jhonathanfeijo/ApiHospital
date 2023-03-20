package br.com.hospital.model.paciente;

import br.com.hospital.model.endereco.Endereco;

public record DadosPaciente(Long id, String nome, String cpf, String email, String telefone, Endereco endereco) {
	public DadosPaciente(Paciente paciente) {
		this.nome = paciente.getNome();
		this.cpf = paciente.getCpf();
		this.endereco = paciente.getEndereco();
		this.email = paciente.getEmail();
		this.id = paciente.getId();
		this.telefone = paciente.getTelefone();
	}
}
