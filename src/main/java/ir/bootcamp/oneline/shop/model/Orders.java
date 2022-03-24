package ir.bootcamp.oneline.shop.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @UpdateTimestamp
    private Timestamp date;
    private Boolean isFinal;
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Customer user;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> orderItems;
    private Long price;

    public Orders() {
    }

    public Orders(Boolean isFinal, Customer user, List<OrderItem> orderItems, Long price) {
        this.isFinal = isFinal;
        this.user = user;
        this.orderItems = orderItems;
        this.price = price;
    }

    public Orders(Long id, Timestamp date, Boolean isFinal, Customer user, List<OrderItem> orderItems, Long price) {
        this.id = id;
        this.date = date;
        this.isFinal = isFinal;
        this.user = user;
        this.orderItems = orderItems;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Boolean getFinal() {
        return isFinal;
    }

    public void setFinal(Boolean aFinal) {
        isFinal = aFinal;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", date=" + date +
                ", items count=" + orderItems.size() +
                ", price=" + price +
                '}';
    }
}
