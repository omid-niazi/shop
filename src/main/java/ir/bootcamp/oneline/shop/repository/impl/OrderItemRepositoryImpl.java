package ir.bootcamp.oneline.shop.repository.impl;

import ir.bootcamp.oneline.shop.model.OrderItem;
import ir.bootcamp.oneline.shop.repository.OrderItemRepository;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class OrderItemRepositoryImpl extends BaseRepository<OrderItem> implements OrderItemRepository {
    public OrderItemRepositoryImpl() {
        super(OrderItem.class);
    }
}