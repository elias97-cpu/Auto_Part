package com.autoparts.dao;

import com.autoparts.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for User entities.
 * Handles Admin and Customer persistence.
 */
public class UserDAO implements BaseDAO<User> {

    @Override
    public void add(User user) {
        String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                User user;
                if ("ADMIN".equals(role)) {
                    user = new Admin(rs.getInt("user_id"), rs.getString("username"), rs.getString("email"));
                } else {
                    user = new Customer(rs.getInt("user_id"), rs.getString("username"), rs.getString("email"));
                }
                user.setRole(role);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getById(int id) {
        // Implementation for getting a user by ID
        return null; 
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>();
    }

    @Override
    public void update(User user) {
        // Implementation for updating user info
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
