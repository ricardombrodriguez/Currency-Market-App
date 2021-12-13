package yes.finance.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private String name;
    private String public_key;

    @ManyToMany
    @JoinTable(
        name = "PortfolioExtension", 
        joinColumns = @JoinColumn(name = "Portfolio.id"), 
        inverseJoinColumns = @JoinColumn(name = "Extension.id"))
    private List<Extension> extensions = new ArrayList<>();

    @ManyToMany(mappedBy = "UserPortfolio")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "Portfolio")
    private List<Order> orders = new ArrayList<>();


    public Portfolio() {
    }

    public Portfolio(String name, String public_key) {
        this.name = name;
        this.public_key = public_key;
    }

    
    public int getId() {
        return id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "public_key", nullable = false)
    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public List<Extension> getExtensions() {
        return this.extensions;
    }

    public void setExtensions(List<Extension> extensions) {
        this.extensions = extensions;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", public_key='" + getPublic_key() + "'" +
            ", extensions='" + getExtensions() + "'" +
            ", users='" + getUsers() + "'" +
            ", orders='" + getOrders() + "'" +
            "}";
    }


}
