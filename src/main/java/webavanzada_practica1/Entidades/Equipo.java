package webavanzada_practica1.entidades;
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
    private int costoAlquilerPorDia;


    // Relaciones 1 a mucho con familia y subFamilia.
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Familia familia;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Familia subFamilia;


    public Equipo(String nombre, String marca, String imagenEquipo, int cantidadExistencia, int costoAlquilerPorDia, Familia familia, Familia subFamilia) {
        this.nombre = nombre;
        this.marca = marca;
        this.imagenEquipo = imagenEquipo;
        this.cantidadExistencia = cantidadExistencia;
        this.costoAlquilerPorDia = costoAlquilerPorDia;
        this.familia = familia;
        this.subFamilia = subFamilia;
    }

    public Equipo() {
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

    public int getCostoAlquilerPorDia() {
        return costoAlquilerPorDia;
    }

    public void setCostoAlquilerPorDia(int costoAlquilerPorDia) {
        this.costoAlquilerPorDia = costoAlquilerPorDia;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Familia getSubFamilia() {
        return subFamilia;
    }

    public void setSubFamilia(Familia subFamilia) {
        this.subFamilia = subFamilia;
    }
}
