# Auto Parts Link Ethiopia

## Project Overview
Auto Parts Link Ethiopia is a digital marketplace application designed to streamline the automotive spare parts industry in Ethiopia. The platform connects car owners, mechanics, and spare parts dealers in a centralized system to simplify the process of searching for and purchasing reliable automotive components.

The system addresses the challenges of a fragmented market by providing a trustworthy online platform where vendors can manage their inventory and buyers can ensure compatibility for specific car models, brands and part categories.

## Core Features
1. <strong><ins>User Management</ins></strong>: Supports distinct roles for Administrators and Customers with secure login capabilities.
2. <strong><ins>Product Management</ins></strong>: Comprehensive catalog for managing new, used, and refurbished spare parts.
3. <strong><ins>Inventory Control</ins></strong>: Tools for vendors to update stock levels and manage listing details.
4. <strong><ins>Search and Filtering</ins></strong>: Advanced capabilities to browse parts by brand, model compatibility, and category.
5. <strong><ins>Role Based Dashboards</ins></strong>: Custom interfaces for different user types to manage their respective tasks.

## Technical Architecture
The application is built using Java and follows the Model-View-Controller (MVC) architectural pattern along with the Data Access Object (DAO) pattern for database abstraction.

### Object-Oriented Design
1. <strong><ins>Inheritance</ins></strong>: Utilizes a base User class extended by Admin and Customer classes.
2. <strong><ins>Abstraction</ins></strong>: Implements a BaseDAO interface to define generic data operations.
3. <strong><ins>Polymorphism</ins></strong>: Employs method overriding for specific user details and entity representations.
4. <strong><ins>Encapsulation</ins></strong>: Protects data integrity through private fields and public getter/setter methods.

### File Structure
<pre>
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
</pre>
## Database Implementation
The system utilizes a MySQL database consisting of six primary tables:
1. <strong><ins>users</ins></strong>: Stores authentication and profile data.
2. <strong><ins>suppliers</ins></strong>: Manages vendor contact information.
3. <strong><ins>spare_parts</ins></strong>: Detailed catalog of all automotive parts.
4. <strong><ins>orders</ins></strong>: Records transaction history.
5. <strong><ins>order_items</ins></strong>: Manages the many-to-many relationship between orders and parts.
6. <strong><ins>deliveries</ins></strong>: Tracks the fulfillment status of purchases.

### Stored Procedures
- <strong><ins>GetLowStockParts</ins></strong>: Identifies items requiring restock based on a defined threshold.
- <strong><ins>GetUserOrderSummary</ins></strong>: Aggregates order history and metrics for specific users.

## Installation and Execution

### Prerequisites
1. Java Development Kit (JDK) 8 or higher installed on your system.
2. MySQL Server installed and running.
3. MySQL Connector/J driver (JAR file) for database connectivity.

### Step 1: Database Setup
1. Log in to your MySQL terminal or use a GUI like MySQL Workbench.
2. Run the SQL script located in the `db/` folder:
   ```sql
   source Desktop/Auto_Part/db/schema.sql;
   ```
   This will create the `auto_parts_db` database and all necessary tables.

### Step 2: Configure Database Connection
1. Open the file `src/com/autoparts/dao/DatabaseConnection.java`.
2. Update the `USER` and `PASSWORD` constants with your MySQL credentials.

### Step 3: Compilation
1. Open your terminal or command prompt.
2. Navigate to the project root directory:
   ```bash
   cd Desktop/Auto_Part
   ```
3. Compile all source files into a `bin` directory (include the MySQL connector JAR in your classpath):
   ```bash
   javac -d bin -cp ".;path/to/mysql-connector-java.jar" src/com/autoparts/*.java src/com/autoparts/model/*.java src/com/autoparts/dao/*.java src/com/autoparts/ui/*.java
   ```

### Step 4: Execution
1. Run the application:
   ```bash
   java -cp "bin;path/to/mysql-connector-java.jar" com.autoparts.Main
   ```

### Application Usage
- On startup, choose **Signup** (Option 2 or 3) to create your first User or Admin account.
- Once registered, use **Login** (Option 1) to access the role-specific dashboard.
- Admins can manage the catalog, while Customers can browse available spare parts.
