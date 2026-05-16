package nextcar.service;

import jakarta.persistence.EntityNotFoundException;
import nextcar.dto.UsuarioRequestDTO;
import nextcar.dto.UsuarioResponseDTO;
import nextcar.model.Usuario;
import nextcar.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    public UsuarioResponseDTO save(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario(dto.getNome(), dto.getLogin(), encoder.encode(dto.getSenha()));
        return new UsuarioResponseDTO(usuarioRepository.save(usuario));
    }

    public List<UsuarioResponseDTO> find() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO update(UsuarioRequestDTO dto, Long id) {
        Usuario existe = buscarPorId(id);
        existe.setNome(dto.getNome());
        existe.setLogin(dto.getLogin());
        existe.setSenha(encoder.encode(dto.getSenha()));
        return new UsuarioResponseDTO(usuarioRepository.save(existe));
    }

    public void delete(Long id) {
        usuarioRepository.delete(buscarPorId(id));
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado: " + id));
    }
}