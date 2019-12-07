package webavanzada_practica1.repositorios;;
import webavanzada_practica1.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Usuario findUsuarioById(long id);

    Usuario findByUsername(String username);
}
