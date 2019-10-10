package webavanzada_practica1.Entidades;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Rol implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private String role;

    public Rol(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Rol(String role) {
        this.role = role;
    }
}
