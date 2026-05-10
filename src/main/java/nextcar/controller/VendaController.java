package nextcar.controller;

import nextcar.model.Venda;
import nextcar.service.VendaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    private final VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    @PostMapping
    public Venda save(@RequestBody Venda venda) {
        return service.save(venda);
    }

    @GetMapping
    public List<Venda> find() {
        return service.find();
    }

    @PutMapping("{id}")
    public Venda update(@RequestBody Venda venda, @PathVariable Long id) {
        return service.update(venda, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}