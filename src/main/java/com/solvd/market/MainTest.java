package com.solvd.market;
import java.sql.Connection;

import com.solvd.market.persistence.ConnectionPool;

public class MainTest {
    public static void main(String[] args) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            System.out.println("✅ Połączono z bazą danych!");
        } catch (Exception e) {
            System.err.println("❌ Błąd połączenia: " + e.getMessage());
        }
    }
}