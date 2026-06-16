package com.autoparts.dao;

import com.autoparts.model.SparePart;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for SparePart entity.
 */
public class SparePartDAO implements BaseDAO<SparePart> {

    @Override
    public void add(SparePart part) {
        String sql = "INSERT INTO spare_parts (name, category, price, stock_quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, part.getName());
            stmt.setString(2, part.getCategory());
            stmt.setDouble(3, part.getPrice());
            stmt.setInt(4, part.getStockQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SparePart getById(int id) {
        String sql = "SELECT * FROM spare_parts WHERE part_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                SparePart part = new SparePart(rs.getInt("part_id"), rs.getString("name"), 
                                             rs.getDouble("price"), rs.getInt("stock_quantity"));
                part.setCategory(rs.getString("category"));
                return part;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SparePart> getAll() {
        List<SparePart> parts = new ArrayList<>();
        String sql = "SELECT * FROM spare_parts";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                SparePart part = new SparePart(rs.getInt("part_id"), rs.getString("name"), 
                                             rs.getDouble("price"), rs.getInt("stock_quantity"));
                part.setCategory(rs.getString("category"));
                parts.add(part);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parts;
    }

    @Override
    public void update(SparePart part) {
        String sql = "UPDATE spare_parts SET name=?, category=?, price=?, stock_quantity=? WHERE part_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, part.getName());
            stmt.setString(2, part.getCategory());
            stmt.setDouble(3, part.getPrice());
            stmt.setInt(4, part.getStockQuantity());
            stmt.setInt(5, part.getPartId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM spare_parts WHERE part_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
