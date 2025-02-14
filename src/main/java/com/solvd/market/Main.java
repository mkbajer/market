package com.solvd.market;

import com.solvd.market.domain.MarketPlace;
import com.solvd.market.domain.users.User;
import com.solvd.market.service.MarketPlaceService;
import com.solvd.market.service.UserService;
import com.solvd.market.service.impl.MarketPlaceServiceImpl;
import com.solvd.market.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Main {
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
        users.forEach(u -> System.out.println("User: " + u.getName() + " " + u.getSurname()));

        Optional<User> fetchedUser = userService.retrieveById(user.getId());
        fetchedUser.ifPresent(u -> System.out.println("Fetched User: " + u.getEmail()));

        MarketPlaceService marketPlaceService = new MarketPlaceServiceImpl();


        MarketPlace marketPlace = new MarketPlace();
        marketPlace.setName("Online-" + UUID.randomUUID().toString().substring(0,2));

        marketPlace = marketPlaceService.create(marketPlace);
        /*
        // Pobranie wszystkich Marketplace'ów
        List<MarketPlace> marketPlaces = marketPlaceService.retrieveAll();
        marketPlaces.forEach(mp -> System.out.println("MarketPlace: " + mp.getName()));

        // Pobranie Marketplace po ID
        MarketPlace fetchedMarket = marketPlaceService.retrieveById(marketPlace.getId());
        System.out.println("Fetched MarketPlace: " + fetchedMarket.getName()); */
    }
}