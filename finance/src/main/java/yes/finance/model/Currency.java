package yes.finance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Currency")
public class Currency {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    private String name;
    private String symbol;
    private String logoUrl;
    private boolean online;

    public Currency(){}

    public Currency(String name, String symbol, String logoUrl, Boolean online) {
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

    @Column(name = "logoUrl")
    public String getLogoUrl() {
        return this.logoUrl;
    }

    public void setLogoUrl(String new_logo) {
        this.logoUrl = new_logo;
    }

    @Column(name = "online", nullable = false)
    public Boolean getOnline() {
        return this.online;
    }
    public void setOnline(Boolean online) {
        this.online = online;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", symbol='" + getSymbol() + "'" +
            ", logoUrl='" + getLogoUrl() + "'" +
            ", online='" + getOnline() + "'" +
            "}";
    }
    
}