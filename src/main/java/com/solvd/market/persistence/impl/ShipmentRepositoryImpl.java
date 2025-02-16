package com.solvd.market.persistence.impl;

import com.solvd.market.domain.shipments.Shipment;
import com.solvd.market.persistence.ConnectionPool;
import com.solvd.market.persistence.ShipmentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShipmentRepositoryImpl implements ShipmentRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO shipment (orders_id, courier) VALUES (?, ?)";
    private static final String FIND_ALL_QUERY = "SELECT id, orders_id, courier FROM shipment";
    private static final String FIND_BY_ID_QUERY = FIND_ALL_QUERY + " WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE shipment SET courier = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM shipment WHERE id = ?";

    @Override
    public void create(Shipment shipment, Long orderId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, orderId);
            stmt.setString(2, shipment.getCourier());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                shipment.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Shipment.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Shipment> findAll() {
        List<Shipment> shipments = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Shipment shipment = new Shipment();
                shipment.setId(rs.getLong("id"));
                shipment.setCourier(rs.getString("courier"));
                shipments.add(shipment);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to fetch Shipments.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return shipments;
    }

    @Override
    public Shipment findById(Long id) {
        Shipment shipment = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                shipment = new Shipment();
                shipment.setId(rs.getLong("id"));
                shipment.setCourier(rs.getString("courier"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find Shipment by ID.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return shipment;
    }

    @Override
    public void update(Shipment shipment) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1, shipment.getCourier());
            stmt.setLong(2, shipment.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update Shipment.", e);
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
            throw new RuntimeException("Unable to delete Shipment.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}