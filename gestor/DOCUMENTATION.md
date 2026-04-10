# Documentación del Proyecto Gestor

## 1. Descripción General

**Gestor** es una aplicación backend desarrollada con Spring Boot que permite a los usuarios gestionar sus finanzas personales mediante el seguimiento de gastos e items deseados.

## 2. Stack Tecnológico

- **Framework:** Spring Boot 3.5.11
- **Lenguaje:** Java 17
- **Build Tool:** Gradle
- **Base de Datos:** H2 (en memoria)
- **Seguridad:** Spring Security (BCrypt)
- **Validación:** Jakarta Validation
- **ORM:** Spring Data JPA

## 3. Arquitectura del Proyecto

```
com.myapp.gestor/
├── config/              # Configuraciones
├── controller/         # Controladores REST
│   ├── user/
│   ├── profile/
│   └── exception/
├── dto/                # Data Transfer Objects
│   ├── auth/
│   ├── profile/
│   └── User/
├── exception/          # Excepciones personalizadas
├── model/              # Entidades JPA
├── repository/         # Repositorios JPA
└── service/            # Lógica de negocio
    ├── auth/
    ├── profile/
    └── User/
```

## 4. Modelo de Datos

### 4.1 Entidades

#### User
| Campo | Tipo | Descripción |
|-------|------|-------------|
| id | Long | PK autoincremental |
| email | String | Email único |
| passwordHash | String | Contraseña encriptada |
| profile | UserProfile | Relación uno a uno |

#### UserProfile
| Campo | Tipo | Descripción |
|-------|------|-------------|
| id | Long | PK autoincremental |
| streaks | Integer | Racha de dias activos |
| currency | String | Moneda seleccionada |
| lastActivityDate | LocalDate | Ultima actividad |
| streakStartedDate | LocalDate | Inicio de racha |
| user | User | FK al usuario |
| items | List<Item> | Items del usuario |

#### Item
| Campo | Tipo | Descripción |
|-------|------|-------------|
| id | Long | PK autoincremental |
| description | String | Descripcion del item |
| price | BigDecimal | Precio |
| state | ItemState | ABANDONED o BOUGHT |
| productUrl | String | URL del producto |
| needLevel | Integer | Nivel de necesidad (1-5) |
| createdAt | LocalDateTime | Fecha de creacion |
| userProfile | UserProfile | FK al perfil |

### 4.2 Diagrama de Entidades

```
User (1) -----> (1) UserProfile (1) -----> (*) Item
```

## 5. API Endpoints

### 5.1 Autenticación (AuthController)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | /auth/register | Registrar nuevo usuario |
| POST | /auth/login | Iniciar sesión |

#### Registro
```json
Request:
{
  "email": "usuario@email.com",
  "passwordHash": "contraseña123"
}

Response (201):
{
  "email": "usuario@email.com"
}
```

#### Login
```json
Request:
{
  "email": "usuario@email.com",
  "password": "contraseña123"
}

Response (200):
{
  "message": "The login has been successfully done"
}
```

### 5.2 Usuario (UserController)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| DELETE | /api/user/account/delete/{id} | Eliminar cuenta |
| PATCH | /api/user/account/update-info/{id} | Actualizar cuenta |

### 5.3 Perfil (UserProfileController)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| PATCH | /api/profile/{id} | Actualizar racha |
| POST | /api/profile/{id}/currency | Establecer moneda |
| GET | /api/profile/{id}/list-items | Listar items paginados |

## 6. Excepciones

| Excepción | Código HTTP | Descripción |
|-----------|-------------|-------------|
| InvalidCredentialsException | 401 | Credenciales incorrectas |
| EntityNotFoundException | 404 | Entidad no encontrada |
| EmailNotFoundException | 404 | Email no registrado |

## 7. Seguridad

- Contraseñas encriptadas con BCrypt
- Validación de credenciales en cada operación敏感

## 8. Configuración

El archivo `application.properties` contiene:
```properties
spring.application.name=gestor
```

## 9. Dependencias Principales

- spring-boot-starter-security
- spring-boot-starter-validation
- spring-boot-starter-data-jpa
- spring-boot-starter-web
- lombok
- h2 (base de datos en memoria)