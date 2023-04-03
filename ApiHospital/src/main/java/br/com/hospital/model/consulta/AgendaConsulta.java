package br.com.hospital.model.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hospital.model.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import br.com.hospital.model.medico.Medico;
import br.com.hospital.model.medico.MedicoRepository;
import br.com.hospital.model.paciente.Paciente;
import br.com.hospital.model.paciente.PacienteRepository;

@Service
public class AgendaConsulta {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private List<ValidadorAgendamentoDeConsulta> validadores;

	public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {

		if (!pacienteRepository.existsById(dados.idPaciente()))
			throw new RuntimeException("Paciente não encontrado!");

		if (dados.idMedico() != null && medicoRepository.existsById(dados.idMedico()))
			throw new RuntimeException("Médico não existe!");

		validadores.forEach(v -> v.validar(dados));

		Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
		Medico medico = encontrarMedico(dados);
		Consulta consulta = new Consulta(null, medico, paciente, dados.data());
		consulta = consultaRepository.save(consulta);
		return new DadosDetalhamentoConsulta(consulta);
	}

	private Medico encontrarMedico(DadosAgendamentoConsulta dados) {
		if (dados.idMedico() != null)
			return medicoRepository.getReferenceById(dados.idMedico());

		if (dados.especialidade() == null)
			throw new RuntimeException("A especialidade é obrigatória");

		var medico = medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
		if (medico == null)
			throw new RuntimeException("Não há médico para esse horário");
		return medico;
	}

}
