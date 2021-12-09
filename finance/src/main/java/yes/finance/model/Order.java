package yes.finance.model;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "Order")
public class Order {

    private int id;
    private float quantity;
    private float value;
    private Timestamp created_at;

    @OneToMany(mappedBy = "Order")
    private List<Transaction> origin_orders = new ArrayList<>();

    @OneToMany(mappedBy = "Order")
    private List<Transaction> destiny_orders = new ArrayList<>();  
   
    @ManyToOne
    @JoinColumn(name = "Portfolio.id")
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "Market.id")
    private Market market;

    public Order() {
    }

    public Order(float quantity, float value) {
        this.quantity = quantity;
        this.value = value;
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @Column(name = "quantity", nullable = false)
    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    @Column(name = "value", nullable = false)
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Column(name = "created_at", nullable = false)
    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public List<Transaction> getOrigin_orders() {
        return this.origin_orders;
    }

    public void setOrigin_orders(List<Transaction> origin_orders) {
        this.origin_orders = origin_orders;
    }

    public List<Transaction> getDestiny_orders() {
        return this.destiny_orders;
    }

    public void setDestiny_orders(List<Transaction> destiny_orders) {
        this.destiny_orders = destiny_orders;
    }

    public Portfolio getPortfolio() {
        return this.portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Market getMarket() {
        return this.market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", value='" + getValue() + "'" +
            ", created_at='" + getCreated_at() + "'" +
            ", origin_orders='" + getOrigin_orders() + "'" +
            ", destiny_orders='" + getDestiny_orders() + "'" +
            ", portfolio='" + getPortfolio() + "'" +
            ", market='" + getMarket() + "'" +
            "}";
    }


}
