package webavanzada_practica1.Repositorios;
import webavanzada_practica1.Entidades.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FamiliaRepositorio extends JpaRepository<Familia, Long> {

    Familia findFamiliaById(long id);
}
