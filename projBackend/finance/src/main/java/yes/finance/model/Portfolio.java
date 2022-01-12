package yes.finance.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.*;
import java.util.Random;

@Entity
@Table(name = "Portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "public_key", nullable = false)
    private String public_key;

    @ManyToMany
    @JoinTable(name = "PortfolioExtension", joinColumns = @JoinColumn(name = "Portfolio.id"), inverseJoinColumns = @JoinColumn(name = "Extension.id"))
    private List<Extension> extensions = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "portfolios")
    @JsonManagedReference
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio")
    private List<Order> orders = new ArrayList<>();

    public Portfolio() {
    }

    public Portfolio(String name) {
        this.name = name;
        Random rand = new Random();
        this.public_key = String.valueOf(rand.nextInt(1000000));
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public void addUser(User user) {
        this.users.add(user);
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
                "}";
    }

}
