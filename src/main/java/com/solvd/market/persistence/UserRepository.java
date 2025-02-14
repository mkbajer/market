package com.solvd.market.persistence;

import com.solvd.market.domain.users.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {

    void create(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    void update(User user);

    void delete(Long id);

}
