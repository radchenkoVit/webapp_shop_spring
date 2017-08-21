package system.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROLES")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @Column(name = "NAME")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
