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
    @NotNull(message = "Marca obrigatoria")
    private String marca;
    @NotNull(message = "Modelo obrigatorio")
    private String modelo;
    @Positive
    @NotNull(message = "Ano obrigatorio")
    private Integer ano;
    @Positive
    @NotNull(message = "Valor obrigatorio")
    private Double preco;
    @NotNull(message = "Tipo obrigatorio")
    private String tipo;
    @NotNull(message = "Status obrigatorio")
    private String status;
}