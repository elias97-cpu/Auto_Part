package com.autoparts.model;

/**
 * Customer class extending User.
 * Demonstrates Inheritance and Polymorphism.
 */
public class Customer extends User {
    
    public Customer() {
        super();
        setRole("CUSTOMER");
    }

    public Customer(int userId, String username, String email) {
        super(userId, username, email, "CUSTOMER");
    }

    @Override
    public String getDetails() {
        return "Customer User: [ID=" + getUserId() + ", Name=" + getUsername() + ", Email=" + getEmail() + "]";
    }
}
