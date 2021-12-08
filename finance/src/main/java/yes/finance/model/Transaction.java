package yes.finance.model;

import javax.persistence.*;


@Entity
@Table(name = "Transaction")
public class Transaction{
    // private int origin_order;
    // private int destiny_order;

    public Transaction(){}


    @ManyToOne
    @JoinColumn(name = "Order.id")
    private Order origin_order;

    @ManyToOne
    @JoinColumn(name = "Order.id")
    private Order destiny_order;


}