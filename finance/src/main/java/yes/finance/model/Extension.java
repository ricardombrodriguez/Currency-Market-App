package yes.finance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "extension")
public class Extension {
    
    private int id;
    private int user;
    private String path;

    public Extension(){}

    public Extension(int user, String path) {
        this.user = user;
        this.path = path;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    
    @Column(name = "user", nullable = false)
    public int getUser() {
        return this.user;
    }
    public void setUser(int new_user) {
        this.user = new_user;
    }
    
    @Column(name = "path", nullable = false)
    public String getPath() {
        return this.path;
    }
    public void setPath(String new_path) {
        this.path = new_path;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", path='" + getPath() + "'" +
            "}";
    }
}