package com.solvd.market.persistence.impl;

import com.solvd.market.domain.products.Discount;
import com.solvd.market.persistence.ConnectionPool;
import com.solvd.market.persistence.DiscountRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountRepositoryImpl implements DiscountRepository {
    private static final String CREATE_DISCOUNT_QUERY = "INSERT INTO discount (name, amount) VALUES (?, ?)";
    private static final String CREATE_CATEGORY_DISCOUNT_QUERY = "INSERT INTO category_discounts (category_id, discount_id) VALUES (?, ?)";
    private static final String FIND_ALL_DISCOUNTS_QUERY = "SELECT * FROM discount";
    private static final String FIND_DISCOUNT_BY_ID_QUERY = "SELECT * FROM discount WHERE id = ?";
    private static final String UPDATE_DISCOUNT_QUERY = "UPDATE discount SET name = ?, amount = ? WHERE id = ?";
    private static final String DELETE_DISCOUNT_QUERY = "DELETE FROM discount WHERE id = ?";
    private static final String DELETE_CATEGORY_DISCOUNT_QUERY = "DELETE FROM category_discounts WHERE discount_id = ?";
    private static final String FIND_DISCOUNTS_BY_CATEGORY_QUERY = "SELECT d.* FROM discount d JOIN category_discounts cd ON d.id = cd.discount_id WHERE cd.category_id = ?";

    @Override
    public void create(Discount discount, Long categoryId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement createDiscountStmt = connection.prepareStatement(CREATE_DISCOUNT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement createCategoryDiscountStmt = connection.prepareStatement(CREATE_CATEGORY_DISCOUNT_QUERY)) {

            createDiscountStmt.setString(1, discount.getName());
            createDiscountStmt.setDouble(2, discount.getAmount());
            createDiscountStmt.executeUpdate();

            try (ResultSet generatedKeys = createDiscountStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    discount.setId(generatedKeys.getLong(1));
                }
            }

            createCategoryDiscountStmt.setLong(1, categoryId);
            createCategoryDiscountStmt.setLong(2, discount.getId());
            createCategoryDiscountStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Discount.", e);
        }
    }

    @Override
    public List<Discount> findAll() {
        List<Discount> discounts = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(FIND_ALL_DISCOUNTS_QUERY);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Discount discount = new Discount();
                discount.setId(rs.getLong("id"));
                discount.setName(rs.getString("name"));
                discount.setAmount(rs.getDouble("amount"));
                discounts.add(discount);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to retrieve all Discounts.", e);
        }
        return discounts;
    }

    @Override
    public Discount findById(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(FIND_DISCOUNT_BY_ID_QUERY)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Discount discount = new Discount();
                    discount.setId(rs.getLong("id"));
                    discount.setName(rs.getString("name"));
                    discount.setAmount(rs.getDouble("amount"));
                    return discount;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to retrieve Discount by ID.", e);
        }
        return null;
    }

    @Override
    public void update(Discount discount) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(UPDATE_DISCOUNT_QUERY)) {

            stmt.setString(1, discount.getName());
            stmt.setDouble(2, discount.getAmount());
            stmt.setLong(3, discount.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Unable to update Discount.", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement deleteCategoryDiscountStmt = connection.prepareStatement(DELETE_CATEGORY_DISCOUNT_QUERY);
             PreparedStatement deleteDiscountStmt = connection.prepareStatement(DELETE_DISCOUNT_QUERY)) {

            // First, delete the entries from the category_discount table
            deleteCategoryDiscountStmt.setLong(1, id);
            deleteCategoryDiscountStmt.executeUpdate();

            // Then, delete the discount
            deleteDiscountStmt.setLong(1, id);
            deleteDiscountStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete Discount.", e);
        }
    }

    @Override
    public List<Discount> findByCategoryId(Long categoryId) {
        List<Discount> discounts = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(FIND_DISCOUNTS_BY_CATEGORY_QUERY)) {

            stmt.setLong(1, categoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Discount discount = new Discount();
                    discount.setId(rs.getLong("id"));
                    discount.setName(rs.getString("name"));
                    discount.setAmount(rs.getDouble("amount"));
                    discounts.add(discount);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to retrieve Discounts by Category ID.", e);
        }
        return discounts;
    }

    @Override
    public void addDiscountToCategory(Long discountId, Long categoryId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(CREATE_CATEGORY_DISCOUNT_QUERY)) {

            stmt.setLong(1, categoryId);
            stmt.setLong(2, discountId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Unable to add Discount to Category.", e);
        }
    }
}