package nextcar.service;

import nextcar.model.Usuario;
import nextcar.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService (UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario save (Usuario usuario) {
       return repository.save(usuario);
    }
}
