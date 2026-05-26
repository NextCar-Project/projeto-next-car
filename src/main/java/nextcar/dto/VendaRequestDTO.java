package nextcar.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VendaRequestDTO {

    @NotNull(message = "Data obrigatoria")
    private LocalDate data;

    @Positive
    @NotNull(message = "Valor obrigatorio")
    private Double valorFinal;

    @NotNull(message = "Veiculo obrigatorio")
    private Long veiculoId;

    @NotNull(message = "Usuario obrigatorio")
    private Long usuarioId;

    public VendaRequestDTO() {}

}
