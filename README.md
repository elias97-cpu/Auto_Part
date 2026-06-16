# Auto Parts Link Ethiopia

## Project Overview
Auto Parts Link Ethiopia is a digital marketplace application designed to streamline the automotive spare parts industry in Ethiopia. The platform connects car owners, mechanics, and spare parts dealers in a centralized system to simplify the process of searching for and purchasing reliable automotive components.

The system addresses the challenges of a fragmented market by providing a trustworthy online platform where vendors can manage their inventory and buyers can ensure compatibility for specific car models, brands, and part categories.

## Core Features
1. User Management: Supports distinct roles for Administrators and Customers with secure login capabilities.
2. Product Management: Comprehensive catalog for managing new, used, and refurbished spare parts.
3. Inventory Control: Tools for vendors to update stock levels and manage listing details.
4. Search and Filtering: Advanced capabilities to browse parts by brand, model compatibility, and category.
5. Role-Based Dashboards: Custom interfaces for different user types to manage their respective tasks.

## Technical Architecture
The application is built using Java and follows the Model-View-Controller (MVC) architectural pattern along with the Data Access Object (DAO) pattern for database abstraction.

### Object-Oriented Design
1. Inheritance: Utilizes a base User class extended by Admin and Customer classes.
2. Abstraction: Implements a BaseDAO interface to define generic data operations.
3. Polymorphism: Employs method overriding for specific user details and entity representations.
4. Encapsulation: Protects data integrity through private fields and public getter/setter methods.

### File Structure
Auto_Part/
├── db/
│   └── schema.sql (Database definition and stored procedures)
└── src/
    └── com/autoparts/
        ├── Main.java (Application entry point)
        ├── dao/ (Database access layer)
        │   ├── BaseDAO.java
        │   ├── DatabaseConnection.java
        │   └── SparePartDAO.java
        ├── model/ (Data entities)
        │   ├── User.java
        │   ├── Admin.java
        │   ├── Customer.java
        │   ├── SparePart.java
        │   ├── Supplier.java
        │   └── Order.java
        ├── ui/ (User interface layer)
        │   └── ConsoleUI.java
        └── test/ (Verification scripts)
            └── SystemTest.java

## Database Implementation
The system utilizes a MySQL database consisting of six primary tables:
1. users: Stores authentication and profile data.
2. suppliers: Manages vendor contact information.
3. spare_parts: Detailed catalog of all automotive parts.
4. orders: Records transaction history.
5. order_items: Manages the many-to-many relationship between orders and parts.
6. deliveries: Tracks the fulfillment status of purchases.

### Stored Procedures
- GetLowStockParts: Identifies items requiring restock based on a defined threshold.
- GetUserOrderSummary: Aggregates order history and metrics for specific users.

## Installation and Execution
1. Database Setup: Execute the script located in db/schema.sql on your MySQL server.
2. Dependencies: Ensure the MySQL Connector/J driver is included in your project classpath.
3. Compilation: Compile all source files using the Java compiler.
4. Execution: Run the com.autoparts.Main class to start the application.
