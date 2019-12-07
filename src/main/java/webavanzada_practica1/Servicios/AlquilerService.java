package webavanzada_practica1.servicios;
import webavanzada_practica1.entidades.Alquiler;
import webavanzada_practica1.repositorios.AlquilerRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class AlquilerService {

    // Por cada repositorio se debe de crear un servicio e implementar cada repositorio en su respectivo servicio
    @Autowired
    private AlquilerRepositorio alquilerRepo;

    // Aqui manejare las funciones que utilizare en la vista para el manejo de datos, las funciones que especificare

    @Transactional
    public void crearAlquiler(Alquiler alquiler){

         alquilerRepo.save(alquiler);
    }


    public List<Alquiler> listarAlquileres(){
        return alquilerRepo.findAll();
    }


    public Alquiler encontrarAlquilerPorId(long id){

        return alquilerRepo.findAlquilerById(id);

    }


    public void eliminarAlquiler(long id){

        Alquiler alquilerToDelete = alquilerRepo.findAlquilerById(id);

        alquilerRepo.delete(alquilerToDelete);
    }
}
