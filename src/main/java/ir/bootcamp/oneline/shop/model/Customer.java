package ir.bootcamp.oneline.shop.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer extends User {
    private Long balance;
    private String address;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Orders> orders;

    public Customer() {
    }

    public Customer(String usernane, String password, Long balance, String address, List<Orders> orders) {
        super(usernane, password);
        this.balance = balance;
        this.address = address;
        this.orders = orders;
    }

    public Customer(Long id, String usernane, String password, Long balance, String address, List<Orders> orders) {
        super(id, usernane, password);
        this.balance = balance;
        this.address = address;
        this.orders = orders;
    }


    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public void addOrder(Orders order) {
        orders.add(order);
    }
}
