package webavanzada_practica1.Repositorios;
import webavanzada_practica1.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {


    Usuario findUsuarioById(long id);

    Usuario findByUsername(String username);
}
