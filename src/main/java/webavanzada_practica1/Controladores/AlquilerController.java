package webavanzada_practica1.controladores;
import webavanzada_practica1.entidades.Alquiler;
import webavanzada_practica1.entidades.Cliente;
import webavanzada_practica1.entidades.Equipo;
import webavanzada_practica1.servicios.AlquilerService;
import webavanzada_practica1.servicios.ClienteService;
import webavanzada_practica1.servicios.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/alquiler")
public class AlquilerController {

    // En cada controlador se prepara el servicio para cada controlador para trabajar las funciones
    @Autowired
    private AlquilerService alquilerService;

    //Mandandoa alquiler los clientes y equipos ya creados, por lo tanto instanciare equiposervices y clienteservices
    @Autowired
    private EquipoService equipoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MessageSource messageSource;


    @RequestMapping("/")
    public String index(Model model, Principal principal, Locale locale) {

        //Indicando el modelo que será pasado a la vista.
        model.addAttribute("titulo", "E&J CXA");

        //Aqui mandare las distintas traducciones de i18n al index
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

        model.addAttribute("alquileres", alquilerService.listarAlquileres());

        // Comento esto para no tener que estar utilizando el login siempre
         model.addAttribute("usuario", principal.getName());
        //Ubicando la vista desde resources/templates
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

        // Para poder crear un alquiler debo mandarle a la vista crearalquiler todos los equipos y clientes ya creados
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("equipos", equipoService.listarEquipos());

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

        // Aqui me encargo de restar la cantidad existencia
        for (Long equipo : idEquipos) {

            // busco el equipo
            Equipo equipoAlquilado = equipoService.encontrarEquipoPorId(equipo);

            //Aqui le estoy mandando a la familia de este equipo los dias que este equipo duro alquilado
            // Y como esto esta dentro de un foreach esto se ira agregando a las demas familias
            // de los demas equipos
            equipoAlquilado.getFamilia().setDiasAlquiler(diasAlquilados);

            //resta su cantidad de existencia
            equipoAlquilado.setCantidadExistencia(equipoAlquilado.getCantidadExistencia() - 1);

            //Obtuve las fechas sus dias y a estos dias los reste para obtener los dias que
            // tuvo alquilado el equipo y esto los multiplico por el costo por dia y esto lo voy sumand en la variable total
            total  += dias * equipoAlquilado.getCostoAlquilerPorDia();

            equipoService.crearEquipo(equipoAlquilado);

            //agregando a la vista
            listaEquiposAlquilados.add(equipoAlquilado);
        }

        Cliente clienteQueAlquila = clienteService.encontrarClientePorId(idCliente);

        Alquiler alquilerToCreate = new Alquiler(fecha, fechaEntrega, clienteQueAlquila, listaEquiposAlquilados,total);

        alquilerService.crearAlquiler(alquilerToCreate);

        return "redirect:/alquiler/";
    }


    @RequestMapping( value = "/mostrar")
    public String mostrarEquiposAlquilados(Model model, Locale locale, @RequestParam(name = "id") long id){

        Alquiler alquilerToShow = alquilerService.encontrarAlquilerPorId(id);

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

        // envio los equipos que estan almacenado en el alquiler mediante la lista equipos
        model.addAttribute("equipos", alquilerToShow.getEquipos());

        return "/freemarker/mostrarequiposalquilados";
    }


    @RequestMapping("/borrar")
    public String eliminarAlquiler(@RequestParam(name = "id") long id) {

        alquilerService.eliminarAlquiler(id);

        return "redirect:/alquiler/";
    }
}
