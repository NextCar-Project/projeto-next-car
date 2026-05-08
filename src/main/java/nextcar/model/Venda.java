package nextcar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;
    private double valorFinal;
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
}