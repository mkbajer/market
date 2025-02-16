package com.solvd.market.service.impl;

import com.solvd.market.domain.products.Discount;
import com.solvd.market.persistence.DiscountRepository;
import com.solvd.market.persistence.impl.DiscountRepositoryImpl;
import com.solvd.market.service.DiscountService;

import java.util.List;

public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;

    public DiscountServiceImpl() {
        this.discountRepository = new DiscountRepositoryImpl();
    }

    @Override
    public Discount create(Discount discount, Long categoryId) {
        discount.setId(null);
        discountRepository.create(discount, categoryId);
        return discount;
    }

    @Override
    public List<Discount> retrieveAll() {
        return discountRepository.findAll();
    }

    @Override
    public Discount retrieveById(Long id) {
        return discountRepository.findById(id);
    }

    @Override
    public void update(Discount discount) {
        discountRepository.update(discount);
    }

    @Override
    public void delete(Long id) {
        discountRepository.delete(id);
    }

    @Override
    public List<Discount> getDiscountsByCategory(Long categoryId) {
        return discountRepository.findByCategoryId(categoryId);
    }

    @Override
    public void addDiscountToCategory(Long discountId, Long categoryId) {
        // Implementation for adding a discount to a category
    }
}