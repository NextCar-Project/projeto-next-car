package nextcar.dto;

import lombok.Getter;
import lombok.Setter;
import nextcar.model.Venda;

import java.time.LocalDate;

@Getter
@Setter
public class VendaResponseDTO {

    private Long id;
    private LocalDate data;
    private double valorFinal;
    private Long veiculoId;
    private String veiculoDescricao;
    private Long usuarioId;
    private String usuarioNome;

    public VendaResponseDTO() {}

    public VendaResponseDTO(Venda venda) {
        this.id               = venda.getId();
        this.data             = venda.getData();
        this.valorFinal       = venda.getValorFinal();
        this.veiculoId        = venda.getVeiculo().getId();
        this.veiculoDescricao = venda.getVeiculo().getMarca() + " " + venda.getVeiculo().getModelo();
        this.usuarioId        = venda.getUsuario().getId();
        this.usuarioNome      = venda.getUsuario().getNome();
    }
}
