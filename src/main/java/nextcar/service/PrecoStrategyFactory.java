package nextcar.service;

import nextcar.exception.TipoPrecoInvalidoException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PrecoStrategyFactory {

    private final Map<String, PrecoStrategy> strategies;

    public PrecoStrategyFactory(Map<String, PrecoStrategy> strategies) {
        this.strategies = strategies;
    }

    public PrecoStrategy getStrategy(String tipo) {
        PrecoStrategy strategy = strategies.get(tipo);
        if (strategy == null) {
            throw new TipoPrecoInvalidoException(tipo);
        }
        return strategy;
    }
}
