package com.solvd.market.service;

import com.solvd.market.domain.products.Discount;

import java.util.List;

public interface DiscountService {
    Discount create(Discount discount, Long categoryId);

    List<Discount> retrieveAll();

    Discount retrieveById(Long id);

    void update(Discount discount);

    void delete(Long id);

    List<Discount> getDiscountsByCategory(Long categoryId);

    void addDiscountToCategory(Long discountId, Long categoryId);
}