package com.solvd.market.service.impl;

import com.solvd.market.domain.products.Category;
import com.solvd.market.persistence.CategoryRepository;
import com.solvd.market.persistence.impl.CategoryRepositoryImpl;
import com.solvd.market.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl() {
        this.categoryRepository = new CategoryRepositoryImpl();
    }

    @Override
    public Category create(Category category) {
        category.setId(null);
        categoryRepository.create(category);
        return category;
    }

    @Override
    public List<Category> retrieveAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category retrieveById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void update(Category category) {
        categoryRepository.update(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
    }
}