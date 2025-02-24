package com.solvd.market.persistence.impl;

import com.solvd.market.domain.orders.Cart;
import com.solvd.market.domain.products.Product;
import com.solvd.market.persistence.CartRepository;
import com.solvd.market.persistence.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartRepositoryImpl implements CartRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO cart (user_id) VALUES (?)";
    private static final String FIND_ALL_QUERY = "SELECT * FROM cart";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM cart WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE cart SET user_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM cart WHERE id = ?";
    private static final String INSERT_CART_PRODUCT_QUERY = "INSERT INTO cart_products (cart_id, product_id) VALUES (?, ?)";
    private static final String DELETE_CART_PRODUCTS_QUERY = "DELETE FROM cart_products WHERE cart_id = ?";

    private static Cart mapRow(ResultSet rs) throws SQLException {
        Cart cart = new Cart();
        cart.setId(rs.getLong("id"));
        return cart;
    }

    @Override
    public void create(Cart cart) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, cart.getUser().getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cart.setId(rs.getLong(1));
            }

            // Powiązanie produktów z koszykiem
            if (cart.getProducts() != null) {
                for (Product product : cart.getProducts()) {
                    try (PreparedStatement stmtProduct = connection.prepareStatement(INSERT_CART_PRODUCT_QUERY)) {
                        stmtProduct.setLong(1, cart.getId());
                        stmtProduct.setLong(2, product.getId());
                        stmtProduct.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Cart.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Cart> findAll() {
        List<Cart> carts = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                carts.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to fetch Carts.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return carts;
    }

    @Override
    public Optional<Cart> findById(Long id) {
        Cart cart = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cart = mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find Cart by ID.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(cart);
    }

    @Override
    public void update(Cart cart) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
            stmt.setLong(1, cart.getUser().getId());
            stmt.setLong(2, cart.getId());
            stmt.executeUpdate();

            // Usuwanie starych produktów
            try (PreparedStatement deleteStmt = connection.prepareStatement(DELETE_CART_PRODUCTS_QUERY)) {
                deleteStmt.setLong(1, cart.getId());
                deleteStmt.executeUpdate();
            }

            // Wstawianie nowych produktów
            for (Product product : cart.getProducts()) {
                try (PreparedStatement stmtProduct = connection.prepareStatement(INSERT_CART_PRODUCT_QUERY)) {
                    stmtProduct.setLong(1, cart.getId());
                    stmtProduct.setLong(2, product.getId());
                    stmtProduct.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to update Cart.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            // Najpierw usuń powiązane produkty
            try (PreparedStatement deleteStmt = connection.prepareStatement(DELETE_CART_PRODUCTS_QUERY)) {
                deleteStmt.setLong(1, id);
                deleteStmt.executeUpdate();
            }

            // Następnie usuń koszyk
            try (PreparedStatement stmt = connection.prepareStatement(DELETE_QUERY)) {
                stmt.setLong(1, id);
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete Cart.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}