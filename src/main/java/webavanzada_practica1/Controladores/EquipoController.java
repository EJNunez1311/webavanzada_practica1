package webavanzada_practica1.controladores;
import webavanzada_practica1.entidades.Equipo;
import webavanzada_practica1.entidades.Familia;
import webavanzada_practica1.servicios.AlquilerService;
import webavanzada_practica1.servicios.EquipoService;
import webavanzada_practica1.servicios.FamiliaService;
import webavanzada_practica1.servicios.SubirArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.security.Principal;
import java.util.Locale;

@Controller
@RequestMapping("/equipo")
public class EquipoController {

    @Autowired
    private AlquilerService alquilerService;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private FamiliaService familiaService;

    @Autowired
    private SubirArchivoService subirArchivoService;

    @Autowired
    private MessageSource messageSource;

    //creando directorio para guardar
    public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";


    @RequestMapping("/grafica")
    public String pruebaGrafica(Model model, Locale locale){

        model.addAttribute("titulo", "E&J CXA");
        model.addAttribute("clientesi18n", messageSource.getMessage("clientesi18n", null, locale));
        model.addAttribute("equiposi18n", messageSource.getMessage("equiposi18n", null, locale));
        model.addAttribute("negocioi18n", messageSource.getMessage("negocioi18n", null, locale));
        model.addAttribute("alquileri18n", messageSource.getMessage("alquileri18n", null, locale));
        model.addAttribute("familiasi18n", messageSource.getMessage("familiasi18n", null, locale));
        model.addAttribute("administradori18n", messageSource.getMessage("administradori18n", null, locale));
        model.addAttribute("usuariosi18n", messageSource.getMessage("usuariosi18n", null, locale));

        model.addAttribute("alquileres", alquilerService.listarAlquileres());

        model.addAttribute("familias", familiaService.listarFamilias());

        return "/freemarker/graficaalquiler";
    }


    @RequestMapping("/")
    public String index(Model model, Principal principal, Locale locale){

        model.addAttribute("clientesi18n", messageSource.getMessage("clientesi18n", null, locale));
        model.addAttribute("equiposi18n", messageSource.getMessage("equiposi18n", null, locale));
        model.addAttribute("negocioi18n", messageSource.getMessage("negocioi18n", null, locale));
        model.addAttribute("alquileri18n", messageSource.getMessage("alquileri18n", null, locale));
        model.addAttribute("familiasi18n", messageSource.getMessage("familiasi18n", null, locale));
        model.addAttribute("administradori18n", messageSource.getMessage("administradori18n", null, locale));
        model.addAttribute("usuariosi18n", messageSource.getMessage("usuariosi18n", null, locale));
        model.addAttribute("listaequipoi18n", messageSource.getMessage("listaequipoi18n", null, locale));
        model.addAttribute("graficaequipoi18n", messageSource.getMessage("graficaequipoi18n", null, locale));
        model.addAttribute("agregarequipoi18n", messageSource.getMessage("agregarequipoi18n", null, locale));
        model.addAttribute("nombreequipoi18n", messageSource.getMessage("nombreequipoi18n", null, locale));
        model.addAttribute("marcaequipoi18n", messageSource.getMessage("marcaequipoi18n", null, locale));
        model.addAttribute("imagenequipoi18n", messageSource.getMessage("imagenequipoi18n", null, locale));
        model.addAttribute("cantidadequipoi18n", messageSource.getMessage("cantidadequipoi18n", null, locale));
        model.addAttribute("costoequipoi18n", messageSource.getMessage("costoequipoi18n", null, locale));
        model.addAttribute("familiaequipoindexi18n", messageSource.getMessage("familiaequipoindexi18n", null, locale));
        model.addAttribute("subfamiliaequipoindexi18n", messageSource.getMessage("subfamiliaequipoindexi18n", null, locale));
        model.addAttribute("opcionei18n", messageSource.getMessage("opcionei18n", null, locale));
        model.addAttribute("titulo", "E&J CXA");

        model.addAttribute("equipos", equipoService.listarEquipos());

        model.addAttribute("usuario", principal.getName());

        return "/freemarker/equipo";
    }


