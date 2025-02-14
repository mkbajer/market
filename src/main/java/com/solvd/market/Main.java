package com.solvd.market;

import com.solvd.market.domain.MarketPlace;
import com.solvd.market.domain.users.User;
import com.solvd.market.service.MarketPlaceService;
import com.solvd.market.service.UserService;
import com.solvd.market.service.impl.MarketPlaceServiceImpl;
import com.solvd.market.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        User user = new User();
        user.setName("John");
        user.setSurname("Doe");
        user.setEmail("jd" + UUID.randomUUID().toString().substring(0,5) + "@gmail.com");
        user.setPassword("Password123");
        user.setPhone("234567899");
        user.setType(true);
        user.setActive(true);

        user = userService.create(user);

        List<User> users = userService.retrieveAll();
        users.forEach(u -> log.info("User: {} {}", u.getName(), u.getSurname()));

        Optional<User> fetchedUser = userService.retrieveById(user.getId());
        fetchedUser.ifPresent(u -> log.info("Fetched User: {}", u.getEmail()));

        MarketPlaceService marketPlaceService = new MarketPlaceServiceImpl();

        MarketPlace marketPlace = new MarketPlace();
        marketPlace.setName("Online-" + UUID.randomUUID().toString().substring(0,2));

        List<MarketPlace> marketPlaces = marketPlaceService.retrieveAll();
        marketPlaces.forEach(mp -> log.info("MarketPlace: {}", mp.getName()));

    }
}