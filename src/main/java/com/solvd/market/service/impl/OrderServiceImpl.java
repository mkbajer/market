package com.solvd.market.service.impl;

import com.solvd.market.domain.orders.Order;
import com.solvd.market.persistence.OrderRepository;
import com.solvd.market.persistence.impl.OrderMapperImpl;
import com.solvd.market.persistence.impl.OrderRepositoryImpl;
import com.solvd.market.service.OrderService;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl() {
        //this.orderRepository = new OrderRepositoryImpl();
        this.orderRepository = new OrderMapperImpl();
    }

    @Override
    public Order create(Order order) {
        order.setId(null);
        orderRepository.create(order);
        return order;
    }

    @Override
    public List<Order> retrieveAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> retrieveById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void update(Order order) {
        orderRepository.update(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.delete(id);
    }
}
