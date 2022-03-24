package ir.bootcamp.oneline.shop.repository.impl;

import ir.bootcamp.oneline.shop.model.Orders;
import ir.bootcamp.oneline.shop.model.Customer;
import ir.bootcamp.oneline.shop.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl extends BaseRepository<Orders> implements OrderRepository {

    public OrderRepositoryImpl() {
        super(Orders.class);
    }

    @Override
    public List<Orders> findByUser(Customer user) {
        try {
            return getCurrentSession().createQuery("select a from Orders a where a.user.id=:id", Orders.class)
                    .setParameter("id", user.getId())
                    .getResultList();
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }
}

