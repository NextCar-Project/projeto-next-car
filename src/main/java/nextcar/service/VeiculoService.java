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

    public Veiculo salvar(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public List<Veiculo> listar() {
        return repository.findAll();
    }

    public Veiculo modificar (Long id, Veiculo novoVeiculo) {
        Veiculo veiculoExiste = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado"));

        veiculoExiste.setMarca(novoVeiculo.getMarca());
        veiculoExiste.setModelo(novoVeiculo.getModelo());
        veiculoExiste.setAno(novoVeiculo.getAno());
        veiculoExiste.setPreco(novoVeiculo.getPreco());
        veiculoExiste.setTipo(novoVeiculo.getTipo());

        return repository.save(veiculoExiste);
    }

    public void deletar (Long id) {
        Veiculo veiculo = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado"));
        repository.delete(veiculo);
    }
}