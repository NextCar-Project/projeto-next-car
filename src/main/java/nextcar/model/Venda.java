package nextcar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "Data obrigatoria")
    private LocalDate data;
    @Positive
    @NotNull(message = "Valor obrigatorio")
    private double valorFinal;
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    @NotNull(message = "Veiculo obrigatorio")
    private Veiculo veiculo;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @NotNull(message = "Usuario obrigatorio")
    private Usuario usuario;

    public  Venda() {}

    public Venda(LocalDate data, double valorFinal, Veiculo veiculo, Usuario usuario) {
        this.data = data;
        this.valorFinal = valorFinal;
        this.veiculo = veiculo;
        this.usuario = usuario;
    }
}