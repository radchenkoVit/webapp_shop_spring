package system.model;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
public class Role {

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
