package nextcar.service;

import org.springframework.stereotype.Component;

@Component("normal")
public class PrecoNormal implements PrecoStrategy{
    @Override
    public double calcularPreco(double valor) {
        return valor;
    }
}
