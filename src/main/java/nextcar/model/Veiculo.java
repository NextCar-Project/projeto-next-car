package nextcar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Marca obrigatoria")
    private String marca;
    @NotBlank(message = "Modelo obrigatorio")
    private String modelo;
    @Positive
    @NotNull(message = "Ano obrigatorio")
    private Integer ano;
    @Positive
    @NotNull(message = "Valor obrigatorio")
    private Double preco;
    @NotBlank(message = "Tipo obrigatorio")
    private String tipo;
    @NotBlank(message = "Status obrigatorio")
    private String status;
    @NotBlank(message = "Tipo de preco obrigatorio")
    private String tipoPreco;

    public Veiculo() {}

    public Veiculo (String marca, String modelo, Integer ano, Double preco, String tipo, String status,  String tipoPreco) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
        this.tipo = tipo;
        this.status = status;
        this.tipoPreco = tipoPreco;
    }
}