package com.solvd.market.persistence.impl;

import com.solvd.market.domain.orders.Order;
import com.solvd.market.persistence.ConnectionPool;
import com.solvd.market.persistence.OrderRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO orders (payment_id, shipment_id, cart_id) VALUES (?, ?, ?)";
    private static final String FIND_ALL_QUERY = "SELECT * FROM orders";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM orders WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE orders SET cart_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM orders WHERE id = ?";

    private static Order mapRow(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        return order;
    }

    @Override
    public void create(Order order) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO orders (cart_id) VALUES (?)", // Poprawiona kolejność!
                Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, order.getCart().getId()); // Wstawiamy tylko `cart_id`
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                order.setId(rs.getLong(1)); // Pobieramy ID utworzonego zamówienia
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Order.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to fetch Orders.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return orders;
    }

    @Override
    public Optional<Order> findById(Long id) {
        Order order = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                order = mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find Order by ID.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(order);
    }

    @Override
    public void update(Order order) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
            stmt.setLong(1, order.getPayment().getId());
            stmt.setLong(2, order.getShipment().getId());
            stmt.setLong(3, order.getCart().getId());
            stmt.setLong(4, order.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update Order.", e);
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
            throw new RuntimeException("Unable to delete Order.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}