package nextcar.service;

import jakarta.persistence.EntityNotFoundException;
import nextcar.dto.UsuarioRequestDTO;
import nextcar.dto.UsuarioResponseDTO;
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
    private UsuarioRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Emerson");
        usuario.setLogin("emerson@test.com");
        usuario.setSenha("senhaCriptografada");

        requestDTO = new UsuarioRequestDTO("Emerson", "emerson@test.com", "123");
    }

    @Test
    void testSaveUsuario() {
        when(encoder.encode("123")).thenReturn("senhaCriptografada");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioResponseDTO resultado = usuarioService.save(requestDTO);

        assertNotNull(resultado);
        assertEquals("Emerson", resultado.getNome());
        assertEquals("emerson@test.com", resultado.getLogin());
        verify(encoder).encode("123");
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void testFindUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<UsuarioResponseDTO> usuarios = usuarioService.find();

        assertEquals(1, usuarios.size());
        assertEquals("Emerson", usuarios.get(0).getNome());
        verify(usuarioRepository).findAll();
    }

    @Test
    void testUpdateUsuario() {
        UsuarioRequestDTO novoDTO = new UsuarioRequestDTO("Novo Nome", "novo@test.com", "456");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(encoder.encode("456")).thenReturn("novaSenhaCriptografada");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioResponseDTO resultado = usuarioService.update(novoDTO, 1L);

        assertNotNull(resultado);
        verify(usuarioRepository).findById(1L);
        verify(encoder).encode("456");
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testUpdateUsuarioNotFound() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> usuarioService.update(requestDTO, 1L));

        verify(usuarioRepository).findById(1L);
    }

    @Test
    void testDeleteUsuario() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        usuarioService.delete(1L);

        verify(usuarioRepository).findById(1L);
        verify(usuarioRepository).delete(usuario);
    }

    @Test
    void testDeleteUsuarioNotFound() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> usuarioService.delete(1L));

        verify(usuarioRepository).findById(1L);
    }

    @Test
    void testBuscarPorIdNotFound() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> usuarioService.buscarPorId(99L));

        verify(usuarioRepository).findById(99L);
    }
}