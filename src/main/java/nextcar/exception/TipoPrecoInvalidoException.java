package nextcar.exception;

public class TipoPrecoInvalidoException extends RuntimeException {

    public TipoPrecoInvalidoException(String tipo) {
        super("Tipo de preco invalido: " + tipo);
    }
}
