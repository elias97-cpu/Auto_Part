package com.autoparts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class to handle MySQL database connections.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/auto_parts_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password"; // To be configured

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
