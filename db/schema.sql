CREATE DATABASE IF NOT EXISTS auto_parts_db;
USE auto_parts_db;

-- 1. Users table (Authentication & Roles)
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    role ENUM('ADMIN', 'CUSTOMER', 'VENDOR') DEFAULT 'CUSTOMER',
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Suppliers table
CREATE TABLE IF NOT EXISTS suppliers (
    supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    contact_person VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100),
    address TEXT
);

-- 3. Spare Parts table
CREATE TABLE IF NOT EXISTS spare_parts (
    part_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    brand VARCHAR(50),
    model_compatibility VARCHAR(255),
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT DEFAULT 0,
    condition_type ENUM('NEW', 'USED', 'REFURBISHED') DEFAULT 'NEW',
    supplier_id INT,
    description TEXT,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);

-- 4. Orders table
CREATE TABLE IF NOT EXISTS orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL,
    payment_method VARCHAR(50),
    status ENUM('PENDING', 'PAID', 'SHIPPED', 'DELIVERED', 'CANCELLED') DEFAULT 'PENDING',
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- 5. Order Items table (Many-to-Many link between Orders and Spare Parts)
CREATE TABLE IF NOT EXISTS order_items (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    part_id INT NOT NULL,
    quantity INT NOT NULL,
    price_at_purchase DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (part_id) REFERENCES spare_parts(part_id)
);

-- 6. Deliveries table
CREATE TABLE IF NOT EXISTS deliveries (
    delivery_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    delivery_address TEXT NOT NULL,
    delivery_status VARCHAR(50) DEFAULT 'IN_TRANSIT',
    estimated_delivery_date DATE,
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

-- STORED PROCEDURES

-- Procedure 1: Low Stock Alert (Lists parts with stock below a threshold)
DELIMITER //
CREATE PROCEDURE GetLowStockParts(IN threshold INT)
BEGIN
    SELECT part_id, name, stock_quantity 
    FROM spare_parts 
    WHERE stock_quantity < threshold;
END //
DELIMITER ;

-- Procedure 2: User Order Summary
DELIMITER //
CREATE PROCEDURE GetUserOrderSummary(IN input_user_id INT)
BEGIN
    SELECT o.order_id, o.order_date, o.total_amount, o.status, COUNT(oi.order_item_id) as item_count
    FROM orders o
    LEFT JOIN order_items oi ON o.order_id = oi.order_id
    WHERE o.user_id = input_user_id
    GROUP BY o.order_id;
END //
DELIMITER ;
