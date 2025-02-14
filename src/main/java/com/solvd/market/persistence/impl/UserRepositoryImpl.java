package com.solvd.market.persistence.impl;

import com.solvd.market.domain.users.User;
import com.solvd.market.persistence.ConnectionPool;
import com.solvd.market.persistence.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO Users (name, surname, email, password, phone, type, active) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL_QUERY = "SELECT * FROM Users";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM Users WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE Users SET name = ?, surname = ?, email = ?, password = ?, phone = ?, type = ?, active = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Users WHERE id = ?";

    public static List<User> mapRow(ResultSet rs, List<User> users) {
        return users;
    }

    private static User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setPhone(rs.getString("phone"));
        user.setType(rs.getBoolean("type"));
        user.setActive(rs.getBoolean("active"));
        return user;
    }

    @Override
    public void create(User user) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getPhone());
            stmt.setBoolean(6, user.getType());
            stmt.setBoolean(7, user.getActive());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create User.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to fetch Users.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find User by ID.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void update(User user) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getPhone());
            stmt.setBoolean(6, user.getType());
            stmt.setBoolean(7, user.getActive());
            stmt.setLong(8, user.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update User.", e);
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
            throw new RuntimeException("Unable to delete User.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
