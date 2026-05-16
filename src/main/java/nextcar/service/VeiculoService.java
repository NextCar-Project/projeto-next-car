package nextcar.service;

import jakarta.persistence.EntityNotFoundException;
import nextcar.exception.TipoPrecoInvalidoException;
import nextcar.model.Veiculo;
import nextcar.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo save(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> find() {
        return veiculoRepository.findAll();
    }

    public Veiculo update(Veiculo veiculoNovo, Long id) {
        Veiculo existe = buscarPorId(id);

        if (!veiculoNovo.getTipoPreco().equalsIgnoreCase("normal") && !veiculoNovo.getTipoPreco().equalsIgnoreCase("desconto")) {

            throw new TipoPrecoInvalidoException("");
        }

        existe.setMarca(veiculoNovo.getMarca());
        existe.setModelo(veiculoNovo.getModelo());
        existe.setAno(veiculoNovo.getAno());
        existe.setPreco(veiculoNovo.getPreco());
        existe.setTipo(veiculoNovo.getTipo());
        existe.setStatus(veiculoNovo.getStatus());
        existe.setTipoPreco(veiculoNovo.getTipoPreco());
        return veiculoRepository.save(existe);
    }

    public void delete(Long id) {
        veiculoRepository.delete(buscarPorId(id));
    }

    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado: " + id));
    }
}