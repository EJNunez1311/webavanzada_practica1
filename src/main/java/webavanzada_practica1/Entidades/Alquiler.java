package webavanzada_practica1.entidades;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Alquiler implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private Date fecha;
    private Date fechaEntrega;
    private int total;

    // Relacion uno a mucho
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Cliente cliente;

    // Relacion mucho a mucho
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Equipo> equipos;


    public Alquiler(Date fecha, Date fechaEntrega, Cliente cliente, List<Equipo> equipos, int total) {
        this.fecha = fecha;
        this.fechaEntrega = fechaEntrega;
        this.cliente = cliente;
        this.equipos = equipos;
        this.total = total;
    }

    public Alquiler() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
