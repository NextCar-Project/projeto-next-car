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

    @PostMapping
    public Veiculo save(@RequestBody Veiculo veiculo) {
        return service.save(veiculo);
    }

    @GetMapping("mostrar")
    public List<Veiculo> find(Veiculo veiculo) {
        return service.find(veiculo);
    }

    @PutMapping("{id}")
    public Veiculo update(@RequestBody Veiculo veiculo, @PathVariable Long id) {
        return service.update(veiculo, id );
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}