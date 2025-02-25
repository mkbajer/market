package com.solvd.market.persistence.impl;

import com.solvd.market.domain.products.Product;
import com.solvd.market.persistence.ProductRepository;
import com.solvd.market.persistence.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProductMapperImpl implements ProductRepository {

    @Override
    public void create(Product product,Long categoryId) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            ProductRepository productRepository = session.getMapper(ProductRepository.class);
            productRepository.create(product, categoryId);
        }
    }

    @Override
    public List<Product> findAll() {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            ProductRepository productRepository = session.getMapper(ProductRepository.class);
            return productRepository.findAll();
        }
    }

    @Override
    public Product findById(Long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            ProductRepository productRepository = session.getMapper(ProductRepository.class);
            return productRepository.findById(id);
        }
    }

    @Override
    public void update(Product product) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            ProductRepository productRepository = session.getMapper(ProductRepository.class);
            productRepository.update(product);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            ProductRepository productRepository = session.getMapper(ProductRepository.class);
            productRepository.delete(id);
        }
    }
}