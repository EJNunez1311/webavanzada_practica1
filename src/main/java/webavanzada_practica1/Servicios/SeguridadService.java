package webavanzada_practica1.servicios;
import webavanzada_practica1.entidades.Rol;
import webavanzada_practica1.entidades.Usuario;
import webavanzada_practica1.repositorios.RolRepositorio;
import webavanzada_practica1.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class SeguridadService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private RolRepositorio rolRepositorio;

    //Para encriptar la información
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Transactional
    public void crearUsuarioAdmin(){
        //creo los roles

        Rol rolUser = new Rol();
        rolUser.setRole("ROLE_USER");
        rolRepositorio.save(rolUser);

        Rol rolAdmin = new Rol("ROLE_ADMIN");
        rolRepositorio.save(rolAdmin);

        Usuario usuarioAdmin = new Usuario();
        usuarioAdmin.setUsername("admin");
        usuarioAdmin.setActive(true);
        usuarioAdmin.setRoles(new HashSet<>(Arrays.asList(rolAdmin)));

        // creo la passwrod, pero tambien la encripto con el password encoder
        usuarioAdmin.setPassword(passwordEncoder.encode("123456"));

        usuarioRepo.save(usuarioAdmin);
    }

    // se necesita implementar este metodo cuando se implementa user details service
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario user = usuarioRepo.findByUsername(username);

        // el profe lo usa para recorrer roles
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Rol role : user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);

        // Hay que retornar un objeto de tipo userdetails por lo tanto hacemos esto y le mandamos los datos del usuario
        // UserDetails userDetails = new User(usuario.getUsername(),usuario.getPassword(),roles);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), user.isActive(), true, true, true, grantedAuthorities);
    }
}
