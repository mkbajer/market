package com.solvd.market;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.market.domain.MarketPlace;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ParserJson {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    public static List<MarketPlace> parseMarketPlaces(String filePath) {
        try {
            MarketWrapper marketWrapper = objectMapper.readValue(new File(filePath), MarketWrapper.class);
            return marketWrapper.getMarketplaces();
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse JSON file.", e);
        }
    }

    public static void main(String[] args) {
        String filePath = "src/main/resources/structures/market.json";
        List<MarketPlace> marketPlaces = parseMarketPlaces(filePath);

        for (MarketPlace marketPlace : marketPlaces) {
            System.out.println("MarketPlace Name: " + marketPlace.getName());
            marketPlace.getUsers().forEach(user -> {
                System.out.println("User ID: " + user.getId());
                System.out.println("Name: " + user.getName());
                System.out.println("Surname: " + user.getSurname());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Phone: " + user.getPhone());
                System.out.println("Type: " + user.getType());
                System.out.println("Active: " + user.getActive());
                user.getCarts().forEach(cart -> {
                    System.out.println("  Cart ID: " + cart.getId());
                    cart.getProducts().forEach(product -> {
                        System.out.println("    Product ID: " + product.getId());
                        System.out.println("    Name: " + product.getName());
                        System.out.println("    Price: " + product.getPrice());
                    });
                });
            });
        }
    }
}