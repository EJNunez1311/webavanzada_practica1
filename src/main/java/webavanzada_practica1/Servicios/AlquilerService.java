package webavanzada_practica1.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import webavanzada_practica1.Entidades.Alquiler;
import webavanzada_practica1.Repositorios.AlquilerRepositorio;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

public class AlquilerService {

    // Aqui se definen las reglas de negocio toda la logica para los controladores
    @Autowired
    private AlquilerRepositorio alquilerRepo;


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
