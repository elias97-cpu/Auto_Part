package com.autoparts.model;

import java.time.LocalDateTime;

/**
 * Model class for Orders.
 */
public class Order {
    private int orderId;
    private int userId;
    private LocalDateTime orderDate;
    private double totalAmount;
    private String status;

    public Order() {}

    public Order(int orderId, int userId, double totalAmount, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Order [ID=" + orderId + ", UserID=" + userId + ", Amount=" + totalAmount + ", Status=" + status + "]";
    }
}
