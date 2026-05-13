package nextcar.service;

import org.springframework.stereotype.Component;

@Component("normal")
public interface PrecoStrategy {
    double calcularPreco(double valor);
}
