package com.solvd.market.service;

import com.solvd.market.domain.orders.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart create(Cart cart);

    List<Cart> retrieveAll();

    Optional<Cart> retrieveById(Long id);

    void update(Cart cart);

    void delete(Long id);
}
