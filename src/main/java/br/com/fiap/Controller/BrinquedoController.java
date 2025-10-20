package br.com.fiap.Controller;

import br.com.fiap.Model.Brinquedo;
import br.com.fiap.Service.BrinquedoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brinquedos")
public class BrinquedoController {

    private final BrinquedoService brinquedoService;

    public BrinquedoController(BrinquedoService brinquedoService) {
        this.brinquedoService = brinquedoService;
    }

    // Listar brinquedos
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("brinquedos", brinquedoService.getAllBrinquedos());
        return "brinquedos/lista"; // thymeleaf -> templates/brinquedos/lista.html
    }

    // Formulário de cadastro
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("brinquedo", new Brinquedo());
        return "brinquedos/form";
    }

    // Salvar cadastro
    @PostMapping
    public String salvar(@Valid @ModelAttribute("brinquedo") Brinquedo brinquedo, BindingResult result) {
        if (result.hasErrors()) {
            return "brinquedos/form";
        }
        brinquedoService.saveBrinquedo(brinquedo);
        return "redirect:/brinquedos";
    }

    // Editar (carrega formulário com dados)
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Brinquedo brinquedo = brinquedoService.getBrinquedoById(id).orElseThrow(() -> new IllegalArgumentException("Brinquedo inválido"));
        model.addAttribute("brinquedo", brinquedo);
        return "brinquedos/form";
    }

    // Atualizar (submit do form de edição)
    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("brinquedo") Brinquedo brinquedo,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "brinquedos/form";
        }
        brinquedoService.updateBrinquedo(id, brinquedo);
        return "redirect:/brinquedos";
    }

    // Excluir
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        brinquedoService.deleteBrinquedo(id);
        return "redirect:/brinquedos";
    }
}


