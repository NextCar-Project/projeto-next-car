package nextcar.controller;

import nextcar.model.Veiculo;
import nextcar.service.VeiculoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @PostMapping("inserir")
    public Veiculo salvar(@RequestBody Veiculo veiculo) {
        return service.salvar(veiculo);
    }

    @GetMapping("/mostrar")
    public List<Veiculo> listar() {
        return service.listar();
    }

    @PutMapping("/{id}")
    public Veiculo modificar (@PathVariable Long id, @RequestBody Veiculo veiculo) {
        return service.modificar(id, veiculo);
    }

    @DeleteMapping("/{id}")
    public void deletar (@PathVariable long id) {
        service.deletar(id);
    }
}