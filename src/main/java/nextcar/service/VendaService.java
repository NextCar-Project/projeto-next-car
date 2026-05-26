package nextcar.service;

import jakarta.persistence.EntityNotFoundException;
import nextcar.model.Venda;
import nextcar.repository.VendaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public Venda save(Venda venda) {
        return vendaRepository.save(venda);
    }

    public List<Venda> find() {
        return vendaRepository.findAll();
    }

    public Venda update(Venda vendaNova, Long id) {
        Venda existe = buscarPorId(id);
        existe.setData(vendaNova.getData());
        existe.setValorFinal(vendaNova.getValorFinal());
        existe.setVeiculo(vendaNova.getVeiculo());
        return vendaRepository.save(existe);
    }

    public void delete(Long id) {
        vendaRepository.delete(buscarPorId(id));
    }

    public Venda buscarPorId(Long id) {
        return vendaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Venda nao encontrada: " + id));
    }
}
