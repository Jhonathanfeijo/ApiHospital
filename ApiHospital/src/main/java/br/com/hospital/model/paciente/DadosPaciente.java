package br.com.hospital.model.paciente;

import br.com.hospital.model.endereco.Endereco;

public record DadosPaciente(Long id, String nome, String cpf, String email, String telefone, Endereco endereco) {

	public DadosPaciente(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getEmail(), paciente.getTelefone(),
				paciente.getEndereco());
	}
}
