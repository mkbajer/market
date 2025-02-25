package com.solvd.market.persistence.impl;

import com.solvd.market.domain.users.User;
import com.solvd.market.persistence.UserRepository;
import com.solvd.market.persistence.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class UserMapperImpl implements UserRepository {

    @Override
    public void create(User user) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            UserRepository userRepository = session.getMapper(UserRepository.class);
            userRepository.create(user);
        }
    }

    @Override
    public List<User> findAll() {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            UserRepository userRepository = session.getMapper(UserRepository.class);
            return userRepository.findAll();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            UserRepository userRepository = session.getMapper(UserRepository.class);
            return userRepository.findById(id);
        }
    }

    @Override
    public void update(User user) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            UserRepository userRepository = session.getMapper(UserRepository.class);
            userRepository.update(user);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            UserRepository userRepository = session.getMapper(UserRepository.class);
            userRepository.delete(id);
        }
    }
}