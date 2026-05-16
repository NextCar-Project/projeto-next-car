package nextcar;

import nextcar.dto.UsuarioRequestDTO;
import nextcar.dto.VendaRequestDTO;
import nextcar.model.Veiculo;
import nextcar.repository.UsuarioRepository;
import nextcar.repository.VeiculoRepository;
import nextcar.repository.VendaRepository;
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

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private VendaRepository vendaRepository;

	@BeforeEach
	void setup() {

		webTestClient = WebTestClient
				.bindToServer()
				.baseUrl("http://localhost:" + port)
				.build();

		vendaRepository.deleteAll();
		veiculoRepository.deleteAll();
		usuarioRepository.deleteAll();
	}

	@Test
	void testCreateNextcarUsuarioSuccess() {

		UsuarioRequestDTO usuario = new UsuarioRequestDTO(
				"Emerson",
				"emerson@test.com",
				"123"
		);

		webTestClient
				.post()
				.uri("/usuarios")
				.bodyValue(usuario)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$.nome").isEqualTo(usuario.getNome())
				.jsonPath("$.login").isEqualTo(usuario.getLogin());
	}

	@Test
	void testCreateNextcarUsuarioFail() {

		UsuarioRequestDTO usuario = new UsuarioRequestDTO(
				"",
				"",
				""
		);

		webTestClient
				.post()
				.uri("/usuarios")
				.bodyValue(usuario)
				.exchange()
				.expectStatus()
				.isBadRequest();
	}

	@Test
	void testCreateNextcarVeiculoSuccess() {

		Veiculo veiculo = new Veiculo(
				"Toyota",
				"Corolla",
				2020,
				80000.0,
				"Sedan",
				"disponivel",
				"normal"
		);

		webTestClient
				.post()
				.uri("/veiculos")
				.bodyValue(veiculo)
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBody()
				.jsonPath("$.marca").isEqualTo(veiculo.getMarca())
				.jsonPath("$.modelo").isEqualTo(veiculo.getModelo())
				.jsonPath("$.ano").isEqualTo(veiculo.getAno())
				.jsonPath("$.preco").isEqualTo(veiculo.getPreco())
				.jsonPath("$.tipo").isEqualTo(veiculo.getTipo())
				.jsonPath("$.status").isEqualTo(veiculo.getStatus())
				.jsonPath("$.tipoPreco").isEqualTo(veiculo.getTipoPreco());
	}

	@Test
	void testCreateNextcarVeiculoFail() {

		Veiculo veiculo = new Veiculo(
				"",
				"",
				0,
				0.0,
				"",
				"",
				""
		);

		webTestClient
				.post()
				.uri("/veiculos")
				.bodyValue(veiculo)
				.exchange()
				.expectStatus()
				.isBadRequest();
	}

	@Test
	void testCreateNextcarVendaSuccess() {

		Veiculo veiculo = veiculoRepository.save(
				new Veiculo(
						"Honda",
						"Civic",
						2022,
						120000.0,
						"Sedan",
						"disponivel",
						"normal"
				)
		);

		var usuario = usuarioRepository.save(
				new nextcar.model.Usuario(
						"Emerson",
						"emerson@test.com",
						"123"
				)
		);

		VendaRequestDTO venda = new VendaRequestDTO();

		venda.setData(LocalDate.now());
		venda.setValorFinal(120000.0);
		venda.setVeiculoId(veiculo.getId());
		venda.setUsuarioId(usuario.getId());

		webTestClient
				.post()
				.uri("/vendas")
				.bodyValue(venda)
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBody()
				.jsonPath("$.valorFinal").isEqualTo(120000.0)
				.jsonPath("$.usuarioNome").isEqualTo("Emerson");
	}

	@Test
	void testCreateNextcarVendaFail() {

		VendaRequestDTO venda = new VendaRequestDTO();

		venda.setData(LocalDate.now());
		venda.setValorFinal(0.0);
		venda.setVeiculoId(999L);
		venda.setUsuarioId(999L);

		webTestClient
				.post()
				.uri("/vendas")
				.bodyValue(venda)
				.exchange()
				.expectStatus()
				.is4xxClientError();
	}
}