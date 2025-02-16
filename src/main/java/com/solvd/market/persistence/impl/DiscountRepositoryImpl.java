package com.solvd.market.persistence.impl;

import com.solvd.market.domain.products.Discount;
import com.solvd.market.persistence.ConnectionPool;
import com.solvd.market.persistence.DiscountRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountRepositoryImpl implements DiscountRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO discount (category_id, name, amount) VALUES (?, ?, ?)";
    private static final String FIND_ALL_QUERY = "SELECT id, category_id, name, amount FROM discount";
    private static final String FIND_BY_ID_QUERY = FIND_ALL_QUERY + " WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE discount SET name = ?, amount = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM discount WHERE id = ?";

    @Override
    public void create(Discount discount, Long categoryId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, categoryId);
            stmt.setString(2, discount.getName());
            stmt.setDouble(3, discount.getAmount());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                discount.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Discount.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Discount> findAll() {
        List<Discount> discounts = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Discount discount = new Discount();
                discount.setId(rs.getLong("id"));
                discount.setName(rs.getString("name"));
                discount.setAmount(rs.getDouble("amount"));
                discounts.add(discount);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to fetch Discounts.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return discounts;
    }

    @Override
    public Discount findById(Long id) {
        Discount discount = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                discount = new Discount();
                discount.setId(rs.getLong("id"));
                discount.setName(rs.getString("name"));
                discount.setAmount(rs.getDouble("amount"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find Discount by ID.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return discount;
    }

    @Override
    public void update(Discount discount) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1, discount.getName());
            stmt.setDouble(2, discount.getAmount());
            stmt.setLong(3, discount.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update Discount.", e);
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
            throw new RuntimeException("Unable to delete Discount.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}