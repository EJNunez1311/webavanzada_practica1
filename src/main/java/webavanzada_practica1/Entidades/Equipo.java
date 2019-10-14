package webavanzada_practica1.Entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Equipo implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String nombre;
    private String marca;
    private String imagenEquipo;
    private int cantidadExistencia;
    private int costoAlquilerporDia;

    //Relacion 1 a Mucho con Familia y SubFamilia

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Familia familia;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Familia subfamilia;

    public Equipo(String nombre, String marca, String imagenEquipo, int cantidadExistencia, int costoAlquilerporDia, Familia familia, Familia subfamilia) {
        this.nombre = nombre;
        this.marca = marca;
        this.imagenEquipo = imagenEquipo;
        this.cantidadExistencia = cantidadExistencia;
        this.costoAlquilerporDia = costoAlquilerporDia;
        this.familia = familia;
        this.subfamilia = subfamilia;
    }

    public Equipo(){}

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getImagenEquipo() {
        return imagenEquipo;
    }

    public void setImagenEquipo(String imagenEquipo) {
        this.imagenEquipo = imagenEquipo;
    }

    public int getCantidadExistencia() {
        return cantidadExistencia;
    }

    public void setCantidadExistencia(int cantidadExistencia) {
        this.cantidadExistencia = cantidadExistencia;
    }

    public int getCostoAlquilerporDia() {
        return costoAlquilerporDia;
    }

    public void setCostoAlquilerporDia(int costoAlquilerporDia) {
        this.costoAlquilerporDia = costoAlquilerporDia;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Familia getSubfamilia() {
        return subfamilia;
    }

    public void setSubfamilia(Familia subfamilia) {
        this.subfamilia = subfamilia;
    }
}
