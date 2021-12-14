package yes.finance.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Currency")
public class Currency {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;


    private String name;
    private String symbol;
    private String logoUrl;
    private Byte online;

    @OneToMany(mappedBy = "origin_currency")
    private List<Market> list_origin_currency = new ArrayList<>();

    @OneToMany(mappedBy = "destiny_currency")
    private List<Market> list_destiny_currency = new ArrayList<>();  

    public Currency(){}

    public Currency(String name, String symbol, String logoUrl, Byte online) {
        this.name = name;
        this.symbol = symbol;
        this.logoUrl = logoUrl;
        this.online = online;
    }
    
    public int getId() {
        return id;
    }
    
    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name = "symbol", nullable = false)
    public String getSymbol() {
        return this.symbol;
    }
    public void setSymbol(String new_symbol) {
        this.symbol = new_symbol;
    }

    @Column(name = "logoUrl", nullable = false)
    public String getLogoUrl() {
        return this.logoUrl;
    }
    public void setLogoUrl(String new_logo) {
        this.logoUrl = new_logo;
    }

    public Byte getOnline() {
        return this.online;
    }
    public void setOnline(Byte online) {
        this.online = online;
    }

    public List<Market> getList_origin_currency() {
        return this.list_origin_currency;
    }
    public void setList_origin_currency(List<Market> list_origin_currency) {
        this.list_origin_currency = list_origin_currency;
    }

    public List<Market> getList_destiny_currency() {
        return this.list_destiny_currency;
    }
    public void setList_destiny_currency(List<Market> list_destiny_currency) {
        this.list_destiny_currency = list_destiny_currency;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", symbol='" + getSymbol() + "'" +
            ", logoUrl='" + getLogoUrl() + "'" +
            ", online='" + getOnline() + "'" +
            ", list_origin_currency='" + getList_origin_currency() + "'" +
            ", list_destiny_currency='" + getList_destiny_currency() + "'" +
            "}";
    }
    
}