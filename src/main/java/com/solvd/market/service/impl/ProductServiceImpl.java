package com.solvd.market.service.impl;

import com.solvd.market.domain.products.Product;
import com.solvd.market.persistence.ProductRepository;
import com.solvd.market.persistence.impl.ProductRepositoryImpl;
import com.solvd.market.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl() {
        //this.productRepository = new ProductRepositoryImpl();
        this.productRepository = new ProductRepositoryImpl();
    }

    @Override
    public Product create(Product product, Long categoryId) {
        product.setId(null);
        productRepository.create(product, categoryId);
        return product;
    }

    @Override
    public List<Product> retrieveAll() {
        return productRepository.findAll();
    }

    @Override
    public Product retrieveById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }
}
