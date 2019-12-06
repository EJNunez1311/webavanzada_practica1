package webavanzada_practica1.Repositorios;

        import org.springframework.stereotype.Repository;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
        import webavanzada_practica1.Entidades.Alquiler;

@Repository
public interface AlquilerRepositorio extends JpaRepository<Alquiler, Long> {


    Alquiler findAlquilerById(long id);
}
