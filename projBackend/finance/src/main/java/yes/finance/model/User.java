package yes.finance.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String username;
    private String fullname;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Extension> extensions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "UserPortfolio", 
        joinColumns = @JoinColumn(name = "user.id"), 
        inverseJoinColumns = @JoinColumn(name = "portfolio.id"))
    private List<Portfolio> portfolios = new ArrayList<>();


    public User() {}

    public User(String username, String fullname, String email, String password) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }

    
    public Integer getId() {
        return id;
    }

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "fullname", nullable = false)
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Extension> getExtensions() {
        return this.extensions;
    }

    public void setExtensions(List<Extension> extensions) {
        this.extensions = extensions;
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
            ", username='" + getUsername() + "'" +
            ", fullname='" + getFullname() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", extensions='" + getExtensions() + "'" +
            ", portfolios='" + getPortfolios() + "'" +
            "}";
    }


}
