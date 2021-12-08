package yes.finance.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Portfolio")
public class Portfolio {

    private int id;
    private String name;
    private String public_key;

    public Portfolio() {
    }

    public Portfolio(String name, String public_key) {
        this.name = name;
        this.public_key = public_key;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @OneToMany(mappedBy = "Portfolio")
    private List<UserPortfolio> user_portfolios = new ArrayList<>();

    @OneToMany(mappedBy = "Portfolio")
    private List<PortfolioExtensions> extensions_portfolios = new ArrayList<>();

    @OneToMany(mappedBy = "Portfolio")
    private List<Order> portfolios = new ArrayList<>();

}