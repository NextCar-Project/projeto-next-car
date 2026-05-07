package nextcar.service;

import jakarta.persistence.EntityNotFoundException;
import nextcar.model.Venda;
import nextcar.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {
    private final VendaRepository repository;

    public VendaService(VendaRepository repository) {
        this.repository = repository;
    }

    public Venda save(Venda venda) {
        return repository.save(venda);
    }

    public List<Venda> find(Venda venda){
        return repository.findAll();
    }

    public Venda update(Venda vendaNova, Long id) {
        Venda vendaExiste = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Venda não encontrada"));

        vendaExiste.setData(vendaNova.getData());
        vendaExiste.setValorFinal(vendaNova.getValorFinal());
        vendaExiste.setVeiculo(vendaNova.getVeiculo());

        return repository.save(vendaExiste);
    }

    public void delete(Long id){
        Venda venda = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Venda não encontrada"));
        repository.delete(venda);
    }
}