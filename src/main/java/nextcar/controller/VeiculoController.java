package nextcar.controller;

import nextcar.model.Veiculo;
import nextcar.service.VeiculoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin("*")
public class VeiculoController {
    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @PostMapping
    public Veiculo save(@RequestBody Veiculo veiculo) {
        return service.save(veiculo);
    }

    @GetMapping
    public List<Veiculo> find() {
        return service.find();
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