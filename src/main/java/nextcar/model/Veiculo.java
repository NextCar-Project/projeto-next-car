package nextcar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Ano obrigatorio")
    private Integer ano;
    @Positive
    @NotBlank(message = "Valor obrigatorio")
    private Double preco;
    @NotBlank(message = "Tipo obrigatorio")
    private String tipo;
    @NotBlank(message = "Status obrigatorio")
    private String status;
}