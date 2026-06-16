package com.autoparts.ui;

import com.autoparts.model.*;
import com.autoparts.dao.*;
import java.util.List;
import java.util.Scanner;

/**
 * Basic Console UI for User Interaction.
 * Fully integrated with DAO layer.
 */
public class ConsoleUI {
    private Scanner scanner = new Scanner(System.in);
    private SparePartDAO partDAO = new SparePartDAO();

    public void start() {
        while (true) {
            System.out.println("\n=== Welcome to Auto Parts Link Ethiopia ===");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choice: ");
            
            if (!scanner.hasNextInt()) break;
            int choice = scanner.nextInt();
            if (choice == 1) {
                login();
            } else {
                System.out.println("Goodbye!");
                break;
            }
        }
    }

    private void login() {
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        // Demonstration Login logic
        if (username.equalsIgnoreCase("admin")) {
            showAdminDashboard(new Admin(1, "admin", "admin@autoparts.com"));
        } else {
            showCustomerDashboard(new Customer(2, username, username + "@example.com"));
        }
    }

    private void showAdminDashboard(Admin admin) {
        while (true) {
            System.out.println("\n--- Admin Dashboard ---");
            System.out.println(admin.getDetails());
            System.out.println("1. View Inventory");
            System.out.println("2. Add New Part");
            System.out.println("3. Delete Part");
            System.out.println("4. Logout");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            if (choice == 1) {
                viewAllParts();
            } else if (choice == 2) {
                addNewPart();
            } else if (choice == 3) {
                deletePart();
            } else if (choice == 4) {
                break;
            }
        }
    }

    private void showCustomerDashboard(Customer customer) {
        while (true) {
            System.out.println("\n--- Customer Dashboard ---");
            System.out.println(customer.getDetails());
            System.out.println("1. Browse Parts Catalog");
            System.out.println("2. Logout");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            if (choice == 1) {
                viewAllParts();
            } else if (choice == 2) {
                break;
            }
        }
    }

    private void viewAllParts() {
        System.out.println("\n--- Spare Parts Catalog ---");
        List<SparePart> parts = partDAO.getAll();
        if (parts.isEmpty()) {
            System.out.println("[DB Note: No parts found or Database not connected]");
        } else {
            for (SparePart p : parts) {
                System.out.println(p);
            }
        }
    }

    private void addNewPart() {
        System.out.print("Part Name: ");
        String name = scanner.next();
        System.out.print("Category: ");
        String cat = scanner.next();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();

        SparePart p = new SparePart(0, name, price, stock);
        p.setCategory(cat);
        partDAO.add(p);
        System.out.println("Part added successfully (to DB if connected).");
    }

    private void deletePart() {
        System.out.print("Enter Part ID to delete: ");
        int id = scanner.nextInt();
        partDAO.delete(id);
        System.out.println("Delete command sent to DB.");
    }
}
