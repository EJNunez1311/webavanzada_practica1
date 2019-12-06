package webavanzada_practica1.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import webavanzada_practica1.Entidades.Equipo;
import webavanzada_practica1.Repositorios.EquipoRepositorio;

import javax.transaction.Transactional;
import java.util.List;

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
