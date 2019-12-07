package webavanzada_practica1.entidades;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Familia implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private String nombre;
    private boolean subFamilia;
    //veri
    private int promedio;

    // La familia se asocia consigo misma
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Familia familia;

    @ElementCollection
    private List<Integer> diasAlquiler;


    public Familia() {
    }

    public Familia(String nombre, boolean subFamilia) {
        this.nombre = nombre;
        this.subFamilia = subFamilia;
        this.diasAlquiler = new ArrayList<>();
    }

    public Familia(String nombre, boolean subFamilia, Familia familia) {
        this.nombre = nombre;
        this.subFamilia = subFamilia;

        if (subFamilia) {
            this.familia = familia;
        }
    }


    // En este metodo calculo el promedio de alquiler dependiendo de los dias que fue alquilado el equipo
    public int getPromedio(){
        if(this.diasAlquiler.size() == 0){
            return 0;
        }

        int suma = 0;
        for(int dias: this.diasAlquiler){
            suma += dias;
        }

        return suma/this.diasAlquiler.size();
    }


    public void setPromedio(int promedio) {
        this.promedio = promedio;
    }

    public List<Integer> getDiasAlquiler() {
        return diasAlquiler;
    }

    public void setDiasAlquiler(List<Integer> diasAlquiler) {
        this.diasAlquiler = diasAlquiler;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSubFamilia() {
        return subFamilia;
    }

    public void setSubFamilia(boolean subFamilia) {
        this.subFamilia = subFamilia;
    }

    public Familia getFamiliaPadre() {
        return familia;
    }

    public void setFamiliaPadre(Familia familiaPadre) {
        this.familia = familiaPadre;
    }
}

