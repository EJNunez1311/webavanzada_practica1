package webavanzada_practica1.Repositorios;
import webavanzada_practica1.Entidades.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EquipoRepositorio extends JpaRepository<Equipo, Long> {

    Equipo findEquipoById(long id);
}
