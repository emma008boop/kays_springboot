Kays.
> A robust REST API built with Spring Boot to handle Micro-Expenses, tracking, and reducing “petty expenses” through the analysis of personal cash flows..

This project is part of my portfolio as a full-stack developer, with a focus on clean code, and monolithic architecture.

---

TECH STACK
*   **Backend:** Java 17+ / Spring Boot 3.5.11
*   **Security:** Spring Security (JWT / OAuth2)
*   **Data Base:** MySQL
*   **Persistency:** Spring Data JPA / Hibernate
*   **Documentation:** Swagger / OpenAPI UI
*   **Dependencies gestor:** Gradle

---

Principal features.
*   **Layered Architecture:** Clear separation between Controller, Service, and Repository.
*   **Global Exception Handling:** Standardized HTTP error responses.
*   **Data Validation:** Using `jakarta.validation` for DTO integrity.
---

Installation and local use.

Pre-required
*   JDK 17 or higher.
*   MySQL intalled and running.
*   Maven.

STEPS
1. **Clone the repository:**
   git clone https://github.com/emma008boop/kays_springboot.git
   cd kays_springboot
2.  On MySQL
   CREATE DATABASE kays_db;
3. # URL connection (Port 3306)
spring.datasource.url=jdbc:mysql://localhost:3306/kays_db?createDatabaseIfNotExist=true&serverTimezone=UTC

spring.datasource.username=root
spring.datasource.password=tu_password_aqui

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

Gamification & Retention Logic:

    Streak System: I implemented a “streak” engine in UserProfile. If the user logs their finances daily, the system rewards consistency. If       they stop using it for more than 48 hours, the streak automatically resets.

    Need Level (1-10): Each item has a needLevel stored as a TINYINT. This allows us to categorize how “impulsive” the expense is: Is it a         real need or an impulse?

    OneToOne & OneToMany: Decoupled structure between User (credentials) and UserProfile (app data) to facilitate future OAuth2 integrations.

```mermaid
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
