package ir.bootcamp.oneline.shop.service;

import ir.bootcamp.oneline.shop.model.Orders;
import ir.bootcamp.oneline.shop.model.Customer;

import java.util.List;

public interface OrderService {
    List<Orders> findUserOrders(Customer user);

    void create(Orders order);
}
