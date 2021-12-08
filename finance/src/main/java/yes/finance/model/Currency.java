package yes.finance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currency")
public class Currency {
    
    private int id;
    private String name;
    private String symbol;
    private String logoUrl;

    public Currency(){}

    public Currency(String name, String symbol, String logoUrl) {
        this.name = name;
        this.symbol = symbol;
        this.logoUrl = logoUrl;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", symbol='" + getSymbol() + "'" +
            ", logoUrl='" + getLogoUrl() + "'" +
            "}";
    }
}