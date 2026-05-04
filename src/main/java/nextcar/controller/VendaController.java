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
    public Venda salvar(@RequestBody Venda venda) {
        return service.salvar(venda);
    }

    @GetMapping
    public List<Venda> listar() {
        return service.listar();
    }
}