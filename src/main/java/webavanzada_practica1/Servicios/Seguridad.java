package webavanzada_practica1.Servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class Seguridad implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usarioRepo;
    private
}
