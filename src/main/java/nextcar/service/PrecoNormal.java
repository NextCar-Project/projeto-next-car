package nextcar.service;

public class PrecoNormal implements PrecoStrategy{
    @Override
    public double calcularPreco(double valor) {
        return valor;
    }
}
