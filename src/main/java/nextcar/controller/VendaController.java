package nextcar.controller;

import jakarta.validation.Valid;
import nextcar.dto.VendaRequestDTO;
import nextcar.dto.VendaResponseDTO;
import nextcar.service.VendaFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
@CrossOrigin("*")
public class VendaController {

    private final VendaFacade facade;

    public VendaController(VendaFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public VendaResponseDTO save(@RequestBody @Valid VendaRequestDTO dto) {
        return facade.registrarVenda(dto);
    }

    @GetMapping
    public List<VendaResponseDTO> find() {
        return facade.listarVendas();
    }

    @PutMapping("{id}")
    public VendaResponseDTO update(@RequestBody @Valid VendaRequestDTO dto, @PathVariable Long id) {
        return facade.atualizarVenda(dto, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        facade.cancelarVenda(id);
    }
}
