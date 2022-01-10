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
    private Integer id;

    private Float prev_value;
    private Float max_buyer_value;
    private Float min_seller_value;
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "market_id", nullable = false)
    private Market market;

    public Ticker() {}

    public Ticker(Market market, Float prev_value, Float max_buyer_value, Float min_seller_value) {
        this.market = market;
        this.prev_value = prev_value;
        this.max_buyer_value = max_buyer_value;
        this.min_seller_value = min_seller_value;
        this.createdAt = new Timestamp(System.currentTimeMillis()); 
    }

    public Integer getId() {
        return id;
    }

    @Column(name = "market", nullable = false)
    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }


    @Column(name = "max_buyer_value", nullable = false)
    public Float getMax_buyer_value() {
        return max_buyer_value;
    }

    public void setMax_buyer_value(Float max_buyer_value) {
        this.max_buyer_value = max_buyer_value;
    }

    @Column(name = "min_seller_value", nullable = false)
    public Float getMin_seller_value() {
        return min_seller_value;
    }

    public void setMin_seller_value(Float min_seller_value) {
        this.min_seller_value = min_seller_value;
    }

    @Column(name = "created_at", nullable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp created_at) {
        this.createdAt = created_at;
    }

    @Column(name = "market", nullable = false)
    public Integer getMarketId() {
        return this.market.getId();
    }


    @Column(name = "prev_value", nullable = false)
    public Float getPrev_value() {
        return this.prev_value;
    }

    public void setPrev_value(Float prev_value) {
        this.prev_value = prev_value;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", max_buyer_value='" + getMax_buyer_value() + "'" +
            ", min_seller_value='" + getMin_seller_value() + "'" +
            ", created_at='" + getCreatedAt() + "'" +
            ", prev_value='" + getPrev_value() + "'" +
            ", market='" + getMarketId() + "'" +
            "}";
    }


}
