package nextcar.controller;

import nextcar.model.Usuario;
import nextcar.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController (UsuarioService service) {
        this.service = service;
    }

    @PostMapping("inserir")
    public Usuario save (@RequestBody Usuario usuario) {
        return service.save(usuario);
    }

    @GetMapping("mostrar")
    public List<Usuario> find (Usuario usuario) {
        return service.find(usuario);
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }
}
