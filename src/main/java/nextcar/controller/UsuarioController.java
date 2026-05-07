package nextcar.controller;


import nextcar.model.Usuario;
import nextcar.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController (UsuarioService service) {
        this.service = service;
    }

    @PostMapping("inserir")
    public Usuario save (Usuario usuario) {
        return service.save(usuario);
    }
}
