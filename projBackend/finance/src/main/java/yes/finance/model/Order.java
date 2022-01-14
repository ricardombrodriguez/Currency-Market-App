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
    private Integer id;
  
    private Float quantity;
    private Float order_value;
    private Timestamp createdAt;

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

    public Order(Float quantity, Float order_value, Portfolio portfolio, Market market) {
        this.quantity = quantity;
        this.order_value = order_value;
        this.portfolio = portfolio;
        this.market = market;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    
    public Integer getId() {
        return id;
    }

    @Column(name = "quantity", nullable = false)
    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    @Column(name = "order_value", nullable = false)
    public Float getOrder_value() {
        return order_value;
    }

    public void setOrder_value(Float order_value) {
        this.order_value = order_value;
    }

    @Column(name = "created_at", nullable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp created_at) {
        this.createdAt = created_at;
    }

    public void setOrigin_orders(List<Transaction> origin_orders) {
        this.origin_orders = origin_orders;
    }

    public void setDestiny_orders(List<Transaction> destiny_orders) {
        this.destiny_orders = destiny_orders;
    }

    public Integer getPortfolioId() {
        return this.portfolio.getId();
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Integer getMarketId() {
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
            ", created_at='" + getCreatedAt() + "'" +
            ", portfolio='" + getPortfolioId() + "'" +
            ", market='" + getMarketId() + "'" +
            "}";
    }


}
