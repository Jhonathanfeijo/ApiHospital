package vollmed;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import br.com.vollmed.model.consulta.Consulta;
import br.com.vollmed.model.endereco.DadosCadastroEndereco;
import br.com.vollmed.model.medico.DadosCadastroMedico;
import br.com.vollmed.model.medico.Especialidade;
import br.com.vollmed.model.medico.Medico;
import br.com.vollmed.model.medico.MedicoRepository;
import br.com.vollmed.model.paciente.DadosCadastroPaciente;
import br.com.vollmed.model.paciente.Paciente;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("Deveria devolver null quando o médico cadastrado não está disponivel na data")
	void escolherMedicoAleatorioLivreNaDataCenario1() {

		var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
		var medico = cadastrarMedico("Medico", "1234", "medico@voll", Especialidade.CARDIOLOGIA, "99999-4444");
		var paciente = cadastrarPaciente("Paciente", "06879199160", "paciente@voll", "9888-5555");
		var consulta = agendarConsulta(paciente, medico, proximaSegundaAs10);

		var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA,
				proximaSegundaAs10);
		assertThat(medicoLivre).isNull();
	}

	private Consulta agendarConsulta(Paciente paciente, Medico medico, LocalDateTime data) {
		var consulta = em.persist(new Consulta(null, medico, paciente, data));
		return consulta;
	}

	private Paciente cadastrarPaciente(String nome, String crm, String email, String telefone) {
		var paciente = new Paciente(dadosPaciente(nome, crm, email, telefone));
		em.persist(paciente);
		return paciente;
	}

	private Medico cadastrarMedico(String nome, String crm, String email, Especialidade especialidade,
			String telefone) {
		var medico = new Medico(dadosMedico(nome, crm, email, especialidade, telefone));
		em.persist(medico);
		return medico;
	}

	private DadosCadastroMedico dadosMedico(String nome, String crm, String email, Especialidade especialidade,
			String telefone) {
		return new DadosCadastroMedico(nome, crm, email, especialidade, telefone, dadosEndereco());
	}

	private DadosCadastroPaciente dadosPaciente(String nome, String cpf, String email, String telefone) {
		return new DadosCadastroPaciente(nome, cpf, email, telefone, dadosEndereco());
	}

	private DadosCadastroEndereco dadosEndereco() {
		return new DadosCadastroEndereco("rua cristovao", "centro", "200", "79000-000", "Sao Paulo", "SP",
				"Perto do shopping");
	}

}
