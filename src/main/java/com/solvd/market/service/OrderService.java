package com.solvd.market.service;

import com.solvd.market.domain.orders.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order create(Order order);

    List<Order> retrieveAll();

    Optional<Order> retrieveById(Long id);

    void update(Order order);

    void delete(Long id);
}
