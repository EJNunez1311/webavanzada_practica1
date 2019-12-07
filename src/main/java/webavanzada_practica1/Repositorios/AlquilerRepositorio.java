package webavanzada_practica1.repositorios;
import webavanzada_practica1.entidades.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// se usan las funciones del jpa para almacenar o listar los datos, debe ser una interfaz
// y debe extender de JpaRepository y dentro de este se especifican la entidad con la que se va a trabajar
// seguido del tipo de dato con el que esta especificado el primary key de ese modelo

@Repository
public interface AlquilerRepositorio extends JpaRepository<Alquiler, Long> {


    Alquiler findAlquilerById(long id);
}
