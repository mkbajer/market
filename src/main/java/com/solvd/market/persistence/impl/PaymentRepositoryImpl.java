package com.solvd.market.persistence.impl;

import com.solvd.market.domain.payments.Payment;
import com.solvd.market.persistence.ConnectionPool;
import com.solvd.market.persistence.PaymentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepositoryImpl implements PaymentRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO payment (orders_id, type) VALUES (?, ?)";
    private static final String FIND_ALL_QUERY = "SELECT id, orders_id, type FROM payment";
    private static final String FIND_BY_ID_QUERY = FIND_ALL_QUERY + " WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE payment SET type = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM payment WHERE id = ?";

    @Override
    public void create(Payment payment, Long orderId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, orderId);
            stmt.setString(2, payment.getType());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                payment.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Payment.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getLong("id"));
                payment.setType(rs.getString("type"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to fetch Payments.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return payments;
    }

    @Override
    public Payment findById(Long id) {
        Payment payment = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                payment = new Payment();
                payment.setId(rs.getLong("id"));
                payment.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find Payment by ID.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return payment;
    }

    @Override
    public void update(Payment payment) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1, payment.getType());
            stmt.setLong(2, payment.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update Payment.", e);
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
            throw new RuntimeException("Unable to delete Payment.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
