package nextcar.service;

import nextcar.dto.VendaRequestDTO;
import nextcar.dto.VendaResponseDTO;
import nextcar.model.Usuario;
import nextcar.model.Veiculo;
import nextcar.model.Venda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VendaFacadeTest {

    @Mock private VendaService vendaService;
    @Mock private VeiculoService veiculoService;
    @Mock private UsuarioService usuarioService;

    @InjectMocks
    private VendaFacade facade;

    private Veiculo veiculo;
    private Usuario usuario;
    private Venda venda;

    @BeforeEach
    void setUp() {
        veiculo = new Veiculo();
        veiculo.setId(1L);
        veiculo.setMarca("Toyota");
        veiculo.setModelo("Corolla");

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Emerson");
        usuario.setLogin("emerson@test.com");
        usuario.setSenha("123");

        venda = new Venda(LocalDate.now(), 80000.0, veiculo, usuario);
        venda.setId(1L);
    }

    @Test
    void testRegistrarVenda() {
        VendaRequestDTO dto = new VendaRequestDTO();
        dto.setData(LocalDate.now());
        dto.setValorFinal(80000.0);
        dto.setVeiculoId(1L);
        dto.setUsuarioId(1L);

        when(veiculoService.buscarPorId(1L)).thenReturn(veiculo);
        when(usuarioService.buscarPorId(1L)).thenReturn(usuario);
        when(vendaService.save(any(Venda.class))).thenReturn(venda);

        VendaResponseDTO response = facade.registrarVenda(dto);

        assertNotNull(response);
        assertEquals(80000.0, response.getValorFinal());
        assertEquals("Toyota Corolla", response.getVeiculoDescricao());
        assertEquals("Emerson", response.getUsuarioNome());
        verify(veiculoService).buscarPorId(1L);
        verify(usuarioService).buscarPorId(1L);
        verify(vendaService).save(any(Venda.class));
    }

    @Test
    void testListarVendas() {
        when(vendaService.find()).thenReturn(List.of(venda));
        List<VendaResponseDTO> lista = facade.listarVendas();
        assertEquals(1, lista.size());
        verify(vendaService).find();
    }

    @Test
    void testAtualizarVenda() {
        VendaRequestDTO dto = new VendaRequestDTO();
        dto.setData(LocalDate.now());
        dto.setValorFinal(95000.0);
        dto.setVeiculoId(1L);

        when(veiculoService.buscarPorId(1L)).thenReturn(veiculo);
        when(vendaService.update(any(Venda.class), eq(1L))).thenReturn(venda);

        VendaResponseDTO response = facade.atualizarVenda(dto, 1L);

        assertNotNull(response);
        verify(vendaService).update(any(Venda.class), eq(1L));
    }

    @Test
    void testCancelarVenda() {
        facade.cancelarVenda(1L);
        verify(vendaService).delete(1L);
    }
}