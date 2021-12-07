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
    
    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name = "year", nullable = false)
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    
    @Override
    public String toString() {

        String ret = "Movie [id=" + id + ", title=" + title + ", year=" + year + "]";
        

        return ret;
    }
    
    
}