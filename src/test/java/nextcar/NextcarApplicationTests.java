package nextcar;

import nextcar.model.Usuario;
import nextcar.model.Veiculo;
import nextcar.model.Venda;
import nextcar.repository.UsuarioRepository;
import nextcar.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NextcarApplicationTests {

	@LocalServerPort
	private int port;

	private WebTestClient webTestClient;

	@BeforeEach
	void setup() {
		webTestClient = WebTestClient
				.bindToServer()
				.baseUrl("http://localhost:" + port)
				.build();
	}

	@Test
	void testCreateNextcarUsuarioSucess() {
		var usuario = new Usuario("test2", "testnext1@gmail.com", "12345Next");

		webTestClient
				.post()
				.uri("/usuarios")
				.bodyValue(usuario)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.jsonPath("$.nome").isEqualTo(usuario.getNome())
				.jsonPath("$.login").isEqualTo(usuario.getLogin())
				.jsonPath("$.senha").exists()
				.jsonPath("$.senha").isNotEmpty();
	}

	@Test
	void testCreateNextcarUsuarioFail() {
		webTestClient
				.post()
				.uri("/usuarios")
				.bodyValue(new Usuario("", "", ""))
				.exchange()
				.expectStatus().isBadRequest();
	}


	@Test
	void testCreateNextcarVeiculoSucess() {
		var veiculo = new Veiculo("test2", "onix", 2025, 150.000, "Sedan", "Vendido");

		webTestClient
				.post()
				.uri("/veiculos")
				.bodyValue(veiculo)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.jsonPath("$.marca").isEqualTo(veiculo.getMarca())
				.jsonPath("$.modelo").isEqualTo(veiculo.getModelo())
				.jsonPath("$.ano").isEqualTo(veiculo.getAno())
				.jsonPath("$.preco").isEqualTo(veiculo.getPreco())
				.jsonPath("$.tipo").isEqualTo(veiculo.getTipo())
				.jsonPath("$.status").isEqualTo(veiculo.getStatus());
	}

	@Test
	void testCreateNextcarVeiculoFail() {
		webTestClient
				.post()
				.uri("/veiculos")
				.bodyValue(new Veiculo("", "", 0, 0.0, "Sedan", "Vendido"))
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	void testCreateNextcarVendaSucess() {
		var veiculo = veiculoRepository.save(new Veiculo("Ford", "Fusion", 2019, 140000.0, "Sedan", "disponivel"));
		var usuario = usuarioRepository.save(new Usuario("test5", "testnext5@gmail.com", "12345Next"));

		Venda venda = new Venda(LocalDate.now(), 150000.0, veiculo, usuario);

		webTestClient
				.post()
				.uri("/vendas")
				.bodyValue(venda)
				.exchange()
				.expectStatus().isOk();
	}

	@Test
	void testCreateNextcarVendaFail() {
		Veiculo veiculo = new Veiculo(
				"",
				"",
				0,
				0.0,
				"",
				""
		);

		Usuario usuario = new Usuario(
				"",
				"",
				""
		);

		webTestClient
				.post()
				.uri("/vendas")
				.bodyValue(
						new Venda(
								LocalDate.of(2026, 5, 11),
								0.0,
								veiculo,
								usuario
						)
				)
				.exchange()
				.expectStatus()
				.isBadRequest();
	}
}