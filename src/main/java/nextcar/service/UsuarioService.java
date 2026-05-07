package nextcar.service;

import jakarta.persistence.EntityNotFoundException;
import nextcar.model.Usuario;
import nextcar.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService (UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario save (Usuario usuario) {
       return repository.save(usuario);
    }

    public List<Usuario> find (Usuario usuario) {
        return repository.findAll();
    }

    public void delete (Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        repository.delete(usuario);
    }
}
