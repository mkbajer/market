package com.solvd.market.persistence;

import com.solvd.market.domain.orders.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepository {
    void create(Cart cart);

    List<Cart> findAll();

    Optional<Cart> findById(Long id);

    void update(Cart cart);

    void delete(Long id);
}
