package com.solvd.market.persistence;

import com.solvd.market.domain.products.Category;

import java.util.List;

public interface CategoryRepository {
    void create(Category category);

    List<Category> findAll();

    Category findById(Long id);

    void update(Category category);

    void delete(Long id);
}
