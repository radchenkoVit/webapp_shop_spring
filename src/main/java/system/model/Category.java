package system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIES")
public class Category {

    @Id
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;
}
