package com.solvd.market.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static ConnectionPool instance; // Singleton instancja klasy
    private final List<Connection> connections; // Lista połączeń do ponownego użycia

    private ConnectionPool() {
        try {
            // Załadowanie sterownika JDBC
            Class.forName(Config.DRIVER.getValue());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find Driver class.", e);
        }

        // Pobranie rozmiaru puli połączeń z konfiguracji
        int poolSize = Integer.parseInt(Config.POOL_SIZE.getValue());
        connections = new ArrayList<>(poolSize);

        // Inicjalizacja puli połączeń
        for (int i = 0; i < poolSize; i++) {
            connections.add(createConnection());
        }
    }

    // Pobranie instancji ConnectionPool (Singleton)
    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    // Tworzy nowe połączenie z bazą danych
    private Connection createConnection() {
        try {
            return DriverManager.getConnection(
                    Config.URL.getValue(),
                    Config.USERNAME.getValue(),
                    Config.PASSWORD.getValue()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create connection.", e);
        }
    }

    // Pobiera dostępne połączenie z puli
    public synchronized Connection getConnection() {
        while (connections.isEmpty()) {
            try {
                wait(); // Czeka na zwolnienie połączenia
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted while waiting for connection.", e);
            }
        }
        return connections.remove(connections.size() - 1);
    }

    // Zwraca połączenie do puli i powiadamia czekające wątki
    public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
        notifyAll(); // Powiadomienie wątków oczekujących na połączenie
    }
}
