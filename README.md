# Application: Carbon Emission Calculator 

This Java application provides a command-line interface for managing various database operations, including user creation, password hashing, and CRUD (Create, Read, Update, Delete) operations on multiple tables. The application is built with a modular structure, separating concerns into different classes and interfaces for better maintainability and scalability.

## Features

- User Management: Create users with hashed passwords and validate user passwords.
- CRUD Operations: Perform insert, update, read, and delete operations on different database tables.
- Custom SQL Operations: Execute predefined SQL operations for data analysis.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven
- MySQL Database (or any other database supported by JDBC)

### Setting Up

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/your-repo.git
    cd your-repo
    ```

2. **Configure the Database:**

   Update the database configuration in `UserDAOImpl.java`:

    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/yourdb";
    private static final String USER = "yourusername";
    private static final String PASSWORD = "yourpassword";
    ```

3. **Build the Project:**

    ```bash
    mvn clean install
    ```

4. **Run the Application:**

    ```bash
    java -cp target/your-artifact-id.jar com.example.Main
    ```

## Usage

Upon running the application, you will be presented with a command-line menu to navigate through different functionalities.

### Main Menu

- `1`: Manage `activity` table
- `2`: Manage `emission_factors` table
- `3`: Manage `emission_goals` table
- `4`: Manage `user_emissions` table
- `5`: Manage `users` table
- `6`: Perform other SQL operations
- `0`: Exit the application

### Table Operations Menu

For each table, you can perform the following operations:

- `1`: Insert a new entry
- `2`: Update an existing entry
- `3`: Read entries
- `4`: Delete an entry
- `5`: Return to the previous menu

### User Management

- **Create User:**

  Create a new user with a username and password. The password is hashed and stored securely in the database.

- **Validate Password:**

  Check if the provided password for a user is correct.


------------------------------------------


# Assignment: Carbon Emission Calculator

## Overview
In this assignment, you will develop a Java application that calculates and tracks the carbon emissions of various activities. The application will use PostgreSQL for data storage and JDBC for database interaction. This project aims to familiarize you with database operations, JDBC, and the integration of Java applications with databases.

## Objectives
- Understand and apply CRUD operations in PostgreSQL.
- Use JDBC for database connectivity in a Java application.
- Design and implement a database schema for a real-world application.
- Practice using SQL features like joins, subqueries, custom enum types, and aggregate functions.

## Project Setup

### Setting Up the Database Environment
- Install PostgreSQL and set up a new database named `carbon_emission`.
- Ensure that your PostgreSQL server is running and accessible.

### Creating Tables and Relationships
You will need to create tables for `Activities`, `Emission_Factors`, and `User_Emissions`. Make sure to define primary keys and foreign keys appropriately.

### Creating Model Classes
Create Java classes that represent the entities in your database: `Activity`, `EmissionFactor`, and `UserEmission`.

### Creating DAO Interfaces
Define DAO interfaces for each model class, specifying methods for CRUD operations and any additional queries you find necessary.

### Creating DAO Implementations Classes
Implement the DAO interfaces using JDBC to perform the actual database operations.

### Creating ConnectionFactory Class
Implement a `ConnectionFactory` class to manage database connections. This class should use the Singleton pattern to ensure only one instance of the connection is used throughout the application.

### Creating SqlQueries.java enum classes
- Create an enum `CRUDQueries` for storing your CRUD operation queries.
- Create an enum `OperationQueries` for storing additional SQL operation queries, including joins, subqueries, and aggregate functions.

### Creating Main Class as Demo using Scanner
Implement a main class that demonstrates the functionality of your application. Use a `Scanner` to interact with the user, allowing them to calculate and track their carbon emissions.

## SQL Operations
Ensure your application supports the following operations:
- Inserting, updating, and deleting records in the database.
- Calculating total emissions for a user.
- Comparing emissions between different activities.
- Listing activities with emissions above a certain threshold.

## Class Diagram includes all required classes with their relationships
### Java Classes Diagram

#### Model Classes

| Class Name     | Attributes                                                                 | Relationships |
|----------------|----------------------------------------------------------------------------|---------------|
| User           | userId: Long<br>username: String<br>email: String                          |               |
| Activity       | activityId: Long<br>name: String<br>description: String                    |               |
| EmissionFactor | factorId: Long<br>activityId: Long<br>factor: Double<br>unit: String       |               |
| UserEmission   | emissionId: Long<br>userId: Long<br>activityId: Long<br>quantity: Double<br>emission: Double<br>date: LocalDate |               |
| EmissionGoal   | goalId: Long<br>userId: Long<br>targetEmission: Double<br>startDate: LocalDate<br>endDate: LocalDate<br>status: String |               |

#### DAO Interfaces and Implementations

| Interface       | Implementation |
|-----------------|----------------|
| UserDAO         | UserDAOImpl    |
| ActivityDAO     | ActivityDAOImpl|
| EmissionFactorDAO | EmissionFactorDAOImpl |
| UserEmissionDAO | UserEmissionDAOImpl   |
| EmissionGoalDAO | EmissionGoalDAOImpl   |

#### Additional Classes

