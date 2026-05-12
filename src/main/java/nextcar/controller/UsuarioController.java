package nextcar.controller;

import jakarta.validation.Valid;
import nextcar.model.Usuario;
import nextcar.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController (UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public Usuario save (@RequestBody @Valid Usuario usuario) {
        return service.save(usuario);
    }

    @GetMapping
    public List<Usuario> find () {
        return service.find();
    }

    @PutMapping("{id}")
    public Usuario update (@RequestBody Usuario usuario, @PathVariable Long id) {
        return service.update(usuario, id);
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }
}