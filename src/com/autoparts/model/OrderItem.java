package com.autoparts.model;

/**
 * Model class for Order Items.
 */
public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int partId;
    private int quantity;
    private double priceAtPurchase;

    public OrderItem() {}

    public OrderItem(int orderId, int partId, int quantity, double priceAtPurchase) {
        this.orderId = orderId;
        this.partId = partId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    public int getOrderItemId() { return orderItemId; }
    public void setOrderItemId(int orderItemId) { this.orderItemId = orderItemId; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getPartId() { return partId; }
    public void setPartId(int partId) { this.partId = partId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPriceAtPurchase() { return priceAtPurchase; }
    public void setPriceAtPurchase(double priceAtPurchase) { this.priceAtPurchase = priceAtPurchase; }
}