    @RequestMapping("/creacion")
    public String creacionEquipo(Model model, Locale locale){

        model.addAttribute("agregarequipoi18n", messageSource.getMessage("agregarequipoi18n", null, locale));
        model.addAttribute("nombreequipoi18n", messageSource.getMessage("nombreequipoi18n", null, locale));
        model.addAttribute("marcaequipoi18n", messageSource.getMessage("marcaequipoi18n", null, locale));
        model.addAttribute("imagenequipoi18n", messageSource.getMessage("imagenequipoi18n", null, locale));
        model.addAttribute("cantidadequipoi18n", messageSource.getMessage("cantidadequipoi18n", null, locale));
        model.addAttribute("costoequipoi18n", messageSource.getMessage("costoequipoi18n", null, locale));
        model.addAttribute("familiaequipoi18n", messageSource.getMessage("familiaequipoi18n", null, locale));
        model.addAttribute("subfamiliaequipoi18n", messageSource.getMessage("subfamiliaequipoi18n", null, locale));
        model.addAttribute("botonguardari18n", messageSource.getMessage("botonguardari18n", null, locale));
        model.addAttribute("botoncancelari18n", messageSource.getMessage("botoncancelari18n", null, locale));
        model.addAttribute("titulo", "E&J CXA");

        model.addAttribute("familias", familiaService.listarFamilias());

        return "/freemarker/crearequipo";
    }


    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public String crearEquipo(@RequestParam(name = "files") MultipartFile[] files, @RequestParam(name = "nombre") String nombre, @RequestParam(name = "marca") String marca, @RequestParam(name = "cantidadExistencia") int cantidadExistencia, @RequestParam(name = "costoAlquilerPorDia") int costoAlquilerPorDia, @RequestParam(name = "familia", required = false) Long idFamilia, @RequestParam(name = "subFamilia", required = false) Long idSubFamilia ){

        //Manejando la imagen para conseguir su nombre y almacenarla
        String nombreDeLaFoto = subirArchivoService.almacenarAndDepurarImagen(files,uploadDirectory);

        Familia familia = familiaService.encontrarFamiliaPorId(idFamilia);
        Familia subFamiliaToFind = familiaService.encontrarFamiliaPorId(idSubFamilia);

        //Ahora mismo lo unico que me falla de equipo es lo de obtener la subfamilia mediante el formulario
        Equipo equipoToCreate = new Equipo(nombre,marca,nombreDeLaFoto,cantidadExistencia,costoAlquilerPorDia,familia,subFamiliaToFind);

        equipoService.crearEquipo(equipoToCreate);

        return "redirect:/equipo/";
    }


    @RequestMapping("/edicion")
    public String edicionEquipo(Model model,Locale locale,  @RequestParam(name = "id") long id ){

        model.addAttribute("titulo", "E&J CXA");
        model.addAttribute("editarequipoi18n", messageSource.getMessage("editarequipoi18n", null, locale));
        model.addAttribute("nombreequipoi18n", messageSource.getMessage("nombreequipoi18n", null, locale));
        model.addAttribute("marcaequipoi18n", messageSource.getMessage("marcaequipoi18n", null, locale));
        model.addAttribute("imagenequipoi18n", messageSource.getMessage("imagenequipoi18n", null, locale));
        model.addAttribute("cantidadequipoi18n", messageSource.getMessage("cantidadequipoi18n", null, locale));
        model.addAttribute("costoequipoi18n", messageSource.getMessage("costoequipoi18n", null, locale));
        model.addAttribute("familiaequipoi18n", messageSource.getMessage("familiaequipoi18n", null, locale));
        model.addAttribute("subfamiliaequipoi18n", messageSource.getMessage("subfamiliaequipoi18n", null, locale));
        model.addAttribute("botonguardari18n", messageSource.getMessage("botonguardari18n", null, locale));
        model.addAttribute("botoncancelari18n", messageSource.getMessage("botoncancelari18n", null, locale));

        Equipo equipoToEdit = equipoService.encontrarEquipoPorId(id);

        model.addAttribute("equipo",equipoToEdit);
        model.addAttribute("familias", familiaService.listarFamilias());

        return "/freemarker/editarequipo";
    }


    @RequestMapping("/editar")
    public String editarEquipo(@RequestParam(name = "files") MultipartFile[] files, @RequestParam(name = "id") long id, @RequestParam(name = "nombre") String nombre, @RequestParam(name = "marca") String marca, @RequestParam(name = "cantidadExistencia") int cantidadExistencia,@RequestParam(name = "costoAlquilerPorDia") int costoAlquilerPorDia, @RequestParam(name = "familia", required = false) Long idFamilia, @RequestParam(name = "subFamilia", required = false) Long subFamilia ){

        String imagenEquipo = subirArchivoService.almacenarAndDepurarImagen(files,uploadDirectory);

        Equipo equipoToEdit = equipoService.encontrarEquipoPorId(id);

        Familia familiaToEdit = familiaService.encontrarFamiliaPorId(idFamilia);

        Familia subFamiliaToEdit = familiaService.encontrarFamiliaPorId(idFamilia);

        equipoToEdit.setNombre(nombre);
        equipoToEdit.setMarca(marca);
        equipoToEdit.setCantidadExistencia(cantidadExistencia);
        equipoToEdit.setImagenEquipo(imagenEquipo);
        equipoToEdit.setCostoAlquilerPorDia(costoAlquilerPorDia);

        //Mandando las familias y subfamilias
        equipoToEdit.setFamilia(familiaToEdit);
        equipoToEdit.setSubFamilia(subFamiliaToEdit);

        equipoService.crearEquipo(equipoToEdit);

        return "redirect:/equipo/";
    }


    @RequestMapping("/borrar")
    public String eliminarEquipo(@RequestParam(name = "id") long id){

        equipoService.eliminarEquipo(id);

        return "redirect:/equipo/";
    }
}
