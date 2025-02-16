package com.solvd.market.persistence;

import com.solvd.market.domain.orders.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    void create(Order order);

    List<Order> findAll();

    Optional<Order> findById(Long id);

    void update(Order order);

    void delete(Long id);
}