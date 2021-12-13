package yes.finance.model;

import javax.persistence.*;


@Entity
@Table(name = "Transaction")
public class Transaction{

    public Transaction(){}

    @ManyToOne
    @JoinColumn(name = "Order.id")
    private Order origin_order;

    @ManyToOne
    @JoinColumn(name = "Order.id")
    private Order destiny_order;

    public Order getOrigin_order() {
        return this.origin_order;
    }

    public void setOrigin_order(Order origin_order) {
        this.origin_order = origin_order;
    }

    public Order getDestiny_order() {
        return this.destiny_order;
    }

    public void setDestiny_order(Order destiny_order) {
        this.destiny_order = destiny_order;
    }

    @Override
    public String toString() {
        return "{" +
            " origin_order='" + getOrigin_order() + "'" +
            ", destiny_order='" + getDestiny_order() + "'" +
            "}";
    }

}
