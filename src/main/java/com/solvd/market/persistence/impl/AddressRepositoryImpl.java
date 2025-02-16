package com.solvd.market.persistence.impl;

import com.solvd.market.domain.shipments.Address;
import com.solvd.market.persistence.AddressRepository;
import com.solvd.market.persistence.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressRepositoryImpl implements AddressRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO address (shipment_id, street, home_nr, flat_nr, city, post_code) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL_QUERY = "SELECT id, shipment_id, street, home_nr, flat_nr, city, post_code FROM address";
    private static final String FIND_BY_ID_QUERY = FIND_ALL_QUERY + " WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE address SET street = ?, home_nr = ?, flat_nr = ?, city = ?, post_code = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM address WHERE id = ?";

    @Override
    public void create(Address address, Long shipmentId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, shipmentId);
            stmt.setString(2, address.getStreet());
            stmt.setInt(3, address.getHomeNr());
            stmt.setInt(4, address.getFlatNr());
            stmt.setString(5, address.getCity());
            stmt.setString(6, address.getPostCode());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                address.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Address.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getLong("id"));
                address.setStreet(rs.getString("street"));
                address.setHomeNr(rs.getInt("home_nr"));
                address.setFlatNr(rs.getInt("flat_nr"));
                address.setCity(rs.getString("city"));
                address.setPostCode(rs.getString("post_code"));
                addresses.add(address);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to fetch Addresses.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return addresses;
    }

    @Override
    public Address findById(Long id) {
        Address address = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                address = new Address();
                address.setId(rs.getLong("id"));
                address.setStreet(rs.getString("street"));
                address.setHomeNr(rs.getInt("home_nr"));
                address.setFlatNr(rs.getInt("flat_nr"));
                address.setCity(rs.getString("city"));
                address.setPostCode(rs.getString("post_code"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find Address by ID.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return address;
    }

    @Override
    public void update(Address address) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1, address.getStreet());
            stmt.setInt(2, address.getHomeNr());
            stmt.setInt(3, address.getFlatNr());
            stmt.setString(4, address.getCity());
            stmt.setString(5, address.getPostCode());
            stmt.setLong(6, address.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update Address.", e);
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
            throw new RuntimeException("Unable to delete Address.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}