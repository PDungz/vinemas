# Vinemas Server Project Documentation

## 1. Introduction
**Vinemas Server** is a backend application built with **Java Spring Boot** to manage a cinema system. The project uses **MySQL** as the database management system and follows the clean architecture pattern.

## 2. Technologies Used
- **Programming Language**: Java
- **Framework**: Spring Boot
- **Database Management System**: MySQL
- **Main Dependencies**:
  - `spring-boot-starter-web`
  - `spring-boot-devtools`
  - `spring-boot-starter-data-jpa`
  - `lombok`
  - `mysql-connector-java`
  - `spring-boot-starter-websocket`
  - `spring-boot-starter-validation`

## 3. Project Structure
```
src/main/java/com/example
│── application  # Business Logic (Use Cases)
│   ├── usecase  
│   │   ├── user
│   │   │   ├── CreateUserUseCase.java
│   │   │   ├── GetUserUseCase.java
│   │   │   ├── UpdateUserUseCase.java
│   │   │   ├── DeleteUserUseCase.java
│   │   │   ├── SearchUserUseCase.java
│   │   ├── chair_config
│   │   │   ├── CreateChairConfigUseCase.java
│   │   │   ├── GetChairConfigUseCase.java
│   │   │   ├── UpdateChairConfigUseCase.java
│   │   │   ├── DeleteChairConfigUseCase.java
│   │   │   ├── SearchChairConfigUseCase.java
│   ├── service  
│   │   ├── UserService.java
│   │   ├── ChairConfigService.java
│── domain  # Core Business Rules (Entities, Interfaces)
│   ├── model  
│   │   ├── user
│   │   │   ├── User.java
│   │   │   ├── Address.java
│   │   │   ├── Role.java
│   │   │   ├── OrderHistory.java
│   │   ├── chair_config
│   │   │   ├── ChairConfig.java
│   ├── repository  
│   │   ├── UserRepository.java
│   │   ├── ChairConfigRepository.java
│── infrastructure  
│   ├── repository  
│   │   ├── JpaUserRepository.java
│   │   ├── JpaChairConfigRepository.java
│   ├── config  
│   │   ├── AppConfig.java
│── adapter  
│   ├── controller  
│   │   ├── UserController.java
│   │   ├── ChairConfigController.java
│── main  
│   ├── VinemasApplication.java
```

# 4. Project Structure and Processing Flow Details

## 4.1. Architecture Overview
The **Vinemas Server** project follows **Clean Architecture**, dividing the application into distinct layers:
- **Application Layer**: Contains use cases that handle business logic.
- **Domain Layer**: Contains entities and interfaces defining core business rules.
- **Infrastructure Layer**: Includes supporting components such as repositories and application configurations.
- **Adapter Layer**: Contains controllers that receive client requests and pass data to the Application layer.

---

## 4.2. Component Details

### 4.2.1. **Application Layer**
The `application` directory contains **Use Cases** and **Services**, responsible for handling business logic:

#### 1. **Use Cases**
Use Case classes execute specific system actions. For example:
- **User**
  - `CreateUserUseCase.java`: Handles the logic for creating a new user.
  - `GetUserUseCase.java`: Handles querying user information.
  - `UpdateUserUseCase.java`: Updates user information.
  - `DeleteUserUseCase.java`: Deletes a user from the system.
  - `SearchUserUseCase.java`: Searches for users based on specific criteria.
- **Chair Config**
  - `CreateChairConfigUseCase.java`: Creates seat configuration in the theater.
  - `GetChairConfigUseCase.java`: Retrieves seat configuration details.
  - `UpdateChairConfigUseCase.java`: Updates seat configuration.
  - `DeleteChairConfigUseCase.java`: Deletes seat configuration.
  - `SearchChairConfigUseCase.java`: Searches for seat configurations based on criteria.

#### 2. **Service**
Service classes act as intermediaries between Use Cases and Repositories:
- `UserService.java`: Handles business logic related to users.
- `ChairConfigService.java`: Handles business logic related to seat configuration.

---

### 4.2.2. **Domain Layer**
The `domain` directory contains core system components:
- **Model**: Defines the main system objects.
  - `User.java`: Represents a user, containing personal information.
  - `Address.java`: Stores user address information.
  - `Role.java`: Defines user roles (Admin, User, etc.).
  - `OrderHistory.java`: Stores ticket purchase history.
  - `ChairConfig.java`: Defines seat configurations in the theater.
- **Repository Interface**: Defines data retrieval methods that repositories implement.
  - `UserRepository.java`: Interface for user queries.
  - `ChairConfigRepository.java`: Interface for seat configuration queries.

---

### 4.2.3. **Infrastructure Layer**
The `infrastructure` directory contains system support components:
- **Repository Implementation**: Implements Repositories using JPA.
  - `JpaUserRepository.java`: Implements `UserRepository` using Spring Data JPA.
  - `JpaChairConfigRepository.java`: Implements `ChairConfigRepository` using Spring Data JPA.
- **Config**: Stores application configurations.
  - `AppConfig.java`: Application configuration file.

---

### 4.2.4. **Adapter Layer**
The `adapter` directory contains controllers that receive client requests and pass data to the Application layer.
- **Controller**:
  - `UserController.java`: Handles APIs related to users.
  - `ChairConfigController.java`: Handles APIs related to seat configurations.

---

## 4.3. System Processing Flow

