package com.solvd.market.persistence.impl;

import com.solvd.market.domain.MarketPlace;
import com.solvd.market.domain.users.User;
import com.solvd.market.persistence.ConnectionPool;
import com.solvd.market.persistence.MarketPlaceRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarketPlaceRepositoryImpl implements MarketPlaceRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO market_places (name) VALUES (?)";
    private static final String FIND_ALL_QUERY = "SELECT mp.id as marketplace_id, mp.name as marketplace_name, " +
            "u.id as user_id, u.name as user_name " +
            "FROM market_places mp " +
            "LEFT JOIN Users u ON mp.id = u.market_places_id";
    private static final String FIND_BY_ID_QUERY = FIND_ALL_QUERY + " WHERE mp.id = ?";
    private static final String UPDATE_QUERY = "UPDATE market_places SET name = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM market_places WHERE id = ?";

    private static List<MarketPlace> mapMarketPlaces(ResultSet rs) throws SQLException {
        List<MarketPlace> marketPlaces = new ArrayList<>();
        while (rs.next()) {
            Long id = rs.getLong("marketplace_id");

            MarketPlace marketPlace = findById(id, marketPlaces);
            marketPlace.setName(rs.getString("marketplace_name"));

            List<User> users = UserRepositoryImpl.mapRow(rs, marketPlace.getUsers());
            marketPlace.setUsers(users);
        }
        return marketPlaces;
    }

    private static MarketPlace findById(Long id, List<MarketPlace> marketPlaces) {
        return marketPlaces.stream()
                .filter(mp -> mp.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    MarketPlace newMarketPlace = new MarketPlace();
                    newMarketPlace.setId(id);
                    marketPlaces.add(newMarketPlace);
                    return newMarketPlace;
                });
    }

    @Override
    public void create(MarketPlace marketPlace) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, marketPlace.getName());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                marketPlace.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create MarketPlace.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<MarketPlace> findAll() {
        List<MarketPlace> marketPlaces = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            marketPlaces = mapMarketPlaces(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to fetch MarketPlaces.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return marketPlaces;
    }

    @Override
    public MarketPlace findById(Long id) {
        MarketPlace marketPlace = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            List<MarketPlace> marketPlaces = mapMarketPlaces(rs);
            if (!marketPlaces.isEmpty()) {
                marketPlace = marketPlaces.get(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find MarketPlace by ID.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return marketPlace;
    }

    @Override
    public void update(MarketPlace marketPlace) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1, marketPlace.getName());
            stmt.setLong(2, marketPlace.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update MarketPlace.", e);
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
            throw new RuntimeException("Unable to delete MarketPlace.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}