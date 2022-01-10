package yes.finance.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Market")
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "price", nullable=true)
    private Float price;

    @Column(name = "change_hour", nullable=true)
    private Float change_hour;

    @Column(name = "change_minute", nullable=true)
    private Float change_minute;

    @Column(name = "createdAt", nullable=false)
    private Instant createdAt;
    //private Timestamp createdAt;
    
    @ManyToOne  
    @JoinColumn(name = "origin_currency_id", nullable = false)
    private Currency originCurrency;

    @ManyToOne
    @JoinColumn(name = "destiny_currency_id", nullable = false)
    private Currency destinyCurrency;


    public Market() {
        this.price = 0f;
        this.change_hour = 0f;
        this.change_minute = 0f;
    }
    
    public Market(String symbol, Currency origin_currency, Currency destiny_currency, Instant createdAt) {
        this.symbol = symbol;
        this.originCurrency = origin_currency;
        this.destinyCurrency = destiny_currency;
        this.price = 0f;
        this.change_hour = 0f;
        this.change_minute = 0f;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Currency getOriginCurrency() {
        return this.originCurrency;
    }

    public void setOriginCurrency(Currency origin_currency) {
        this.originCurrency = origin_currency;
    }
    
    public Currency getDestinyCurrency() {
        return this.destinyCurrency;
    }

    public void setDestinyCurrency(Currency destiny_currency) {
        this.destinyCurrency = destiny_currency;
    }

    public Float getMinuteChange() {
        return this.change_minute;
    }

    public void setMinuteChange(Float minuteChange) {
        this.change_minute = minuteChange;
    }


    public Float getHourChange() {
        return this.change_hour;
    }

    public void setHourChange(Float hourChange) {
        this.change_hour = hourChange;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", origin_currency='" + getOriginCurrency() + "'" +
            ", destiny_currency='" + getDestinyCurrency() + "'" +
            "}";
    }
    
   
}