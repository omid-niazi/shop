package ir.bootcamp.oneline.shop.repository;

import ir.bootcamp.oneline.shop.model.Orders;
import ir.bootcamp.oneline.shop.model.Customer;

import java.util.List;

public interface OrderRepository extends RepositoryInterface<Orders> {
    List<Orders> findByUser(Customer user);
}
