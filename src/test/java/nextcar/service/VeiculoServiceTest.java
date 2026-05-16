package nextcar.service;

import jakarta.persistence.EntityNotFoundException;
import nextcar.exception.TipoPrecoInvalidoException;
import nextcar.model.Veiculo;
import nextcar.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VeiculoServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

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
        veiculo.setStatus("disponivel");
        veiculo.setTipoPreco("normal");
    }

    @Test
    void testSaveVeiculo() {
        when(veiculoRepository.save(veiculo)).thenReturn(veiculo);
        Veiculo resultado = veiculoService.save(veiculo);
        assertNotNull(resultado);
        assertEquals("Toyota", resultado.getMarca());
        verify(veiculoRepository).save(veiculo);
    }

    @Test
    void testFindVeiculos() {
        when(veiculoRepository.findAll()).thenReturn(List.of(veiculo));
        assertEquals(1, veiculoService.find().size());
        verify(veiculoRepository).findAll();
    }

    @Test
    void testUpdateVeiculo() {
        Veiculo novo = new Veiculo();
        novo.setMarca("Honda");
        novo.setModelo("Civic");
        novo.setAno(2022);
        novo.setPreco(120000.0);
        novo.setTipo("Sedan");
        novo.setStatus("disponivel");
        novo.setTipoPreco("normal");

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);

        Veiculo resultado = veiculoService.update(novo, 1L);

        assertNotNull(resultado);
        verify(veiculoRepository).save(veiculo);
    }

    @Test
    void testUpdateVeiculoNotFound() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> veiculoService.update(veiculo, 1L));
    }

    @Test
    void testUpdateVeiculoTipoPrecoInvalido() {

        Veiculo novo = new Veiculo();
        novo.setMarca("Honda");
        novo.setModelo("Civic");
        novo.setAno(2022);
        novo.setPreco(100000.0);
        novo.setTipo("Sedan");
        novo.setStatus("disponivel");
        novo.setTipoPreco("invalido");

        Veiculo existente = new Veiculo();
        existente.setId(1L);

        when(veiculoRepository.findById(1L))
                .thenReturn(Optional.of(existente));

        assertThrows(TipoPrecoInvalidoException.class,
                () -> veiculoService.update(novo, 1L));
    }

    @Test
    void testDeleteVeiculo() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        veiculoService.delete(1L);
        verify(veiculoRepository).delete(veiculo);
    }

    @Test
    void testDeleteVeiculoNotFound() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> veiculoService.delete(1L));
    }

    @Test
    void testBuscarPorIdNotFound() {
        when(veiculoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> veiculoService.buscarPorId(99L));
    }
}