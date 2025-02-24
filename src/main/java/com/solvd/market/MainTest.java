package com.solvd.market;

import com.solvd.market.persistence.ConnectionPool;

import java.sql.Connection;

public class MainTest {
    public static void main(String[] args) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            System.out.println("✅ Połączono z bazą danych!");
        } catch (Exception e) {
            System.err.println("❌ Błąd połączenia: " + e.getMessage());
        }
    }
}