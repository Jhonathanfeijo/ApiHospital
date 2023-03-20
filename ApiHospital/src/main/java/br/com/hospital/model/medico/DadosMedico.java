package br.com.hospital.model.medico;

public record DadosMedico(Long id, String nome, String crm, Especialidade especialidade, String email, String telefone) {
	public DadosMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEspecialidade(), medico.getEmail(), medico.getTelefone());
	}
}
