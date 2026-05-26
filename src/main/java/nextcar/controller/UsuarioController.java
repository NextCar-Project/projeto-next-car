package nextcar.controller;

import jakarta.validation.Valid;
import nextcar.dto.UsuarioRequestDTO;
import nextcar.dto.UsuarioResponseDTO;
import nextcar.service.UsuarioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public UsuarioResponseDTO save(@RequestBody @Valid UsuarioRequestDTO dto) {
        return usuarioService.save(dto);
    }

    @GetMapping
    public List<UsuarioResponseDTO> find() {
        return usuarioService.find();
    }

    @PutMapping("{id}")
    public UsuarioResponseDTO update(@RequestBody @Valid UsuarioRequestDTO dto, @PathVariable Long id) {
        return usuarioService.update(dto, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}