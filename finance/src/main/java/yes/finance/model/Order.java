package yes.finance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
  
    private float quantity;
    private float order_value;
    private Timestamp created_at;

    @OneToMany(mappedBy = "origin_order")
    private List<Transaction> origin_orders = new ArrayList<>();

    @OneToMany(mappedBy = "destiny_order")
    private List<Transaction> destiny_orders = new ArrayList<>();  
   
    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "market_id")
    private Market market;

    public Order(){}

    public Order(float quantity, float order_value) {
        this.quantity = quantity;
        this.order_value = order_value;
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

    
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

    @Column(name = "order_value", nullable = false)
    public float getOrder_value() {
        return order_value;
    }

    public void setOrder_value(float order_value) {
        this.order_value = order_value;
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

    public int getPortfolioId() {
        return this.portfolio.getId();
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public int getMarketId() {
        return this.market.getId();
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", order_value='" + getOrder_value() + "'" +
            ", created_at='" + getCreated_at() + "'" +
            ", origin_orders='" + getOrigin_orders() + "'" +
            ", destiny_orders='" + getDestiny_orders() + "'" +
            ", portfolio='" + getPortfolio() + "'" +
            ", market='" + getMarket() + "'" +
            "}";
    }


}
