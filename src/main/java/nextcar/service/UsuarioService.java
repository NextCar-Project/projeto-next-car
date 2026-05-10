package nextcar.service;

import jakarta.persistence.EntityNotFoundException;
import nextcar.model.Usuario;
import nextcar.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public UsuarioService (UsuarioRepository repository,  PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public Usuario save (Usuario usuario) {
        String senha = encoder.encode(usuario.getSenha());
        usuario.setSenha(senha);
       return repository.save(usuario);
    }

    public List<Usuario> find() {
        return repository.findAll();
    }

    public Usuario update (Usuario usuarioNovo, Long id) {
        Usuario usuarioExiste = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

        usuarioExiste.setNome(usuarioNovo.getNome());
        usuarioExiste.setLogin(usuarioNovo.getLogin());
        usuarioExiste.setSenha(usuarioNovo.getSenha());

        String senha = encoder.encode(usuarioExiste.getSenha());
        usuarioExiste.setSenha(senha);

        return repository.save(usuarioExiste);
    }

    public void delete (Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        repository.delete(usuario);
    }
}