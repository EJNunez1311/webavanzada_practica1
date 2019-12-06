package webavanzada_practica1.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import webavanzada_practica1.Entidades.Familia;
import webavanzada_practica1.Repositorios.FamiliaRepositorio;

import javax.transaction.Transactional;
import java.util.List;

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
