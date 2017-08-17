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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "APPLICATIONS_CATEGORIES",
            joinColumns = @JoinColumn(name = "APPLICATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    private Set<Category> categories;

    @Column(name = "DOWNLOADED_TIMES")
    private int downloadedTimes;

    @ManyToMany(mappedBy = "applications")
    private List<User> users;


}
