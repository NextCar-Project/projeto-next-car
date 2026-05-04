package nextcar.service;

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
    public Venda salvar(Venda venda) {
        return repository.save(venda);
    }
    public List<Venda> listar(){
        return repository.findAll();
    }
}