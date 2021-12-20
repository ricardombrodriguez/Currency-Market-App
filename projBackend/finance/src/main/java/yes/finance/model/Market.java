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

    private String symbol;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    
    private int id;
    
    @ManyToOne  
    @JoinColumn(name = "origin_currency_id", nullable = false)
    private Currency origin_currency;

    @ManyToOne
    @JoinColumn(name = "destiny_currency_id", nullable = false)
    private Currency destiny_currency;

    /*
    @OneToMany(mappedBy = "id")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "market")
    private List<Ticker> tickers = new ArrayList<>();  
    */

    public Market() {}

    public Market(String symbol, Currency origin_currency, Currency destiny_currency) {
        this.symbol = symbol;
        this.origin_currency = origin_currency;
        this.destiny_currency = destiny_currency;
    }

    public int getId() {
        return id;
    }

    @Column(name = "symbol", nullable = false)
    public String getSymbol() {
        return this.symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    @Column(name = "origin_currency", nullable = false)
    public Integer getOriginCurrencyId() {
        return this.origin_currency.getId();
    }

    // public Currency getOrigin_currency() {
    //     return this.origin_currency;
    // }

    public void setOriginCurrency(Currency origin_currency) {
        this.origin_currency = origin_currency;
    }
    
    @Column(name = "destiny_currency", nullable = false)
    public Integer getDestinyCurrencyId() {
        return this.destiny_currency.getId();
    }
    // public Currency getDestiny_currency() {
    //     return this.destiny_currency;
    // }
    public void setDestinyCurrency(Currency destiny_currency) {
        this.destiny_currency = destiny_currency;
    }


    @Override
    public String toString() {
        return "{" +
            " msymbol='" + getSymbol() + "'" +
            ", id='" + getId() + "'" +
            ", origin_currency='" + getOriginCurrencyId() + "'" +
            ", destiny_currency='" + getDestinyCurrencyId() + "'" +
            "}";
    }
   
}