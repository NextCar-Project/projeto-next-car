package nextcar.service;

import jakarta.persistence.EntityNotFoundException;
import nextcar.model.Usuario;
import nextcar.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Emerson");
        usuario.setLogin("emerson");
        usuario.setSenha("123");
    }

    @Test
    void testSaveUsuario() {

        when(encoder.encode("123")).thenReturn("senhaCriptografada");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.save(usuario);

        assertNotNull(resultado);
        assertEquals("senhaCriptografada", resultado.getSenha());

        verify(encoder, times(1)).encode("123");
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testFindUsuarios() {

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> usuarios = usuarioService.find();

        assertEquals(1, usuarios.size());

        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testUpdateUsuario() {

        Usuario usuarioNovo = new Usuario();
        usuarioNovo.setNome("Novo Nome");
        usuarioNovo.setLogin("novoLogin");
        usuarioNovo.setSenha("456");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(encoder.encode("456")).thenReturn("novaSenhaCriptografada");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = usuarioService.update(usuarioNovo, 1L);

        assertNotNull(resultado);
        assertEquals("Novo Nome", resultado.getNome());
        assertEquals("novoLogin", resultado.getLogin());
        assertEquals("novaSenhaCriptografada", resultado.getSenha());

        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testUpdateUsuarioNotFound() {

        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> usuarioService.update(usuario, 1L));

        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteUsuario() {

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        usuarioService.delete(1L);

        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).delete(usuario);
    }

    @Test
    void testDeleteUsuarioNotFound() {

        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> usuarioService.delete(1L));

        verify(usuarioRepository, times(1)).findById(1L);
    }
}