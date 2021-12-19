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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    
    private Integer id;

    private String symbol;
    
    @ManyToOne  
    @JoinColumn(name = "origin_currency_id")
    private Currency origin_currency;

    @ManyToOne
    @JoinColumn(name = "destiny_currency_id")
    private Currency destiny_currency;

    @OneToMany(mappedBy = "id")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "market")
    private List<Ticker> tickers = new ArrayList<>();

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

    @Column(name = "symbol", nullable = false)
    public String getSymbol() {
        return this.symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    @Column(name = "origin_currency", nullable = false)
    public Integer getOrigin_currencyId() {
        return this.origin_currency.getId();
    }

    // public Currency getOrigin_currency() {
    //     return this.origin_currency;
    // }

    public void setOrigin_currency(Currency origin_currency) {
        this.origin_currency = origin_currency;
    }
    
    @Column(name = "destiny_currency", nullable = false)
    public Integer getDestiny_currencyId() {
        return this.destiny_currency.getId();
    }
    // public Currency getDestiny_currency() {
    //     return this.destiny_currency;
    // }
    public void setDestiny_currency(Currency destiny_currency) {
        this.destiny_currency = destiny_currency;
    }


    public List<Order> getOrders() {
        return this.orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Ticker> getTickers() {
        return this.tickers;
    }
    public void setTicker(List<Ticker> tickers) {
        this.tickers = tickers;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", origin_currency='" + getOrigin_currencyId() + "'" +
            ", destiny_currency='" + getDestiny_currencyId() + "'" +
            ", orders='" + getOrders() + "'" +
            "}";
    }
}