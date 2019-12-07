package webavanzada_practica1.repositorios;
import webavanzada_practica1.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long> {

    Rol findRolById(long id);
}
