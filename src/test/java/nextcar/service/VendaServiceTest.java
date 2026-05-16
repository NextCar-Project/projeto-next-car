package nextcar.service;

import jakarta.persistence.EntityNotFoundException;
import nextcar.model.Usuario;
import nextcar.model.Veiculo;
import nextcar.model.Venda;
import nextcar.repository.VendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VendaServiceTest {

    @Mock
    private VendaRepository vendaRepository;

    @InjectMocks
    private VendaService vendaService;

    @Mock
    private PrecoStrategyFactory precoStrategyFactory;

    @Mock
    private PrecoStrategy precoStrategy;


    private Venda venda;
    private Veiculo veiculo;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        veiculo = new Veiculo();
        veiculo.setId(1L);
        veiculo.setMarca("Toyota");
        veiculo.setModelo("Corolla");
        veiculo.setPreco(80000.0);
        veiculo.setTipoPreco("normal");

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Emerson");
        usuario.setLogin("emerson@test.com");
        usuario.setSenha("hash");

        venda = new Venda();
        venda.setId(1L);
        venda.setData(LocalDate.now());
        venda.setValorFinal(80000.0);
        venda.setVeiculo(veiculo);
        venda.setUsuario(usuario);
    }

    @Test
    void testSaveVenda() {

        when(precoStrategyFactory.getStrategy("normal"))
                .thenReturn(precoStrategy);

        when(precoStrategy.calcularPreco(80000.0))
                .thenReturn(80000.0);

        when(vendaRepository.save(venda))
                .thenReturn(venda);

        Venda resultado = vendaService.save(venda);

        assertNotNull(resultado);
        assertEquals(80000.0, resultado.getValorFinal());

        verify(vendaRepository).save(venda);
    }

    @Test
    void testFindVendas() {
        when(vendaRepository.findAll()).thenReturn(List.of(venda));

        List<Venda> vendas = vendaService.find();

        assertEquals(1, vendas.size());
        verify(vendaRepository).findAll();
    }

    @Test
    void testUpdateVenda() {
        Venda vendaNova = new Venda();
        vendaNova.setData(LocalDate.now());
        vendaNova.setValorFinal(95000.0);
        vendaNova.setVeiculo(veiculo);

        when(vendaRepository.findById(1L)).thenReturn(Optional.of(venda));
        when(vendaRepository.save(any(Venda.class))).thenReturn(venda);

        Venda resultado = vendaService.update(vendaNova, 1L);

        assertNotNull(resultado);
        assertEquals(95000.0, resultado.getValorFinal());
        verify(vendaRepository).findById(1L);
        verify(vendaRepository).save(venda);
    }

    @Test
    void testUpdateVendaNotFound() {
        when(vendaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> vendaService.update(venda, 1L));

        verify(vendaRepository).findById(1L);
    }

    @Test
    void testDeleteVenda() {
        when(vendaRepository.findById(1L)).thenReturn(Optional.of(venda));

        vendaService.delete(1L);

        verify(vendaRepository).findById(1L);
        verify(vendaRepository).delete(venda);
    }

    @Test
    void testDeleteVendaNotFound() {
        when(vendaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> vendaService.delete(1L));

        verify(vendaRepository).findById(1L);
    }

    @Test
    void testBuscarPorIdNotFound() {
        when(vendaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> vendaService.buscarPorId(99L));

        verify(vendaRepository).findById(99L);
    }
}
