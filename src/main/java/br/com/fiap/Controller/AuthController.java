package br.com.fiap.Controller;

import br.com.fiap.Model.Usuario;
import br.com.fiap.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/brinquedos";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "logout", required = false) String logout,
                       Authentication authentication,
                       Model model) {
        
        // Se já estiver logado, redireciona para brinquedos
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/brinquedos";
        }
        
        if (error != null) {
            model.addAttribute("error", "Usuário ou senha inválidos!");
        }
        if (logout != null) {
            model.addAttribute("message", "Logout realizado com sucesso!");
        }
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute("usuario") Usuario usuario, 
                                 BindingResult result, 
                                 Model model) {
        
        // Validações customizadas
        if (usuarioService.existeUsername(usuario.getUsername())) {
            result.rejectValue("username", "error.usuario", "Nome de usuário já existe");
        }
        
        if (usuarioService.existeEmail(usuario.getEmail())) {
            result.rejectValue("email", "error.usuario", "Email já está em uso");
        }
        
        if (result.hasErrors()) {
            return "auth/register";
        }
        
        try {
            usuarioService.salvarUsuario(usuario);
            model.addAttribute("success", "Usuário cadastrado com sucesso! Faça o login.");
            return "auth/login";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao cadastrar usuário. Tente novamente.");
            return "auth/register";
        }
    }
}