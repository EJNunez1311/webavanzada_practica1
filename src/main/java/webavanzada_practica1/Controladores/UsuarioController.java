package webavanzada_practica1.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import webavanzada_practica1.Entidades.Cliente;
import webavanzada_practica1.Entidades.Familia;
import webavanzada_practica1.Entidades.Rol;
import webavanzada_practica1.Entidades.Usuario;
import webavanzada_practica1.Servicios.ClienteService;
import webavanzada_practica1.Servicios.EquipoService;
import webavanzada_practica1.Servicios.FamiliaService;
import webavanzada_practica1.Servicios.UsuarioService;

import java.security.Principal;
import java.util.*;

    @Controller
    @RequestMapping("/usuario")
    public class UsuarioController {

        @Autowired
        private UsuarioService usuarioService;

        @Autowired
        private ClienteService clienteService;

        @Autowired
        private EquipoService equipoService;

        @Autowired
        private FamiliaService familiaService;

        @Autowired
        private MessageSource messageSource;

        private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //Creeo cliente, familia, aquiler por defecto cada vez que reinicio la app
        @RequestMapping("/default")
        public String defaultCreate(Model model){

            Cliente clientePorDefecto = new Cliente("Johanna","Rodriguez","054-0148526-7","Moca","829-785-9656","foto.jpg");
            Cliente clientePorDefecto2 = new Cliente("Edgar","Nunez","402-2335865-9","Calle 9 Gurabo","809-962-5658","foto1.jpg");
            clienteService.crearCliente(clientePorDefecto);
            clienteService.crearCliente(clientePorDefecto2);

            Familia familiaPorDefecto = new Familia("Electrodomestico",false);

            //Agrego dias de alquiler por defecto  las familias por defecto
            List<Integer> diasAlquiladosPorDefault = new ArrayList<>();
            diasAlquiladosPorDefault.add(5);
            diasAlquiladosPorDefault.add(4);
            diasAlquiladosPorDefault.add(7);

            familiaPorDefecto.setDiasAlquiler(diasAlquiladosPorDefault);

            familiaPorDefecto.setPromedio(familiaPorDefecto.getPromedio());

            familiaService.crearFamilia(familiaPorDefecto);

            Familia subFamiliaPorDefecto = new Familia("Televisores",true,familiaPorDefecto);

            subFamiliaPorDefecto.setDiasAlquiler(diasAlquiladosPorDefault);
            subFamiliaPorDefecto.setPromedio(subFamiliaPorDefecto.getPromedio());

            familiaService.crearFamilia(subFamiliaPorDefecto);

            Familia subFamiliaPorDefecto2 = new Familia("Neveras",true,familiaPorDefecto);

            subFamiliaPorDefecto2.setDiasAlquiler(diasAlquiladosPorDefault);
            subFamiliaPorDefecto2.setPromedio(subFamiliaPorDefecto.getPromedio());

            familiaService.crearFamilia(subFamiliaPorDefecto2);

            Equipo equipoPorDefecto = new Equipo("TV Smart 55","Samsung","tv.jpg",7,250,familiaPorDefecto,subFamiliaPorDefecto);

            equipoService.crearEquipo(equipoPorDefecto);

            return "redirect:/usuario/";
        }


        @RequestMapping("/")
        public String index(Model model, Principal principal, Locale locale){

            model.addAttribute("titulo", "EJ CXA");
            model.addAttribute("clientesi18n", messageSource.getMessage("clientesi18n", null, locale));
            model.addAttribute("equiposi18n", messageSource.getMessage("equiposi18n", null, locale));
            model.addAttribute("negocioi18n", messageSource.getMessage("negocioi18n", null, locale));
            model.addAttribute("alquileri18n", messageSource.getMessage("alquileri18n", null, locale));
            model.addAttribute("familiasi18n", messageSource.getMessage("familiasi18n", null, locale));
            model.addAttribute("administradori18n", messageSource.getMessage("administradori18n", null, locale));
            model.addAttribute("usuariosi18n", messageSource.getMessage("usuariosi18n", null, locale));
            model.addAttribute("opcionei18n", messageSource.getMessage("opcionei18n", null, locale));
            model.addAttribute("listausuarioi18n", messageSource.getMessage("listausuarioi18n", null, locale));
            model.addAttribute("agregarusuarioi18n", messageSource.getMessage("agregarusuarioi18n", null, locale));
            model.addAttribute("nombreusuarioi18n", messageSource.getMessage("nombreusuarioi18n", null, locale));
            model.addAttribute("activousuarioi18n", messageSource.getMessage("activousuarioi18n", null, locale));

            model.addAttribute("usuarios",usuarioService.listarUsuarios());

            model.addAttribute("usuario", principal.getName());

            return "/freemarker/usuario";
        }


        @RequestMapping("/creacion")
        public String creacionUsuario(Model model, Locale locale){

            model.addAttribute("titulo", "EJ CXA");
            model.addAttribute("agregarusuarioi18n", messageSource.getMessage("agregarusuarioi18n", null, locale));
            model.addAttribute("nombreusuarioi18n", messageSource.getMessage("nombreusuarioi18n", null, locale));
            model.addAttribute("passwordusuarioi18n", messageSource.getMessage("passwordusuarioi18n", null, locale));
            model.addAttribute("rolusuarioi18n", messageSource.getMessage("rolusuarioi18n", null, locale));
            model.addAttribute("botonguardari18n", messageSource.getMessage("botonguardari18n", null, locale));
            model.addAttribute("botoncancelari18n", messageSource.getMessage("botoncancelari18n", null, locale));

            model.addAttribute("roles", usuarioService.listarRoles());

            return "/freemarker/crearusuario";
        }


        @RequestMapping( value = "/crear", method = RequestMethod.POST)
        public String crearUsuario(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, @RequestParam(name = "idRoles") long idRoles ){

            // Mando el id para que me busque el rol creado
            Rol rolCreated = usuarioService.encontrarRolPorId(idRoles);

            Usuario usuarioToCreate = new Usuario();
            usuarioToCreate.setUsername(username);

            usuarioToCreate.setRoles(new HashSet<>(Arrays.asList(rolCreated)));

            usuarioToCreate.setPassword(passwordEncoder.encode(password));

            usuarioToCreate.setActive(true);


            usuarioService.crearUsuario(usuarioToCreate);

            return "redirect:/usuario/";
        }

        @RequestMapping("/borrar")
        public String eliminarUsuario(@RequestParam(name = "id") long id){

            usuarioService.eliminarUsuario(id);

            return "redirect:/usuario/";
        }
    }
}
