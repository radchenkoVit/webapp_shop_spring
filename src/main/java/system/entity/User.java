package system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "NAME", nullable = false)
    private String name;

//    @Column(name = "LASTNAME")
//    private String name;
//
    @Column(name = "PASS", nullable = false)
    private String password;
//
//    @Column(name = "EMAIL", nullable = false)
//    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USERS_APPLICATIONS",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "APPLICATION_ID")
    )
    private Set<Application> applications;

    @ManyToOne
    @JoinColumn(name = "ID", nullable = false)
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
