package nextcar.service;

import nextcar.exception.TipoPrecoInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PrecoStrategyFactoryTest {

    private PrecoStrategyFactory factory;

    @BeforeEach
    void setUp() {
        factory = new PrecoStrategyFactory(Map.of(
                "normal",   new PrecoNormal(),
                "desconto", new PrecoDesconto()
        ));
    }

    @Test
    void testGetStrategyNormal() {
        PrecoStrategy strategy = factory.getStrategy("normal");
        assertNotNull(strategy);
        assertEquals(100.0, strategy.calcularPreco(100.0));
    }

    @Test
    void testGetStrategyDesconto() {
        PrecoStrategy strategy = factory.getStrategy("desconto");
        assertNotNull(strategy);
        assertEquals(90.0, strategy.calcularPreco(100.0));
    }

    @Test
    void testGetStrategyTipoInvalido() {
        assertThrows(TipoPrecoInvalidoException.class,
                () -> factory.getStrategy("invalido"));
    }

    @Test
    void testMensagemExcecaoContemTipo() {
        TipoPrecoInvalidoException ex = assertThrows(TipoPrecoInvalidoException.class,
                () -> factory.getStrategy("xyz"));
        assertTrue(ex.getMessage().contains("xyz"));
    }
}