package com.solvd.market.persistence.impl;

import com.solvd.market.domain.products.Category;
import com.solvd.market.domain.products.Product;
import com.solvd.market.persistence.ConnectionPool;
import com.solvd.market.persistence.ProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO product (category_id, name, price) VALUES (?, ?, ?)";
    private static final String FIND_ALL_QUERY = "SELECT id, category_id, name, price FROM product";
    private static final String FIND_BY_ID_QUERY = FIND_ALL_QUERY + " WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE product SET name = ?, price = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM product WHERE id = ?";

    @Override
    public void create(Product product, Long categoryId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, categoryId);
            stmt.setString(2, product.getName());
            stmt.setDouble(3, product.getPrice());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                product.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Product.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.name AS product_name, c.name AS category_name FROM product p JOIN category c ON p.category_id = c.id";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setName(rs.getString("product_name"));
                Category category = new Category();
                category.setName(rs.getString("category_name"));
                product.setCategory(category);
                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to retrieve Products with Categories.", e);
        }
        return products;
    }

    @Override
    public Product findById(Long id) {
        Product product = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find Product by ID.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return product;
    }

    @Override
    public void update(Product product) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setLong(3, product.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update Product.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_QUERY)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete Product.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}

