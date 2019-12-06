package webavanzada_practica1.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import webavanzada_practica1.Entidades.Alquiler;
import webavanzada_practica1.Entidades.Cliente;
import webavanzada_practica1.Entidades.Equipo;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/alquiler")
public class AlquilerController {


    @Autowired
    private AlquilerServices alquilerServices;

    @Autowired
    private EquipoServices equipoServices;

    @Autowired
    private ClienteServices clienteServices;

    @Autowired
    private MessageSource messageSource;


    @RequestMapping("/")
    public String index(Model model, Principal principal, Locale locale) {

        //Modelo para la vista.
        model.addAttribute("titulo", "EJ CXA");

        //Traducciones de i18n al index
        model.addAttribute("clientesi18n", messageSource.getMessage("clientesi18n", null, locale));
        model.addAttribute("equiposi18n", messageSource.getMessage("equiposi18n", null, locale));
        model.addAttribute("negocioi18n", messageSource.getMessage("negocioi18n", null, locale));
        model.addAttribute("alquileri18n", messageSource.getMessage("alquileri18n", null, locale));
        model.addAttribute("familiasi18n", messageSource.getMessage("familiasi18n", null, locale));
        model.addAttribute("administradori18n", messageSource.getMessage("administradori18n", null, locale));
        model.addAttribute("usuariosi18n", messageSource.getMessage("usuariosi18n", null, locale));
        model.addAttribute("fechaalquileri18n", messageSource.getMessage("fechaalquileri18n", null, locale));
        model.addAttribute("fechaentregaalquileri18n", messageSource.getMessage("fechaentregaalquileri18n", null, locale));
        model.addAttribute("listaalquileri18n", messageSource.getMessage("listaalquileri18n", null, locale));
        model.addAttribute("agregaralquileri18n", messageSource.getMessage("agregaralquileri18n", null, locale));
        model.addAttribute("clientealquileri18n", messageSource.getMessage("clientealquileri18n", null, locale));
        model.addAttribute("totalalquileri18n", messageSource.getMessage("totalalquileri18n", null, locale));
        model.addAttribute("opcionei18n", messageSource.getMessage("opcionei18n", null, locale));

        model.addAttribute("alquileres", alquilerServices.listarAlquileres());
        model.addAttribute("usuario", principal.getName());
        return "/freemarker/alquiler";
    }


    @RequestMapping("/creacion")
    public String creacionAlquiler(Model model, Locale locale) {

        model.addAttribute("agregaralquileri18n", messageSource.getMessage("agregaralquileri18n", null, locale));
        model.addAttribute("clientealquileri18n", messageSource.getMessage("clientealquileri18n", null, locale));
        model.addAttribute("equipoalquileri18n", messageSource.getMessage("equipoalquileri18n", null, locale));
        model.addAttribute("fechaalquileri18n", messageSource.getMessage("fechaalquileri18n", null, locale));
        model.addAttribute("fechaentregaalquileri18n", messageSource.getMessage("fechaentregaalquileri18n", null, locale));
        model.addAttribute("botonguardari18n", messageSource.getMessage("botonguardari18n", null, locale));
        model.addAttribute("botoncancelari18n", messageSource.getMessage("botoncancelari18n", null, locale));
        model.addAttribute("titulo", "Electrodomesticos CXA");

        // Mando a la vista los clientes y equipos para crear alquiler
        model.addAttribute("clientes", clienteServices.listarClientes());
        model.addAttribute("equipos", equipoServices.listarEquipos());

        return "/freemarker/crearalquiler";
    }

    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public String crearAlquiler(@RequestParam(name = "total", required = false)  int total, @RequestParam(name = "fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam(name = "fechaEntrega") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaEntrega, @RequestParam(name = "idCliente") long idCliente, @RequestParam(name = "idEquipos" , required = false) List<Long> idEquipos) {

        List<Equipo> listaEquiposAlquilados = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);

        Calendar calendarEntrega = Calendar.getInstance();
        calendarEntrega.setTime(fechaEntrega);

        int dias = (calendarEntrega.get(Calendar.DAY_OF_MONTH)- calendar.get(Calendar.DAY_OF_MONTH));

        List<Integer> diasAlquilados = new ArrayList<>();

        diasAlquilados.add(dias);

        // Actualizando existencia de los equipos
        for (Long equipo : idEquipos) {


            Equipo equipoAlquilado = equipoServices.encontrarEquipoPorId(equipo);

            equipoAlquilado.getFamilia().setDiasAlquiler(diasAlquilados);
            //Existencia
            equipoAlquilado.setCantidadExistencia(equipoAlquilado.getCantidadExistencia() - 1);

            total  += dias * equipoAlquilado.getCostoAlquilerPorDia();
            equipoServices.crearEquipo(equipoAlquilado);

            listaEquiposAlquilados.add(equipoAlquilado);
        }

        Cliente clienteQueAlquila = clienteServices.encontrarClientePorId(idCliente);

        Alquiler alquilerToCreate = new Alquiler(fecha, fechaEntrega, clienteQueAlquila, listaEquiposAlquilados,total);

        alquilerServices.crearAlquiler(alquilerToCreate);

        return "redirect:/alquiler/";
    }


    @RequestMapping( value = "/mostrar")
    public String mostrarEquiposAlquilados(Model model, Locale locale, @RequestParam(name = "id") long id){

        Alquiler alquilerToShow = alquilerServices.encontrarAlquilerPorId(id);

        model.addAttribute("titulo", "Electrodomesticos CXA");
        model.addAttribute("clientesi18n", messageSource.getMessage("clientesi18n", null, locale));
        model.addAttribute("equiposi18n", messageSource.getMessage("equiposi18n", null, locale));
        model.addAttribute("negocioi18n", messageSource.getMessage("negocioi18n", null, locale));
        model.addAttribute("alquileri18n", messageSource.getMessage("alquileri18n", null, locale));
        model.addAttribute("familiasi18n", messageSource.getMessage("familiasi18n", null, locale));
        model.addAttribute("administradori18n", messageSource.getMessage("administradori18n", null, locale));
        model.addAttribute("usuariosi18n", messageSource.getMessage("usuariosi18n", null, locale));
        model.addAttribute("nombreequipoi18n", messageSource.getMessage("nombreequipoi18n", null, locale));
        model.addAttribute("marcaequipoi18n", messageSource.getMessage("marcaequipoi18n", null, locale));
        model.addAttribute("cantidadequipoi18n", messageSource.getMessage("cantidadequipoi18n", null, locale));
        model.addAttribute("costoequipoi18n", messageSource.getMessage("costoequipoi18n", null, locale));
        model.addAttribute("fechaalquileri18n", messageSource.getMessage("fechaalquileri18n", null, locale));
        model.addAttribute("fechaentregaalquileri18n", messageSource.getMessage("fechaentregaalquileri18n", null, locale));

        model.addAttribute("alquiler", alquilerToShow);

        //Equipos via el alquiler
        model.addAttribute("equipos", alquilerToShow.getEquipos());

        return "/freemarker/mostrarequiposalquilados";
    }


    @RequestMapping("/borrar")
    public String eliminarAlquiler(@RequestParam(name = "id") long id) {

         alquilerServices.eliminarAlquiler(id);

        return "redirect:/alquiler/";
    }
}
