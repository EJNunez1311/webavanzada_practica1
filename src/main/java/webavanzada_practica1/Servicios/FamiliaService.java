package webavanzada_practica1.servicios;
import webavanzada_practica1.entidades.Familia;
import webavanzada_practica1.repositorios.FamiliaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class FamiliaService {

    @Autowired
    private FamiliaRepositorio familiaRepo;


    @Transactional
    public void crearFamilia(Familia familia){

         familiaRepo.save(familia);
    }


    public List<Familia> listarFamilias(){

        return familiaRepo.findAll();
    }


    public Familia encontrarFamiliaPorId(long id){

        return familiaRepo.findFamiliaById(id);

    }


    public void eliminarFamilia(long id){

        Familia familiaToDelete = familiaRepo.findFamiliaById(id);
        familiaRepo.delete(familiaToDelete);
    }
}
