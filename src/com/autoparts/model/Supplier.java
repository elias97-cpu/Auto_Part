package com.autoparts.model;

/**
 * Model class for Supplier.
 */
public class Supplier {
    private int supplierId;
    private String name;
    private String phone;
    private String email;

    public Supplier() {}

    public Supplier(int supplierId, String name, String phone) {
        this.supplierId = supplierId;
        this.name = name;
        this.phone = phone;
    }

    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Supplier [ID=" + supplierId + ", Name=" + name + ", Phone=" + phone + "]";
    }
}
