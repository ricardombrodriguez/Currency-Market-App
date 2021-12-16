package yes.finance.model;

import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.lang.Float;

import java.util.Date;


@Entity
@Table(name = "Ticker")
public class Ticker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private Float lastValue;
    private Float maxBuyerValue;
    private Float minSellerValue;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "market_id")
    private Market market;

    public Ticker() {
    }

    public Ticker(Market market, float last_value, float max_buyer_value, float min_seller_value) {
        this.market = market;
        this.lastValue = last_value;
        this.maxBuyerValue = max_buyer_value;
        this.minSellerValue = min_seller_value;
    }

    @Column(name = "created_at", nullable = false)
    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Column(name = "lastValue", nullable = false)
    public Float getlastValue() {
        return lastValue;
    }

    public void setlastValue(Float lastValue) {
        this.lastValue = lastValue;
    }

    @Column(name = "maxBuyerValue", nullable = false)
    public Float getmaxBuyerValue() {
        return maxBuyerValue;
    }

    public void setMmaxBuyerValue(Float maxBuyerValue) {
        this.maxBuyerValue = maxBuyerValue;
    }

    @Column(name = "minSellerValue", nullable = false)
    public Float getminSellerValue() {
        return minSellerValue;
    }

    public void setminSellerValue(Float minSellerValue) {
        this.minSellerValue = minSellerValue;
    }

}
