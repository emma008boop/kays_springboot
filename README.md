Kays API 

    A robust REST API built with Spring Boot to track "Micro-Expenses" and analyze personal cash flow to reduce impulsive spending.

This project is part of my portfolio as a Full-Stack Developer, focusing on Clean Code, Layered Architecture, and Domain-Driven design principles.
🛠 Tech Stack

    Backend: Java 17+ / Spring Boot 3.4.x

    Security: Spring Security (BCrypt Password Hashing)

    Database: MySQL

    Persistence: Spring Data JPA / Hibernate

    Dependency Manager: Gradle

 Principal Features

    Layered Architecture: Clear separation between Controller, Service, and Repository layers.

    Global Exception Handling: Standardized HTTP error responses for a better API consumer experience.

    Data Validation: Using jakarta.validation to ensure DTO integrity.

    Gamification Logic: * Streak System: A custom engine that rewards daily financial logging. Streaks reset automatically after 48 hours of inactivity.

        Need Level (1-10): Categorizes expenses to identify impulsive vs. essential spending.

Installation & Local Setup
Prerequisites

    JDK 17 or higher.

    MySQL installed and running.

    Gradle (optional, you can use the wrapper ./gradlew).

Steps

    Clone the repository:
    Bash

    git clone https://github.com/emma008boop/kays_springboot.git
    cd kays_springboot

    Database Setup:
    Connect to your MySQL instance and run:
    SQL

    CREATE DATABASE kays_db;

    Configure Environment:
    Update your src/main/resources/application.properties with your credentials:
    Properties

    spring.datasource.url=jdbc:mysql://localhost:3306/kays_db?serverTimezone=UTC
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update

Data Model
Fragmento de código

erDiagram
    USER ||--|| USER_PROFILE : "has"
    USER_PROFILE ||--o{ ITEM : "manages"

    USER {
        Long id
        String email
        String passwordHash
    }
    USER_PROFILE {
        Long id
        Integer streaks
        String currency
        LocalDate lastActivityDate
    }
    ITEM {
        Long id
        String description
        BigDecimal price
        Integer needLevel
        String productUrl
    }