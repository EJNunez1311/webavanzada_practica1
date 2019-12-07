package webavanzada_practica1.servicios;
import webavanzada_practica1.entidades.Equipo;
import webavanzada_practica1.repositorios.EquipoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepositorio equipoRepo;


    @Transactional
    public void crearEquipo(Equipo equipo){

         equipoRepo.save(equipo);
    }


    public List<Equipo> listarEquipos(){

        return equipoRepo.findAll();
    }


    public Equipo encontrarEquipoPorId(long id){
        return equipoRepo.findEquipoById(id);

    }


    public void eliminarEquipo(long id){

        Equipo equipoToDelete = equipoRepo.findEquipoById(id);
        equipoRepo.delete(equipoToDelete);
    }
}