### **Processing Flow for Creating a New User**
1. `UserController` receives a request from the client.
2. The controller calls `CreateUserUseCase` in the Application layer.
3. `CreateUserUseCase` calls `UserRepository` to store data in the database.
4. `JpaUserRepository` (in the Infrastructure layer) executes storage in MySQL.
5. The repository returns the result to `UserController`, which then responds to the client.

### **Processing Flow for Retrieving Cinema List**
1. `CinemaController` receives a request from the client.
2. The controller calls `GetCinemaUseCase` in the Application layer.
3. `GetCinemaUseCase` calls `CinemaRepository` to retrieve the list of cinemas.
4. `JpaCinemaRepository` executes the data query from MySQL.
5. The repository returns the result to `CinemaController`, which then responds to the client.

---

## 4.4. Overall Model

```
[Client] → [Controller] → [UseCase] → [Repository] → [Database]
```

Example:
```
[Client] → [UserController] → [CreateUserUseCase] → [UserRepository] → [JpaUserRepository] → [MySQL]
```

## 5. Application Configuration
```properties
spring.application.name=vinemas_server

# MySQL Configuration
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate properties
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 6. Database Schema

### 6.1 Users Table
```sql
CREATE TABLE Users (
    user_auth_id VARCHAR(50) PRIMARY KEY,
    avatar_url TEXT,
    date_of_birth DATETIME,
    email VARCHAR(255),
    full_name VARCHAR(255),
    gender INT(1),
    phone_number VARCHAR(15),
    address TEXT
);
```

### 6.2 CinemaBand Table
```sql
CREATE TABLE CinemaBand (
    cinema_band_id VARCHAR(50) PRIMARY KEY,
    name_cinema VARCHAR(255),
    image_url TEXT,
    open_date DATETIME,
    close_date DATETIME,
    description TEXT
);
```

### 6.3 Cinema Table
```sql
CREATE TABLE Cinema (
    cinema_id VARCHAR(50) PRIMARY KEY,
    cinema_band_id VARCHAR(50),
    chair_config_id VARCHAR(50),
    open_date DATETIME,
    close_date DATETIME,
    description TEXT,
    name_cinema VARCHAR(255),
    address TEXT,
    FOREIGN KEY (cinema_band_id) REFERENCES CinemaBand(cinema_band_id),
    FOREIGN KEY (chair_config_id) REFERENCES ChairConfig(chair_config_id)
);
```

### 6.4 ChairConfig Table
```sql
CREATE TABLE ChairConfig (
    chair_config_id VARCHAR(50) PRIMARY KEY,
    layout VARCHAR(50),
    row_count INT(5),
    seats_per_row INT(5)
);
```

### 6.5 ChairType Table
```sql
CREATE TABLE ChairType (
    chair_config_id VARCHAR(50),
    chair_type_id VARCHAR(50),
    seat_row VARCHAR(50),
    PRIMARY KEY (chair_config_id, chair_type_id, seat_row),
    FOREIGN KEY (chair_config_id) REFERENCES ChairConfig(chair_config_id)
);
```

### 6.6 SessionMovie Table
```sql
CREATE TABLE SessionMovie (
    session_movie_id VARCHAR(50) PRIMARY KEY,
    cinema_id VARCHAR(50),
    movie_id VARCHAR(50),
    start_date DATETIME,
    end_date DATETIME,
    description TEXT,
    FOREIGN KEY (cinema_id) REFERENCES Cinema(cinema_id)
);
```

### 6.7 ChairStatus Table
```sql
CREATE TABLE ChairStatus (
    session_movie_id VARCHAR(50),
    seat_id VARCHAR(50),
    status INT(1),
    PRIMARY KEY (session_movie_id, seat_id),
    FOREIGN KEY (session_movie_id) REFERENCES SessionMovie(session_movie_id)
);
```

### 6.8 SeatPrice Table
```sql
CREATE TABLE SeatPrice (
    session_movie_id VARCHAR(50),
    seat_type VARCHAR(50),
    price DECIMAL(15),
    PRIMARY KEY (session_movie_id, seat_type),
    FOREIGN KEY (session_movie_id) REFERENCES SessionMovie(session_movie_id)
);
```

### 6.9 Ticket Table
```sql
CREATE TABLE Ticket (
    ticket_id VARCHAR(50) PRIMARY KEY,
    session_movie_id VARCHAR(50),
    total DECIMAL(15,2),
    booking_time DATETIME,
    content TEXT,
    update_time DATETIME,
    status INT(1),
    FOREIGN KEY (session_movie_id) REFERENCES SessionMovie(session_movie_id)
);
```

### 6.10 TicketSeat
```sql
CREATE TABLE TicketSeat (
ticket_id VARCHAR(50),
seat_id VARCHAR(50),
PRIMARY KEY (ticket_id, seat_id),
FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id)
);
```

## 6.11 Payment
```sql
CREATE TABLE Payment (
payment_id VARCHAR(50) PRIMARY KEY,
user_auth_id VARCHAR(50),
ticket_id VARCHAR(50),
payment_method INT(1),
amount DECIMAL(15,2),
created_at DATETIME,
content TEXT,
update_at DATETIME,
payment_status INT(1),
FOREIGN KEY (user_auth_id) REFERENCES Users(user_auth_id),
FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id)
);
```

This document provides a structured overview of the **Vinemas Server** system.