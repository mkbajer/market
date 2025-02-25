package com.solvd.market.persistence.impl;

import com.solvd.market.domain.orders.Order;
import com.solvd.market.persistence.OrderRepository;
import com.solvd.market.persistence.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class OrderMapperImpl implements OrderRepository {

    @Override
    public void create(Order order) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            OrderRepository orderRepository = session.getMapper(OrderRepository.class);
            orderRepository.create(order);
        }
    }

    @Override
    public List<Order> findAll() {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            OrderRepository orderRepository = session.getMapper(OrderRepository.class);
            return orderRepository.findAll();
        }
    }

    @Override
    public Optional<Order> findById(Long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            OrderRepository orderRepository = session.getMapper(OrderRepository.class);
            return orderRepository.findById(id);
        }
    }

    @Override
    public void update(Order order) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            OrderRepository orderRepository = session.getMapper(OrderRepository.class);
            orderRepository.update(order);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            OrderRepository orderRepository = session.getMapper(OrderRepository.class);
            orderRepository.delete(id);
        }
    }
}