package com.autoparts.model;

/**
 * Admin class extending User.
 * Demonstrates Inheritance and Polymorphism.
 */
public class Admin extends User {
    
    public Admin() {
        super();
        setRole("ADMIN");
    }

    public Admin(int userId, String username, String email) {
        super(userId, username, email, "ADMIN");
    }

    @Override
    public String getDetails() {
        return "Admin User: [ID=" + getUserId() + ", Name=" + getUsername() + ", Email=" + getEmail() + "]";
    }
}
