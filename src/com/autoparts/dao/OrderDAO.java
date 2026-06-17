package com.autoparts.dao;

import com.autoparts.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for Orders and OrderItems.
 */
public class OrderDAO implements BaseDAO<Order> {

    @Override
    public void add(Order order) {
        String sql = "INSERT INTO orders (user_id, total_amount, payment_method, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getUserId());
            stmt.setDouble(2, order.getTotalAmount());
            stmt.setString(3, "Mobile Payment"); // Default
            stmt.setString(4, "PENDING");
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                order.setOrderId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrderItem(OrderItem item) {
        String sql = "INSERT INTO order_items (order_id, part_id, quantity, price_at_purchase) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getOrderId());
            stmt.setInt(2, item.getPartId());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getPriceAtPurchase());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getById(int id) { return null; }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Order o = new Order(rs.getInt("order_id"), rs.getInt("user_id"), 
                                  rs.getDouble("total_amount"), rs.getString("status"));
                orders.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void update(Order order) {}

    @Override
    public void delete(int id) {}
}
