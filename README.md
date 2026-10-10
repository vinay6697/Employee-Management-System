# Employee Management System

## 📌 Project Overview

The **Employee Management System** is a Java-based application developed using JDBC and PostgreSQL to manage employee records efficiently. It follows a layered architecture to separate business logic, database operations, and application interaction.

The project demonstrates practical implementation of CRUD operations, SQL queries, file handling, JDBC batch processing, and employee statistics.

## 🛠️ Technologies Used

- **Programming Language:** Java
- **Database:** PostgreSQL
- **Database Connectivity:** JDBC
- **IDE:** Eclipse
- **Version Control:** Git and GitHub

## ✨ Features

### 1. Employee Management
- Add new employee records.
- Update existing employee details.
- Delete employee records.
- Find an employee using employee ID.
- Retrieve all employee records.
- Search employees by email.
- Retrieve employees by department.
- Find employees within a specified salary range.

### 2. Employee Statistics
- Calculate the total number of employees.
- Find the highest employee salary.
- Find the lowest employee salary.
- Calculate the average employee salary.
- Count employees in each department using SQL `GROUP BY`.

### 3. File Handling
- Export employee details to a text file.
- Export department-specific employee details to separate files.
- Import employee records from a CSV file into PostgreSQL.

### 4. JDBC Batch Processing
- Use `PreparedStatement` for parameterized SQL queries.
- Import multiple employee records using `addBatch()` and `executeBatch()`.
- Use transaction management to commit successful operations or roll back failed imports.

### 5. Application Architecture
- Separate entity, DAO, service, utility, and main application layers.
- Use try-with-resources for automatic JDBC resource management.

##  Project Architecture

EmployeeManagementSystem/
│
├── src/
│   ├── entity/
│   │   └── Employee.java
│   │
│   ├── dao/
│   │   ├── EmployeeDAO.java
│   │   └── EmployeeDAOImpl.java
│   │
│   ├── service/
│   │   └── EmployeeService.java
│   │
│   ├── util/
│   │   └── DatabaseConnection.java
│   │
│   └── main/
│       └── Main.java
│
├── README.md
└── .gitignore
```

*Note: Adjust the package and file names above to match your actual project structure.*

## 🗄️ Employee Data Model

The Employee class contains the following fields:

| Field | Java Data Type | Description |
|---|---|---|
| employeeId | int | Unique employee identifier |
| employeeName | String | Employee name |
| email | String | Employee email address |
| phoneNumber | long | Employee phone number |
| department | String | Employee department |
| salary | double | Employee salary |
| joiningDate | LocalDate | Employee joining date |
| createdDate | LocalDateTime | Record creation timestamp |

## ⚙️ Database Configuration

### Prerequisites

- Java Development Kit (JDK)
- PostgreSQL
- PostgreSQL JDBC driver
- Eclipse IDE or another Java IDE

### Setup Instructions

1. Install and start PostgreSQL.
2. Create a database for the Employee Management System.
3. Create the `employee_details` table using the appropriate columns and constraints for your implementation.
4. Configure the database URL, username, and password in `DatabaseConnection.java`.
5. Add the PostgreSQL JDBC driver to the project classpath.
6. Run the main application class.

**Security note:** Do not commit actual database passwords or other credentials to GitHub.

## 📄 CSV Import Format

The CSV import feature reads employee records from a file and inserts them into PostgreSQL using JDBC batch processing.

Example:

```csv
employee_id,employee_name,email,phone_number,department,salary,joining_date,created_date
201,Rahul,rahul@gmail.com,9876543210,Java,45000.00,2025-01-15,2026-10-09 09:30:00
202,Priya,priya@gmail.com,9876543211,Testing,40000.00,2025-02-10,2026-10-09 09:35:00
```

The CSV column order and date formats must match the import implementation. Use employee IDs that do not already exist in the database.

## 🎯 Learning Outcomes

Through this project, I practised:

- Java object-oriented programming.
- JDBC connectivity and SQL CRUD operations.
- PreparedStatement and ResultSet.
- SQL aggregate functions and `GROUP BY`.
- DAO and service layer separation.
- File reading and writing in Java.
- JDBC batch processing.
- Transaction management and rollback.
- PostgreSQL database integration.
- Git and GitHub version control.

## 🚀 Future Enhancements

- Add a graphical user interface or REST API using Spring Boot.
- Introduce pagination for employee listings.
- Add advanced input validation and centralized exception handling.
- Generate employee reports in additional formats.

## 👨‍💻 Author

**Vinay**

Java Backend Development | JDBC | PostgreSQL | SQL

---

This project was developed as part of my practical learning in Java backend development.
