package yes.finance.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Extension")
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "path", nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "extensions")
    @JsonIgnore
    private List<Portfolio> portfolios = new ArrayList<>();

    public Extension() {
    }

    public Extension(User user, String name, String description, String path) {
        this.user = user;
        this.name = name;
        this.path = path;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User new_user) {
        this.user = new_user;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String new_path) {
        this.path = new_path;
    }

    public List<Portfolio> getPortfolios() {
        return this.portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", path='" + getPath() + "'" +
                ", user='" + getUser() + "'" +
                ", portfolios='" + getPortfolios() + "'" +
                "}";
    }

}