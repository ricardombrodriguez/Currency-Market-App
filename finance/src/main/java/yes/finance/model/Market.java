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
    @JoinColumn(name = "origin_currency_id")
    private Currency origin_currency;

    @ManyToOne
    @JoinColumn(name = "destiny_currency_id")
    private Currency destiny_currency;

    public Market() {
    }
    
    public Market(String symbol, Currency origin_currency, Currency destiny_currency) {
        this.symbol = symbol;
        this.origin_currency = origin_currency;
        this.destiny_currency = destiny_currency;
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

    public Currency getOrigin_currency() {
        return this.origin_currency;
    }

    public void setOrigin_currency(Currency origin_currency) {
        this.origin_currency = origin_currency;
    }
    
    public Currency getDestiny_currency() {
        return this.destiny_currency;
    }

    public void setDestiny_currency(Currency destiny_currency) {
        this.destiny_currency = destiny_currency;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", origin_currency='" + getOrigin_currency() + "'" +
            ", destiny_currency='" + getDestiny_currency() + "'" +
            "}";
    }
}