package webavanzada_practica1.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import webavanzada_practica1.Entidades.Familia;

import java.security.Principal;
import java.util.Locale;

@Controller
@RequestMapping("/familia")
public class FamiliaController {

    @Autowired
    private FamiliaService familiaService;

    @Autowired
    private MessageSource messageSource;


    @RequestMapping("/")
    public String index(Model model, Principal principal, Locale locale){

        model.addAttribute("titulo", "EJ CXA");

        //Aqui mandare las distintas traducciones de i18n al index
        model.addAttribute("clientesi18n", messageSource.getMessage("clientesi18n", null, locale));
        model.addAttribute("equiposi18n", messageSource.getMessage("equiposi18n", null, locale));
        model.addAttribute("negocioi18n", messageSource.getMessage("negocioi18n", null, locale));
        model.addAttribute("alquileri18n", messageSource.getMessage("alquileri18n", null, locale));
        model.addAttribute("familiasi18n", messageSource.getMessage("familiasi18n", null, locale));
        model.addAttribute("administradori18n", messageSource.getMessage("administradori18n", null, locale));
        model.addAttribute("usuariosi18n", messageSource.getMessage("usuariosi18n", null, locale));
        model.addAttribute("opcionei18n", messageSource.getMessage("opcionei18n", null, locale));
        model.addAttribute("listafamiliai18n", messageSource.getMessage("listafamiliai18n", null, locale));
        model.addAttribute("agregarfamiliai18n", messageSource.getMessage("agregarfamiliai18n", null, locale));
        model.addAttribute("nombrefamiliai18n", messageSource.getMessage("nombrefamiliai18n", null, locale));
        model.addAttribute("subfamiliai18n", messageSource.getMessage("subfamiliai18n", null, locale));
        model.addAttribute("familiai18n", messageSource.getMessage("familiai18n", null, locale));

        model.addAttribute("familias", familiaService.listarFamilias());
        model.addAttribute("usuario", principal.getName());

        return "/freemarker/familia";
    }


    @RequestMapping("/creacion")
    public String creacionFamilia(Model model, Locale locale){

        model.addAttribute("agregarfamiliai18n", messageSource.getMessage("agregarfamiliai18n", null, locale));
        model.addAttribute("nombrefamiliai18n", messageSource.getMessage("nombrefamiliai18n", null, locale));
        model.addAttribute("subfamiliai18n", messageSource.getMessage("subfamiliai18n", null, locale));
        model.addAttribute("familiai18n", messageSource.getMessage("familiai18n", null, locale));
        model.addAttribute("botonguardari18n", messageSource.getMessage("botonguardari18n", null, locale));
        model.addAttribute("botoncancelari18n", messageSource.getMessage("botoncancelari18n", null, locale));

        model.addAttribute("titulo", "Electrodomesticos CXA");
        model.addAttribute("familias", familiaService.listarFamilias());

        return "/freemarker/crearfamilia";
    }


    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public String crearFamilia(@RequestParam(name = "nombre") String nombre, @RequestParam(name = "subFamilia", required = false) boolean subFamilia, @RequestParam(name = "idFamilia", required = false) Long idFamilia){


        if (subFamilia == false){

            Familia familiaToCreate = new Familia(nombre,subFamilia);

            familiaService.crearFamilia(familiaToCreate);
        }

        if (subFamilia == true){


            Familia familiaToFind = familiaService.encontrarFamiliaPorId(idFamilia);

            Familia familiaToCreate = new Familia(nombre,subFamilia, familiaToFind);

            familiaService.crearFamilia(familiaToCreate);

        }

        return "redirect:/familia/";
    }


    @RequestMapping("/borrar")
    public String eliminarFamilia(@RequestParam(name = "id") long id){

        familiaService.eliminarFamilia(id);

        return "redirect:/familia/";
    }
}
