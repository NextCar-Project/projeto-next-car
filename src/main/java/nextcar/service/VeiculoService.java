package nextcar.service;

import jakarta.persistence.EntityNotFoundException;
import nextcar.model.Veiculo;
import nextcar.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public Veiculo save(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public List<Veiculo> find() {
        return repository.findAll();
    }

    public Veiculo update (Veiculo veiculoNovo, Long id) {
        Veiculo veiculoExiste = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado"));

        veiculoExiste.setMarca(veiculoNovo.getMarca());
        veiculoExiste.setModelo(veiculoNovo.getModelo());
        veiculoExiste.setAno(veiculoNovo.getAno());
        veiculoExiste.setPreco(veiculoNovo.getPreco());
        veiculoExiste.setTipo(veiculoNovo.getTipo());

        return repository.save(veiculoExiste);
    }

    public void delete(Long id) {
        Veiculo veiculo = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado"));
        repository.delete(veiculo);
    }
}