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
    private UserDAO userDAO = new UserDAO();
    private SupplierDAO supplierDAO = new SupplierDAO();

    // ... (previous methods start, login, signup)

    private void showAdminDashboard(Admin admin) {
        while (true) {
            System.out.println("\n--- Admin Dashboard ---");
            System.out.println(admin.getDetails());
            System.out.println("1. Manage Inventory (Parts)");
            System.out.println("2. Manage Suppliers");
            System.out.println("3. Logout");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            if (choice == 1) {
                manageInventory();
            } else if (choice == 2) {
                manageSuppliers();
            } else if (choice == 3) {
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
