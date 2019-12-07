package webavanzada_practica1.repositorios;
import webavanzada_practica1.entidades.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepositorio extends JpaRepository<Equipo, Long> {

    Equipo findEquipoById(long id);
}
