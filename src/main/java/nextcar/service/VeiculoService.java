package nextcar.service;

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

    public Veiculo salvar(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public List<Veiculo> listar() {
        return repository.findAll();
    }

    public Veiculo modificar (Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public void deletar (Long id) {
        repository.deleteById(id);
    }
}