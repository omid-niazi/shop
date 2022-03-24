package ir.bootcamp.oneline.shop.service.impl;

import ir.bootcamp.oneline.shop.common.Transaction;
import ir.bootcamp.oneline.shop.model.Orders;
import ir.bootcamp.oneline.shop.model.Customer;
import ir.bootcamp.oneline.shop.repository.OrderRepository;
import ir.bootcamp.oneline.shop.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transaction
    public List<Orders> findUserOrders(Customer user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public void create(Orders order) {
        orderRepository.save(order);
    }
}
