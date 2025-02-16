package com.solvd.market.persistence.impl;

import com.solvd.market.domain.products.Category;
import com.solvd.market.persistence.CategoryRepository;
import com.solvd.market.persistence.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO category (name) VALUES (?)";
    private static final String FIND_ALL_QUERY = "SELECT id, name FROM category";
    private static final String FIND_BY_ID_QUERY = FIND_ALL_QUERY + " WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE category SET name = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM category WHERE id = ?";

    @Override
    public void create(Category category) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, category.getName());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                category.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Category.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to fetch Categories.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return categories;
    }

    @Override
    public Category findById(Long id) {
        Category category = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                category = new Category();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find Category by ID.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return category;
    }

    @Override
    public void update(Category category) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1, category.getName());
            stmt.setLong(2, category.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update Category.", e);
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
            throw new RuntimeException("Unable to delete Category.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