| Class Name        | Description |
|-------------------|-------------|
| ConnectionFactory | Manages the database connection, ensuring the use of a single connection instance throughout the application. |
| SqlQueries Enums  | Two enums (`CRUDQueries` and `OperationQueries`) to organize SQL queries for CRUD operations and additional SQL operations, respectively. |
| Main Class        | The entry point of the application, which uses a `Scanner` to interact with the user, demonstrating the application's functionality. |

#### Optional Service Classes

| Service Class            | Description |
|--------------------------|-------------|
| UserService              |             |
| ActivityService          |             |
| EmissionCalculationService |             |

## Tables Diagram includes all required Tables with their relationships
### Database Schema Tables

#### Users Table

| Column Name    | Data Type | Constraints     |
|----------------|-----------|-----------------|
| user_id        | INT       | PRIMARY KEY     |
| username       | VARCHAR   |                 |
| email          | VARCHAR   |                 |
| password_hash  | VARCHAR   |                 |

#### Activities Table

| Column Name    | Data Type | Constraints     |
|----------------|-----------|-----------------|
| activity_id    | INT       | PRIMARY KEY     |
| name           | VARCHAR   |                 |
| description    | TEXT      |                 |

#### Emission_Factors Table

| Column Name    | Data Type | Constraints                  |
|----------------|-----------|------------------------------|
| factor_id      | INT       | PRIMARY KEY                  |
| activity_id    | INT       | FOREIGN KEY (Activities)     |
| factor         | DECIMAL   |                              |
| unit           | VARCHAR   |                              |

#### User_Emissions Table

| Column Name    | Data Type | Constraints                  |
|----------------|-----------|------------------------------|
| emission_id    | INT       | PRIMARY KEY                  |
| user_id        | INT       | FOREIGN KEY (Users)          |
| activity_id    | INT       | FOREIGN KEY (Activities)     |
| quantity       | DECIMAL   |                              |
| emission       | DECIMAL   |                              |
| date           | DATE      |                              |

#### Emission_Goals Table

| Column Name    | Data Type | Constraints              |
|----------------|-----------|--------------------------|
| goal_id        | INT       | PRIMARY KEY              |
| user_id        | INT       | FOREIGN KEY (Users)      |
| target_emission| DECIMAL   |                          |
| start_date     | DATE      |                          |
| end_date       | DATE      |                          |
| status         | VARCHAR   |                          |

### Database Schema


### Relationships Overview Diagram

```plaintext
[Users] 1----* [User_Emissions] *----1 [Activities]
  |                                      |
  |                                      |
  *                                      *
[Emission_Goals]                        [Emission_Factors]
```

## Tasks
1. Set up the PostgreSQL environment and create the required tables.
2. Implement model classes in Java.
3. Define and implement DAO interfaces.
4. Implement the `ConnectionFactory` class.
5. Populate `CRUDQueries` and `OperationQueries` enums with necessary SQL queries.
6. Implement CRUD operations in DAO classes.
7. ### SQL Operation
Implement SQL operations for calculating and comparing emissions:
  a. **Calculate Total Emissions for a User**
   - Use a `JOIN` between `User_Emissions` and `Users`.
   - Aggregate emissions using `SUM`.
   - Group results by user.

  b. **Compare Emissions Between Different Activities**
   - Use a `JOIN` between `User_Emissions` and `Activities`.
   - Calculate the average emission per activity using `AVG`.
   - Group results by activity and order by average emission.

  c. **List Activities with Emissions Above a Certain Threshold**
   - Use a `JOIN` between `User_Emissions` and `Activities`.
   - Filter results where emission is greater than a specified threshold.

  d. **Calculate Monthly Emissions for a User**
   - Extract month from emission date.
   - Aggregate emissions using `SUM` for each month.
   - Group results by month.

  e. **Find Users Who Have Exceeded Their Emission Goals**
   - Use a `JOIN` between `Users` and `Emission_Goals`.
   - Include a subquery to calculate total emissions for each user.
   - Filter users whose total emissions exceed their target emission.

  f. **Using Custom Enum Types in Queries**
   - Assume a custom enum type for a column (e.g., `status` in `Emission_Goals`).
   - Filter results based on the enum value (e.g., `status = 'ACHIEVED'`).

  g. **Aggregate Emissions by Activity Type and Filter by Minimum Emission**
   - Use a `JOIN` between `User_Emissions` and `Activities`.
   - Group results by activity.
   - Use `HAVING` to filter groups by a minimum total emission.

  h. **Identify Top 3 Activities with the Highest Average Emissions**
   - Use a `JOIN` between `User_Emissions` and `Activities`.
   - Calculate the average emission per activity.
   - Group results by activity and order by average emission.
   - Limit results to the top 3 activities.
8. Develop a CLI-based Main Class for User Interaction

## Implementation
Follow the project setup instructions to implement the application. Ensure that your code is clean, well-documented, and follows best practices for database interaction and Java development.

## Bonus Task
- Implement a feature to visualize the user's carbon emissions over time using a simple chart or graph.

## Notes
- Pay attention to SQL injection vulnerabilities. Use prepared statements where applicable.
- Ensure your application handles database connections and exceptions gracefully.

## Submission
Submit your project via GitHub Classroom. Ensure your repository is well-organized, with clear instructions on how to set up and run your application. Include any necessary SQL scripts for setting up the database.