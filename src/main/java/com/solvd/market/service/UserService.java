package com.solvd.market.service;

import com.solvd.market.domain.users.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(User user);

    List<User> retrieveAll();

    Optional<User> retrieveById(Long id);

    void update(User user);

    void delete(Long id);
}
