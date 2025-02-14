package com.solvd.market.domain.orders;

import com.solvd.market.domain.products.Product;
import com.solvd.market.domain.users.User;

import java.util.List;

public class Cart {

    private Long id;
    private User user;
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
