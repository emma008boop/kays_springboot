# Kays - Personal Finance Manager API

A REST API built with Spring Boot designed to help users track and manage their micro-expenses ("petty expenses") through the analysis of personal cash flows. The application enables users to maintain wishlists of desired items, track spending patterns, and build financial discipline through gamification features like streak tracking.

---

## Project Context

This project was created as part of a full-stack developer portfolio, demonstrating:

- Clean code architecture with clear separation of concerns
- Spring Boot best practices
- Role-based security implementation
- RESTful API design
- Database modeling with JPA/Hibernate

The application manages two core concepts:
1. **User Profiles** - Track personal financial activity, streaks, and preferred currency
2. **Items** - Maintain wishlists of desired purchases with price tracking and "need level" categorization

---

## Tech Stack

| Component | Technology |
|-----------|-------------|
| Language | Java 17 |
| Framework | Spring Boot 3.5.11 |
| Security | Spring Security (BCrypt password encoding) |
| Database | MySQL |
| ORM | Spring Data JPA / Hibernate |
| Build Tool | Gradle |
| Validation | Jakarta Validation |

---

## Features

### Authentication & Authorization
- User registration with email/password
- Secure login with BCrypt-encrypted passwords
- Role-based access control (ADMIN, USER)
- Permission system (CREATE, READ, DELETE, CREATE_USER, READ_USERS, DELETE_USER)

### User Management
- Create new user accounts
- Update account information
- Delete accounts
- View user profiles

### Financial Tracking
- **Streak System** - Track daily finance logging. Streaks reset after 48 hours of inactivity.
- **Currency Selection** - Support for multiple currencies (USD, EUR, MXN, etc.)
- **Need Level** - Categorize items by necessity (1-10 scale: impulse buy vs. genuine need)

### Item Management
- Create wishlist items with description, price, and product URL
- Track item state (ABANDONED or BOUGHT)
- List items with pagination support

---

## Architecture

```
com.myapp.gestor/
├── config/              # Security configuration
├── controller/          # REST endpoints
│   ├── auth/           # Authentication (register, login)
│   ├── user/           # User account management
│   └── profile/       # Profile & item management
├── dto/                # Data Transfer Objects
├── exception/          # Custom exceptions
├── model/              # JPA entities
├── repository/         # Data access layer
└── service/            # Business logic
```

---

## Database Schema

```
USER (1) -----> (1) USER_PROFILE (1) -----> (*) ITEM
```

### User
| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key |
| username | String | Unique username |
| email | String | Unique email |
| passwordHash | String | Encrypted password |
| roles | Set<RoleEntity> | User roles |
| isEnable | boolean | Account status |
| profile | UserProfile | One-to-one relation |

### UserProfile
| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key |
| streaks | Integer | Active streak count |
| currency | String | Selected currency |
| lastActivityDate | LocalDate | Last activity |
| user | User | Foreign key |
| items | List<Item> | User's items |

### Item
| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key |
| description | String | Item description |
| price | BigDecimal | Item price |
| state | ItemState | ABANDONED or BOUGHT |
| productUrl | String | Product URL |
| needLevel | Integer | Need level (1-10) |
| userProfile | UserProfile | Foreign key |

---

## API Endpoints

### Authentication

| Method | Endpoint | Description | Body |
|--------|----------|-------------|-----|
| POST | /auth/register | Register new user | `{"email", "password", "username"}` |
| POST | /auth/login | Login user | `{"email", "password"}` |

### User Management

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| PATCH | /api/user/account/update-info/{id} | Update user info | USER |
| DELETE | /api/user/account/delete/{id} | Delete account | USER |

### Profile

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| PATCH | /api/profile/{id} | Update streak | USER |
| POST | /api/profile/{id}/currency | Set currency | USER |
| GET | /api/profile/{id}/list-items | List items (paginated) | USER |

---

## Installation

### Prerequisites
- JDK 17 or higher
- MySQL 8.0+
- Gradle (included wrapper)

### Database Setup
```sql
CREATE DATABASE kays_db;
```

### Configuration
Edit `gestor/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/kaysdb
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### Run the Application
```bash
cd gestor
./gradlew bootRun
```

### Default Users (created on first run)
| Username | Password | Role |
|----------|----------|------|
| emma | emma123 | ADMIN + USER |
| marshall | marshall123 | USER |

---

## Example Requests

### Register User
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email": "newuser@example.com", "password": "password123", "username": "newuser"}'
```

### Login
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "emma@gmail.com", "password": "emma123"}'
```

### Set Currency
```bash
curl -X POST http://localhost:8080/api/profile/1/currency \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  -d '{"currency": "USD"}'
```

---

## License

This project is for portfolio purposes.