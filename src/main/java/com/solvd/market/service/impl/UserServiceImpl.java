package com.solvd.market.service.impl;

import com.solvd.market.domain.users.User;
import com.solvd.market.persistence.UserRepository;
import com.solvd.market.persistence.impl.UserMapperImpl;
import com.solvd.market.persistence.impl.UserRepositoryImpl;
import com.solvd.market.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl() {
        //this.userRepository = new UserRepositoryImpl();
        this.userRepository = new UserMapperImpl();
    }

    @Override
    public User create(User user) {
        user.setId(null);
        userRepository.create(user);
        return user;
    }

    @Override
    public List<User> retrieveAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> retrieveById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

}
