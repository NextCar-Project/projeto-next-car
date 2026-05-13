package nextcar.service;

import jakarta.persistence.EntityNotFoundException;
import nextcar.model.Veiculo;
import nextcar.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VeiculoServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @Mock
    private Map<String, PrecoStrategy> strategies;

    @Mock
    private PrecoStrategy precoStrategy;

    @InjectMocks
    private VeiculoService veiculoService;

    private Veiculo veiculo;

    @BeforeEach
    void setUp() {
        veiculo = new Veiculo();

        veiculo.setId(1L);
        veiculo.setMarca("Toyota");
        veiculo.setModelo("Corolla");
        veiculo.setAno(2020);
        veiculo.setPreco(80000.0);
        veiculo.setTipo("Sedan");
        veiculo.setTipoPreco("normal");
    }

    @Test
    void testSaveVeiculo() {

        when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

        Veiculo resultado = veiculoService.save(veiculo);

        assertNotNull(resultado);
        assertEquals("Toyota", resultado.getMarca());

        verify(veiculoRepository, times(1)).save(veiculo);
    }

    @Test
    void testFindVeiculos() {

        when(veiculoRepository.findAll()).thenReturn(List.of(veiculo));

        List<Veiculo> veiculos = veiculoService.find();

        assertEquals(1, veiculos.size());

        verify(veiculoRepository, times(1)).findAll();
    }

    @Test
    void testUpdateVeiculo() {

        Veiculo veiculoNovo = new Veiculo();

        veiculoNovo.setMarca("Honda");
        veiculoNovo.setModelo("Civic");
        veiculoNovo.setAno(2022);
        veiculoNovo.setPreco(120000.0);
        veiculoNovo.setTipo("Sedan");
        veiculoNovo.setTipoPreco("normal");

        when(veiculoRepository.findById(1L))
                .thenReturn(Optional.of(veiculo));

        when(veiculoRepository.save(any(Veiculo.class)))
                .thenReturn(veiculo);

        when(strategies.get("normal"))
                .thenReturn(precoStrategy);

        when(precoStrategy.calcularPreco(anyDouble()))
                .thenReturn(120000.0);

        Veiculo resultado =
                veiculoService.update(veiculoNovo, 1L);

        assertNotNull(resultado);
        assertEquals("Honda", resultado.getMarca());
        assertEquals("Civic", resultado.getModelo());
        assertEquals(2022, resultado.getAno());
        assertEquals(120000.0, resultado.getPreco());
        assertEquals("Sedan", resultado.getTipo());
        assertEquals("normal", resultado.getTipoPreco());

        verify(veiculoRepository, times(1)).findById(1L);
        verify(veiculoRepository, times(1)).save(veiculo);
    }

    @Test
    void testUpdateVeiculoNotFound() {

        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> veiculoService.update(veiculo, 1L));

        verify(veiculoRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteVeiculo() {

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        veiculoService.delete(1L);

        verify(veiculoRepository, times(1)).findById(1L);
        verify(veiculoRepository, times(1)).delete(veiculo);
    }

    @Test
    void testDeleteVeiculoNotFound() {

        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> veiculoService.delete(1L));

        verify(veiculoRepository, times(1)).findById(1L);
    }
}