package nextcar.service;

import nextcar.dto.VendaRequestDTO;
import nextcar.dto.VendaResponseDTO;
import nextcar.model.Usuario;
import nextcar.model.Veiculo;
import nextcar.model.Venda;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VendaFacade {

    private final VendaService vendaService;
    private final VeiculoService veiculoService;
    private final UsuarioService usuarioService;
    private final PrecoStrategyFactory strategyFactory;

    public VendaFacade(VendaService vendaService, VeiculoService veiculoService, UsuarioService usuarioService, PrecoStrategyFactory strategyFactory) {
        this.vendaService = vendaService;
        this.veiculoService = veiculoService;
        this.usuarioService = usuarioService;
        this.strategyFactory = strategyFactory;
    }

    public VendaResponseDTO registrarVenda(VendaRequestDTO dto) {
        Veiculo veiculo = veiculoService.buscarPorId(dto.getVeiculoId());
        Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId());

        double precoFinal = strategyFactory.getStrategy(veiculo.getTipoPreco()).calcularPreco(veiculo.getPreco());

        if (veiculo.getStatus().equalsIgnoreCase("disponivel")) {
            veiculo.setStatus("vendido");
            veiculoService.save(veiculo);
        }

        Venda venda = new Venda(dto.getData(), precoFinal, veiculo, usuario);
        return new VendaResponseDTO(vendaService.save(venda));
    }

    public List<VendaResponseDTO> listarVendas() {
        return vendaService.find()
                .stream()
                .map(VendaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public VendaResponseDTO atualizarVenda(VendaRequestDTO dto, Long id) {
        Veiculo veiculo = veiculoService.buscarPorId(dto.getVeiculoId());

        Venda vendaNova = new Venda();
        vendaNova.setData(dto.getData());
        vendaNova.setValorFinal(dto.getValorFinal());
        vendaNova.setVeiculo(veiculo);

        return new VendaResponseDTO(vendaService.update(vendaNova, id));
    }

    public void cancelarVenda(Long id) {
        vendaService.delete(id);
    }
}
