package yes.finance.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Market")
public class Market {
    
    private int id;
    private double last_value;
    private double max_bid;
    private double max_sell;

    @ManyToOne
    @JoinColumn(name = "Currency.id")
    private Currency origin_currency;

    @ManyToOne
    @JoinColumn(name = "Currency.id")
    private Currency destiny_currency;

    @OneToMany
    private List<Order> orders = new ArrayList<>();

    public Market(){}

    public Market(double last_value, double max_bid, double max_sell) {
        this.last_value = last_value;
        this.max_bid = max_bid;
        this.max_sell = max_sell;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    
    @Column(name = "origin_currency", nullable = false)
    public Currency getOrigin_currency() {
        return this.origin_currency;
    }
    public void setOrigin_currency(Currency origin_currency) {
        this.origin_currency = origin_currency;
    }
    
    @Column(name = "destiny_currency", nullable = false)
    public Currency getDestiny_currency() {
        return this.destiny_currency;
    }
    public void setDestiny_currency(Currency destiny_currency) {
        this.destiny_currency = destiny_currency;
    }

    @Column(name = "last_value", nullable = false)
    public double getLast_value() {
        return this.last_value;
    }
    public void setLast_value(double last_value) {
        this.last_value = last_value;
    }

    @Column(name = "max_bid", nullable = false)
    public double getMax_bid() {
        return this.max_bid;
    }
    public void setMax_bid(double max_bid) {
        this.max_bid = max_bid;
    }

    @Column(name = "max_sell", nullable = false)
    public double getMax_sell() {
        return this.max_sell;
    }
    public void setMax_sell(double max_sell) {
        this.max_sell = max_sell;
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
            ", last_value='" + getLast_value() + "'" +
            ", max_bid='" + getMax_bid() + "'" +
            ", max_sell='" + getMax_sell() + "'" +
            ", origin_currency='" + getOrigin_currency() + "'" +
            ", destiny_currency='" + getDestiny_currency() + "'" +
            ", orders='" + getOrders() + "'" +
            "}";
    }



}