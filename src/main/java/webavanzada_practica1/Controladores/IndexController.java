package webavanzada_practica1.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class IndexController {

    @Autowired
    private SeguridadService seguridadService;


    @RequestMapping("/")
    public String index(Model model, Principal principal ){


        seguridadService.crearUsuarioAdmin();

        model.addAttribute("titulo", "EJ CXA");

        // model.addAttribute("usuario", principal.getName());

        return "redirect:/cliente/";
    }

    @RequestMapping("/login")
    public String login(){

        return "/freemarker/login";
    }
}
