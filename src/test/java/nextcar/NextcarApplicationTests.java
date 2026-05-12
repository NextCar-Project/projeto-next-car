package nextcar;

import nextcar.model.Usuario;
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
				.uri("/usuario")
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
				.uri("/usuario")
				.bodyValue(new Usuario("", "", ""))
				.exchange()
				.expectStatus().isBadRequest();
	}


}