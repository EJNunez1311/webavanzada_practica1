package webavanzada_practica1.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import webavanzada_practica1.Entidades.Alquiler;
import webavanzada_practica1.Repositorios.AlquilerRepositorio;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

public class AlquilerService {

    // Por cada repositorio se debe de crear un servicio e implementar cada repositorio en su respectivo servicio
    // ya que los servicios son los encargados de manejar las reglas de negocios y por lo tanto de aqui sera que se obtendran
    // los datos para trabajar en los respectivos controladores
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
