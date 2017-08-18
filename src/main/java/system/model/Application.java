package system.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "APPLICATIONS")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //TODO: check what strategy should be
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "APPLICATIONS_CATEGORIES",
            joinColumns = @JoinColumn(name = "APPLICATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    private List<Category> categories;

    @Column(name = "DOWNLOADED_TIMES")
    private int downloadedTimes;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "USERS_APPLICATIONS",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "APPLICATION_ID")
    )
    private Set<User> users;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public int getDownloadedTimes() {
        return downloadedTimes;
    }

    public void setDownloadedTimes(int downloadedTimes) {
        this.downloadedTimes = downloadedTimes;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
