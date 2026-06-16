package com.autoparts.model;

/**
 * Model class for Spare Parts.
 * Demonstrates Encapsulation.
 */
public class SparePart {
    private int partId;
    private String name;
    private String category;
    private String brand;
    private String modelCompatibility;
    private double price;
    private int stockQuantity;
    private String condition;
    private int supplierId;
    private String description;

    public SparePart() {}

    public SparePart(int partId, String name, double price, int stockQuantity) {
        this.partId = partId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters
    public int getPartId() { return partId; }
    public void setPartId(int partId) { this.partId = partId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModelCompatibility() { return modelCompatibility; }
    public void setModelCompatibility(String modelCompatibility) { this.modelCompatibility = modelCompatibility; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "SparePart [ID=" + partId + ", Name=" + name + ", Price=" + price + ", Stock=" + stockQuantity + "]";
    }
}
