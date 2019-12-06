package webavanzada_practica1.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import webavanzada_practica1.Entidades.Alquiler;
import webavanzada_practica1.Entidades.Cliente;
import webavanzada_practica1.Entidades.Equipo;
import webavanzada_practica1.Servicios.AlquilerService;
import webavanzada_practica1.Servicios.ClienteService;
import webavanzada_practica1.Servicios.EquipoService;


import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/equipo")
public class EquipoController {

    // En cada controlador preparo el servicio que le corresponde a cada controlador para poder trabajar con las funciones
    // del crud ya especificadas en los servicios, tener en cuenta que el services solo se puede trabajar dentro de funciones
    @Autowired
    private AlquilerService alquilerService;

    // Debo mandarle al alquiler los clientes y equipos ya creados, por lo tanto instanciare equiposervices y clienteservices
    @Autowired
    private EquipoService equipoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MessageSource messageSource;


    @RequestMapping("/")
    public String index(Model model, Principal principal, Locale locale) {

        //Indicando el modelo que será pasado a la vista.
        model.addAttribute("titulo", "Electrodomesticos CXA");

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

        // Comento esto para no tener que estar utilizando el login siempre que toy jarto
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

    // error de fecha solucionado, para solucionarlo utilice @DatimeFormat, ya que spring me da error a la hora de mandar
    // fechas por el controlador, y con este metodo es posible solucionar este problema, en pattern pongo el formate que mi fecha tendra
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

        // Aqui me encargo de restar la cantidad existencia de los distintos equipos que recibire
        for (Long equipo : idEquipos) {

            // primero encuentro el equipo
            Equipo equipoAlquilado = equipoService.encontrarEquipoPorId(equipo);

            //Aqui le estoy mandando a la familia de este equipo los dias que este equipo duro alquilado
            // Y como esto esta dentro de un foreach esto se ira agregando a las demas familias
            // de los demas equipos
            equipoAlquilado.getFamilia().setDiasAlquiler(diasAlquilados);

            //Aqui me encargo de restar su cantidad de existencia
            equipoAlquilado.setCantidadExistencia(equipoAlquilado.getCantidadExistencia() - 1);

            //Aqui basicamente obtuve de las fechas sus dias y a estos dias los reste para obtener los dias que
            // tuvo alquilador el equipo y esto los multiplico por el costo por dia y esto lo voy sumand en la variable total
            // Al final funciona a la perfeccion para resolver esto siempre es recomendable, imprimir por consola los resultados
            // para ver asi si el error esta en los calculos
            total  += dias * equipoAlquilado.getCostoAlquilerPorDia();

            //Aqui lo guardo ya que crear sirve tanto para crear desde 0 como para guardar los cambios
            equipoService.crearEquipo(equipoAlquilado);

            //Y finalmente lo agrego a la lista
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

        //Aqui mando los equipos que estan almacenado en el alquiler mediante la lista equipos
        model.addAttribute("equipos", alquilerToShow.getEquipos());

        return "/freemarker/mostrarequiposalquilados";
    }


    @RequestMapping("/borrar")
    public String eliminarAlquiler(@RequestParam(name = "id") long id) {

        // Aqui elimino el cliente mandandole el id obtenido mediante la url en el requesparam
        alquilerService.eliminarAlquiler(id);

        return "redirect:/alquiler/";
    }
}
