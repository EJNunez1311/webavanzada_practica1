package webavanzada_practica1.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import webavanzada_practica1.Entidades.Rol;
import webavanzada_practica1.Entidades.Usuario;
import webavanzada_practica1.Repositorios.RolRepositorio;
import webavanzada_practica1.Repositorios.UsuarioRepositorio;

import javax.transaction.Transactional;
import java.util.*;

public class SeguridadService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private RolRepositorio rolRepositorio;

    //Encritando información
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Transactional
    public void crearUsuarioAdmin(){

        //Creando roles

        Rol rolUser = new Rol();
        rolUser.setRole("ROLE_USER");
        rolRepositorio.save(rolUser);

        Rol rolAdmin = new Rol("ROLE_ADMIN");
        rolRepositorio.save(rolAdmin);

        Usuario adminUser = new Usuario();
        adminUser.setUsername("admin");
        adminUser.setActive(true);
        adminUser.setRoles(new HashSet<>(Arrays.asList(rolAdmin)));
        adminUser.setPassword(passwordEncoder.encode("123456"));

        usuarioRepo.save(adminUser);
    }

    // ES necesario implementar este metodo cuando se implementa user details service
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario user = usuarioRepo.findByUsername(username);

        // El profe lo usa para recorrer roles
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Rol role : user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);

        // UserDetails userDetails = new User(usuario.getUsername(),usuario.getPassword(),roles);
        //Retornando el usuario, rol y pass
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), user.isActive(), true, true, true, grantedAuthorities);
    }

}
