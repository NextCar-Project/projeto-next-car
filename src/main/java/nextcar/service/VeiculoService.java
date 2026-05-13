package nextcar.service;

import jakarta.persistence.EntityNotFoundException;
import nextcar.model.Veiculo;
import nextcar.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final Map<String, PrecoStrategy> strategies;

    public VeiculoService(VeiculoRepository veiculoRepository, Map<String, PrecoStrategy> strategies) {
        this.veiculoRepository = veiculoRepository;
        this.strategies = strategies;
    }

    public Veiculo save(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> find() {
        return veiculoRepository.findAll();
    }

    public Veiculo update (Veiculo veiculoNovo, Long id) {
        Veiculo veiculoExiste = veiculoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado"));

        veiculoExiste.setMarca(veiculoNovo.getMarca());
        veiculoExiste.setModelo(veiculoNovo.getModelo());
        veiculoExiste.setAno(veiculoNovo.getAno());
        veiculoExiste.setPreco(veiculoNovo.getPreco());
        veiculoExiste.setTipo(veiculoNovo.getTipo());
        veiculoExiste.setTipoPreco(veiculoNovo.getTipoPreco());

        PrecoStrategy strategy = strategies.get(veiculoNovo.getTipoPreco());

        if (strategy == null) throw new RuntimeException("Preço invalido");

        double precoFinal = strategy.calcularPreco(veiculoExiste.getPreco());

        veiculoExiste.setPreco(precoFinal);

        return veiculoRepository.save(veiculoExiste);
    }

    public void delete(Long id) {
        Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado"));
        veiculoRepository.delete(veiculo);
    }
}