package webavanzada_practica1.servicios;
import webavanzada_practica1.entidades.Rol;
import webavanzada_practica1.entidades.Usuario;
import webavanzada_practica1.repositorios.RolRepositorio;
import webavanzada_practica1.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private RolRepositorio rolRepo;


    @Transactional
    public void crearUsuario(Usuario usuario){

        usuarioRepo.save(usuario);
    }


    public List<Usuario> listarUsuarios(){

        return usuarioRepo.findAll();
    }


    public Usuario encontrarUsuarioPorId(long id){

        return usuarioRepo.findUsuarioById(id);
    }


    public void eliminarUsuario(long id){

        Usuario usuarioToDelete = usuarioRepo.findUsuarioById(id);

        usuarioRepo.delete(usuarioToDelete);
    }


    public void crearRol(Rol rol){

        rolRepo.save(rol);
    }


    public List<Rol> listarRoles(){

        return rolRepo.findAll();
    }


    public Rol encontrarRolPorId(long id){

        return rolRepo.findRolById(id);
    }
}
