package nextcar.service;

import jakarta.persistence.EntityNotFoundException;
import nextcar.model.Veiculo;
import nextcar.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final PrecoStrategyFactory strategyFactory;

    public VeiculoService(VeiculoRepository veiculoRepository, PrecoStrategyFactory strategyFactory) {
        this.veiculoRepository = veiculoRepository;
        this.strategyFactory   = strategyFactory;
    }

    public Veiculo save(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> find() {
        return veiculoRepository.findAll();
    }

    public Veiculo update(Veiculo veiculoNovo, Long id) {
        Veiculo existe = buscarPorId(id);
        double precoFinal = strategyFactory.getStrategy(veiculoNovo.getTipoPreco()).calcularPreco(veiculoNovo.getPreco());

        existe.setMarca(veiculoNovo.getMarca());
        existe.setModelo(veiculoNovo.getModelo());
        existe.setAno(veiculoNovo.getAno());
        existe.setPreco(precoFinal);
        existe.setTipo(veiculoNovo.getTipo());
        existe.setStatus(veiculoNovo.getStatus());
        existe.setTipoPreco(veiculoNovo.getTipoPreco());

        return veiculoRepository.save(existe);
    }

    public void delete(Long id) {
        veiculoRepository.delete(buscarPorId(id));
    }

    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veiculo nao encontrado: " + id));
    }
}
