package com.solvd.market.persistence.impl;

import com.solvd.market.domain.orders.Cart;
import com.solvd.market.persistence.CartRepository;
import com.solvd.market.persistence.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class CartMapperImpl implements CartRepository {

    @Override
    public void create(Cart cart) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            CartRepository cartRepository = session.getMapper(CartRepository.class);
            cartRepository.create(cart);
        }
    }

    @Override
    public List<Cart> findAll() {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            CartRepository cartRepository = session.getMapper(CartRepository.class);
            return cartRepository.findAll();
        }
    }

    @Override
    public Optional<Cart> findById(Long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            CartRepository cartRepository = session.getMapper(CartRepository.class);
            return cartRepository.findById(id);
        }
    }

    @Override
    public void update(Cart cart) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            CartRepository cartRepository = session.getMapper(CartRepository.class);
            cartRepository.update(cart);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            CartRepository cartRepository = session.getMapper(CartRepository.class);
            cartRepository.delete(id);
        }
    }
}