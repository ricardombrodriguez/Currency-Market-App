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

@Entity
@Table(name = "Extension")
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private String path;

    @ManyToOne
    @JoinColumn(name = "User.id")
    private User user;

    @ManyToMany(mappedBy = "extensions")
    private List<Portfolio> portfolios = new ArrayList<>();

    public Extension(){}

    public Extension(String path) {
        this.path = path;
    }
    
    
    public int getId() {
        return id;
    }
    
    @Column(name = "user", nullable = false)
    public User getUser() {
        return this.user;
    }
    public void setUser(User new_user) {
        this.user = new_user;
    }
    
    @Column(name = "path", nullable = false)
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