package yes.finance.model;

import javax.persistence.*;


@Entity
@Table(name = "Transaction")
public class Transaction{

    public Transaction(){}  

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "origin_order_id")
    private Order origin_order;

    @ManyToOne
    @JoinColumn(name = "destiny_order_id")
    private Order destiny_order;

    public Integer getId() {
        return id;
    }

    public Integer getOrigin_orderId() {
        return this.origin_order.getId();
    }

    // public Order getOrigin_order() {
    //     return this.origin_order;
    // }

    public void setOrigin_order(Order origin_order) {
        this.origin_order = origin_order;
    }

    public Integer getDestiny_orderId() {
        return this.destiny_order.getId();
    }

    // public Order getDestiny_order() {
    //     return this.destiny_order;
    // }

    public void setDestiny_order(Order destiny_order) {
        this.destiny_order = destiny_order;
    }

    @Override
    public String toString() {
        return "{" +
            " origin_order='" + getOrigin_orderId() + "'" +
            ", destiny_order='" + getDestiny_orderId() + "'" +
            "}";
    }

}
