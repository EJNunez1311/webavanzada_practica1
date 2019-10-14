package webavanzada_practica1.Entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Familia implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String nombre;
    private boolean subFamilia;

    private int promedio;

    //La Familia se asocia
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Familia familia;

    @ElementCollection
    private List<Integer> diasAlquiler;

    public Familia(){

    }

    public Familia(String nombre, boolean subFamilia, Familia familia, List<Integer> diasAlquiler) {
        this.nombre = nombre;
        this.subFamilia = subFamilia;
        if(subFamilia){
            this.familia = familia;
        }
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

    public void setPromedio(int promedio) {
        this.promedio = promedio;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public List<Integer> getDiasAlquiler() {
        return diasAlquiler;
    }

    public void setDiasAlquiler(List<Integer> diasAlquiler) {
        this.diasAlquiler = diasAlquiler;
    }

    //Metodo para calcular el promedio de Alquiler
    public int getPromedio(){
        if (this.diasAlquiler.size()==0){
            return 0;
        }
        int suma=0;
        for (int dias: this.diasAlquiler){
            suma += dias;
        }
        return suma/this.diasAlquiler.size();
        }
    }

