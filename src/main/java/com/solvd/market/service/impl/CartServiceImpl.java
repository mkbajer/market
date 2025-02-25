package com.solvd.market.service.impl;

import com.solvd.market.domain.orders.Cart;
import com.solvd.market.persistence.CartRepository;
import com.solvd.market.persistence.impl.CartMapperImpl;
import com.solvd.market.persistence.impl.CartRepositoryImpl;
import com.solvd.market.service.CartService;

import java.util.List;
import java.util.Optional;

public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    public CartServiceImpl() {
        //this.cartRepository = new CartRepositoryImpl();
        this.cartRepository = new CartMapperImpl();
    }

    @Override
    public Cart create(Cart cart) {
        cart.setId(null);
        cartRepository.create(cart);
        return cart;
    }

    @Override
    public List<Cart> retrieveAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> retrieveById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public void update(Cart cart) {
        cartRepository.update(cart);
    }

    @Override
    public void delete(Long id) {
        cartRepository.delete(id);
    }
}
