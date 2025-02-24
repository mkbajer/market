package com.solvd.market.service;

import com.solvd.market.domain.products.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);

    List<Category> retrieveAll();

    Category retrieveById(Long id);

    void update(Category category);

    void delete(Long id);
}