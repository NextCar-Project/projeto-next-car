package nextcar.model;

import jakarta.persistence.*;
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
    @NotNull
    private String marca;
    @NotNull
    private String modelo;
    @Positive
    private Integer ano;
    @Positive
    private Double preco;
    @NotNull
    private String tipo;
    @NotNull
    private String status;
}