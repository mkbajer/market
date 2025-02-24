package com.solvd.market.persistence;

import com.solvd.market.domain.products.Discount;

import java.util.List;

public interface DiscountRepository {
    void create(Discount discount, Long categoryId);

    List<Discount> findAll();

    Discount findById(Long id);

    void update(Discount discount);

    void delete(Long id);

    List<Discount> findByCategoryId(Long categoryId);

    void addDiscountToCategory(Long discountId, Long categoryId);
}