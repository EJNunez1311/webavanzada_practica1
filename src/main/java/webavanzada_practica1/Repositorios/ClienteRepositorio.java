package webavanzada_practica1.Repositorios;
import webavanzada_practica1.Entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    Cliente findClienteById(long id);
}
