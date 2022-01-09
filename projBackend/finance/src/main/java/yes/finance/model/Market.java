package yes.finance.model;

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
    
    @ManyToOne  
    @JoinColumn(name = "origin_currency_id", nullable = false)
    private Currency originCurrency;

    @ManyToOne
    @JoinColumn(name = "destiny_currency_id", nullable = false)
    private Currency destinyCurrency;

    public Market() {
    }
    
    public Market(String symbol, Currency origin_currency, Currency destiny_currency) {
        this.symbol = symbol;
        this.originCurrency = origin_currency;
        this.destinyCurrency = destiny_currency;
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

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", origin_currency='" + getOriginCurrency() + "'" +
            ", destiny_currency='" + getDestinyCurrency() + "'" +
            "}";
    }
   
}