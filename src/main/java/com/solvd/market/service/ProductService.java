package com.solvd.market.service;

import com.solvd.market.domain.products.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product, Long categoryId);

    List<Product> retrieveAll();

    Product retrieveById(Long id);

    void update(Product product);

    void delete(Long id);
}
