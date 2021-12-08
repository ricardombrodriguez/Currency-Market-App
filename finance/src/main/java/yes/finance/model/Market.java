package yes.finance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "extension")
public class Market {
    
    private int id;
    private int origin_currency;
    private int destiny_currency;
    private double last_value;
    private double max_bid;
    private double max_sell;

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
    public int getOrigin_currency() {
        return this.origin_currency;
    }
    public void setOrigin_currency(int origin_currency) {
        this.origin_currency = origin_currency;
    }
    
    @Column(name = "destiny_currency", nullable = false)
    public int getDestiny_currency() {
        return this.destiny_currency;
    }
    public void setDestiny_currency(int destiny_currency) {
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


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", origin_currency='" + getOrigin_currency() + "'" +
            ", destiny_currency='" + getDestiny_currency() + "'" +
            ", last_value='" + getLast_value() + "'" +
            ", max_bid='" + getMax_bid() + "'" +
            ", max_sell='" + getMax_sell() + "'" +
            "}";
    }

}