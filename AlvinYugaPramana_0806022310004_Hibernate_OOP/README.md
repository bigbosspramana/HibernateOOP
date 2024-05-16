# Project Documentation

## Overview
This project is a simple Java application that utilizes Hibernate for ORM (Object-Relational Mapping) to manage student records in a database. It provides basic CRUD (Create, Read, Update, Delete) operations on student data.

## Technologies Used
- Java
- Hibernate ORM
- MySQL Database
- Maven for dependency management

## Configuration and Setup
### Database Configuration
The database connection settings are configured in the `hibernate.cfg.xml` file. It uses MySQL as the database with the following settings:
- Driver Class: `com.mysql.cj.jdbc.Driver`
- URL: `jdbc:mysql://localhost:3306/mydatabase?useSSL=false&serverTimezone=UTC`
- Username: `root`
- Password: (empty)

### Hibernate Configuration
Hibernate settings are managed in the `hibernate.cfg.xml` file, including:
- Dialect: `org.hibernate.dialect.MySQL8Dialect`
- Show SQL: `true`
- Format SQL: `true`
- HBM2DDL Auto: `update`

### Maven Dependencies
Key dependencies include:
- Hibernate Core
- MySQL JDBC Driver
- JPA API

## Key Classes
### `Students`
Represents the student entity with fields for id, name, age, and major. Mapped to the `students` table in the database.

### `Main`
Contains the main method and handles user interactions for performing CRUD operations on `Students` data.

### `HibernateUtil`
Provides a singleton instance of `SessionFactory` for use throughout the application, ensuring that the configuration is loaded correctly.

## Running the Application
To run the application, execute the `main` method in the `Main` class. This starts the application, displaying a navigation menu for various operations on the student data.

## Conclusion
This project demonstrates a simple implementation of Hibernate ORM in a Java application for managing student records, showcasing the integration of various technologies and configurations for real-world Java applications.
