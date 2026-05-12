package nextcar.service;

import jakarta.persistence.EntityNotFoundException;
import nextcar.model.Usuario;
import nextcar.model.Veiculo;
import nextcar.model.Venda;
import nextcar.repository.UsuarioRepository;
import nextcar.repository.VeiculoRepository;
import nextcar.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {
    private final VendaRepository repository;
    private final VeiculoRepository veiculoRepository;
    private final UsuarioRepository usuarioRepository;

    public VendaService(VendaRepository repository, VeiculoRepository veiculoRepository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.veiculoRepository = veiculoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Venda save(Venda venda) {
       Veiculo veiculo = veiculoRepository.findById(venda.getVeiculo().getId()).orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado"));
       Usuario usuario = usuarioRepository.findById(venda.getUsuario().getId()).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

        venda.setVeiculo(veiculo);
        venda.setUsuario(usuario);
        return repository.save(venda);
    }

    public List<Venda> find(){
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