package webavanzada_practica1.repositorios;;
import webavanzada_practica1.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {


    Cliente findClienteById(long id);
}
