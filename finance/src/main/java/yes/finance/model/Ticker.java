package yes.finance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "Ticker")
public class Ticker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private float prev_value;
    private float max_buyer_value;
    private float min_seller_value;
    private Timestamp created_at; 

    @ManyToOne
    @JoinColumn(name = "market_id")
    private Market market;

    public Ticker(){}

    public Ticker( float prev_value, float max_buyer_value, float min_seller_value) {
        this.prev_value = prev_value;
        this.max_buyer_value = max_buyer_value;
        this.min_seller_value = min_seller_value;
        this.created_at = new Timestamp(System.currentTimeMillis()); 
    }

    public int getId() {
        return id;
    }


    @Column(name = "max_buyer_value", nullable = false)
    public float getMax_buyer_value() {
        return max_buyer_value;
    }

    public void setMax_buyer_value(float max_buyer_value) {
        this.max_buyer_value = max_buyer_value;
    }

    @Column(name = "min_seller_value", nullable = false)
    public float getMin_seller_value() {
        return min_seller_value;
    }

    public void setMin_seller_value(float min_seller_value) {
        this.min_seller_value = min_seller_value;
    }

    @Column(name = "created_at", nullable = false)
    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    @Column(name = "market", nullable = false)
    public int getMarketId() {
        return this.market.getId();
    }
    // public Market getMarket() {
    //     return this.market;
    // }

    public void setMarket(Market market) {
        this.market = market;
    }


    @Column(name = "prev_value", nullable = false)
    public float getPrev_value() {
        return this.prev_value;
    }

    public void setPrev_value(float prev_value) {
        this.prev_value = prev_value;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", max_buyer_value='" + getMax_buyer_value() + "'" +
            ", min_seller_value='" + getMin_seller_value() + "'" +
            ", created_at='" + getCreated_at() + "'" +
            ", prev_value='" + getPrev_value() + "'" +
            ", market='" + getMarketId() + "'" +
            "}";
    }


}