package com.autoparts.ui;

import com.autoparts.model.*;
import com.autoparts.dao.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Console UI for User Interaction.
 * Fully integrated with DAO layer, Cart, and Order logic.
 */
public class ConsoleUI {
    private Scanner scanner = new Scanner(System.in);
    private SparePartDAO partDAO = new SparePartDAO();
    private UserDAO userDAO = new UserDAO();
    private SupplierDAO supplierDAO = new SupplierDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private List<SparePart> cart = new ArrayList<>();

    public void start() {
        while (true) {
            System.out.println("\n=== Welcome to Auto Parts Link Ethiopia ===");
            System.out.println("1. Login");
            System.out.println("2. Signup (Customer)");
            System.out.println("3. Signup (Admin)");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            
            if (!scanner.hasNextInt()) break;
            int choice = scanner.nextInt();
            if (choice == 1) {
                login();
            } else if (choice == 2) {
                signup("CUSTOMER");
            } else if (choice == 3) {
                signup("ADMIN");
            } else {
                System.out.println("Goodbye!");
                break;
            }
        }
    }

    private void login() {
        System.out.println("\n--- Login ---");
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        User user = userDAO.authenticate(username, password);
        if (user != null) {
            System.out.println("Login Successful! Welcome, " + user.getUsername());
            if (user instanceof Admin) {
                showAdminDashboard((Admin) user);
            } else {
                showCustomerDashboard((Customer) user);
            }
        } else {
            System.out.println("Error: Invalid username or password.");
        }
    }

    private void signup(String role) {
        System.out.println("\n--- Signup as " + role + " ---");
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        System.out.print("Email: ");
        String email = scanner.next();

        User newUser;
        if ("ADMIN".equals(role)) {
            newUser = new Admin(0, username, email);
        } else {
            newUser = new Customer(0, username, email);
        }
        newUser.setPassword(password);
        userDAO.add(newUser);
        System.out.println(role + " account created successfully!");
    }

    private void showAdminDashboard(Admin admin) {
        while (true) {
            System.out.println("\n--- Admin Dashboard ---");
            System.out.println(admin.getDetails());
            System.out.println("1. Manage Inventory (Parts)");
            System.out.println("2. Manage Suppliers");
            System.out.println("3. View All Orders");
            System.out.println("4. Logout");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            if (choice == 1) {
                manageInventory();
            } else if (choice == 2) {
                manageSuppliers();
            } else if (choice == 3) {
                viewAllOrders();
            } else if (choice == 4) {
                break;
            }
        }
    }

    private void manageInventory() {
        System.out.println("\n--- Inventory Management ---");
        System.out.println("1. View All Parts");
        System.out.println("2. Add New Part");
        System.out.println("3. Update Part Price/Stock");
        System.out.println("4. Delete Part");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: viewAllParts(); break;
            case 2: addNewPart(); break;
            case 3: updatePart(); break;
            case 4: deletePart(); break;
        }
    }

    private void manageSuppliers() {
        System.out.println("\n--- Supplier Management ---");
        System.out.println("1. View All Suppliers");
        System.out.println("2. Add New Supplier");
        System.out.println("3. Update Supplier");
        System.out.println("4. Delete Supplier");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: viewSuppliers(); break;
            case 2: addSupplier(); break;
            case 3: updateSupplier(); break;
            case 4: deleteSupplier(); break;
        }
    }

    private void updatePart() {
        System.out.print("Enter Part ID to update: ");
        int id = scanner.nextInt();
        SparePart part = partDAO.getById(id);
        if (part != null) {
            System.out.print("New Price (current: " + part.getPrice() + "): ");
            part.setPrice(scanner.nextDouble());
            System.out.print("New Stock (current: " + part.getStockQuantity() + "): ");
            part.setStockQuantity(scanner.nextInt());
            partDAO.update(part);
            System.out.println("Part updated.");
        } else {
            System.out.println("Part not found.");
        }
    }

    private void viewSuppliers() {
        List<Supplier> suppliers = supplierDAO.getAll();
        for (Supplier s : suppliers) System.out.println(s);
    }

    private void addSupplier() {
        System.out.print("Supplier Name: ");
        String name = scanner.next();
        System.out.print("Phone: ");
        String phone = scanner.next();
        Supplier s = new Supplier(0, name, phone);
        supplierDAO.add(s);
        System.out.println("Supplier added.");
    }

    private void updateSupplier() {
        System.out.print("Enter Supplier ID to update: ");
        int id = scanner.nextInt();
        Supplier s = supplierDAO.getById(id);
        if (s != null) {
            System.out.print("New Phone: ");
            s.setPhone(scanner.next());
            supplierDAO.update(s);
            System.out.println("Supplier updated.");
        }
    }

    private void deleteSupplier() {
        System.out.print("Enter Supplier ID to delete: ");
        int id = scanner.nextInt();
        supplierDAO.delete(id);
        System.out.println("Supplier deleted.");
    }

    private void viewAllOrders() {
        System.out.println("\n--- Global Order History ---");
        List<Order> orders = orderDAO.getAll();
        for (Order o : orders) System.out.println(o);
    }

    private void showCustomerDashboard(Customer customer) {
        while (true) {
            System.out.println("\n--- Customer Dashboard ---");
            System.out.println(customer.getDetails());
            System.out.println("1. Browse All Parts");
            System.out.println("2. Search Parts");
            System.out.println("3. Add to Cart");
            System.out.println("4. View Cart & Checkout");
            System.out.println("5. Logout");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            if (choice == 1) {
                viewAllParts();
            } else if (choice == 2) {
                searchParts();
            } else if (choice == 3) {
                addToCart();
            } else if (choice == 4) {
                viewCartAndCheckout(customer);
            } else if (choice == 5) {
                break;
            }
        }
    }

    private void searchParts() {
        System.out.print("Enter search term (name or category): ");
        String query = scanner.next();
        List<SparePart> results = partDAO.search(query);
        System.out.println("\n--- Search Results ---");
        for (SparePart p : results) System.out.println(p);
    }

    private void addToCart() {
        System.out.print("Enter Part ID to add to cart: ");
        int id = scanner.nextInt();
        SparePart p = partDAO.getById(id);
        if (p != null) {
            cart.add(p);
            System.out.println(p.getName() + " added to cart.");
        } else {
            System.out.println("Part not found.");
        }
    }

    private void viewCartAndCheckout(Customer customer) {
        System.out.println("\n--- Your Shopping Cart ---");
        double total = 0;
        for (SparePart p : cart) {
            System.out.println("- " + p.getName() + " ($" + p.getPrice() + ")");
            total += p.getPrice();
        }
        System.out.println("Total: $" + total);

        if (cart.isEmpty()) return;

        System.out.print("Proceed to Checkout? (y/n): ");
        String ans = scanner.next();
        if (ans.equalsIgnoreCase("y")) {
            Order order = new Order(0, customer.getUserId(), total, "PAID");
            orderDAO.add(order);
            for (SparePart p : cart) {
                OrderItem item = new OrderItem(order.getOrderId(), p.getPartId(), 1, p.getPrice());
                orderDAO.addOrderItem(item);
            }
            System.out.println("Checkout successful! Order #" + order.getOrderId() + " placed.");
            cart.clear();
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
        System.out.println("Part added successfully.");
    }

    private void deletePart() {
        System.out.print("Enter Part ID to delete: ");
        int id = scanner.nextInt();
        partDAO.delete(id);
        System.out.println("Delete command sent to DB.");
    }
}
