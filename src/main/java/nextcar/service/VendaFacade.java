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

    public VendaFacade(VendaService vendaService, VeiculoService veiculoService, UsuarioService usuarioService) {
        this.vendaService = vendaService;
        this.veiculoService = veiculoService;
        this.usuarioService = usuarioService;
    }

    public VendaResponseDTO registrarVenda(VendaRequestDTO dto) {
        Veiculo veiculo = veiculoService.buscarPorId(dto.getVeiculoId());
        Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId());

        Venda venda = new Venda(dto.getData(), dto.getValorFinal(), veiculo, usuario);
        Venda salva = vendaService.save(venda);

        return new VendaResponseDTO(salva);
    }

    public List<VendaResponseDTO> listarVendas() {
        return vendaService.find()
                .stream()
                .map(VendaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public VendaResponseDTO atualizarVenda(VendaRequestDTO dto, Long id) {
        Venda vendaNova = new Venda();
        vendaNova.setData(dto.getData());
        vendaNova.setValorFinal(dto.getValorFinal());

        Veiculo veiculo = veiculoService.buscarPorId(dto.getVeiculoId());
        vendaNova.setVeiculo(veiculo);

        Venda atualizada = vendaService.update(vendaNova, id);
        return new VendaResponseDTO(atualizada);
    }

    public void cancelarVenda(Long id) {
        vendaService.delete(id);
    }
}
