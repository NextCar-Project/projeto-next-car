package nextcar.service;

import org.springframework.stereotype.Component;

@Component("desconto")
public class PrecoDesconto implements  PrecoStrategy {
    @Override
    public double calcularPreco(double valor) {
        return valor * 0.9;
    }
}
