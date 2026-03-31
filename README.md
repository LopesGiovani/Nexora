# Nexora

## Overview

Nexora is a backend application developed with Spring Boot designed to manage appointments. It provides a secure RESTful API with JWT authentication to schedule, update, retrieve, and delete appointments efficiently.

## Technology Stack

*   **Java 21**: Leveraging the latest LTS features.
*   **Spring Boot 4**: Serving as the foundational framework.
*   **Spring Web MVC**: Implementing the REST APIs.
*   **Spring Data JPA**: Handling database interactions and Object-Relational Mapping (ORM).
*   **Spring Security**: Stateless authentication with JWT tokens.
*   **PostgreSQL**: Production-grade relational database.
*   **Flyway**: Database schema versioning and migrations.
*   **Auth0 java-jwt**: JWT token generation and validation.
*   **SpringDoc OpenAPI (Swagger)**: Interactive API documentation.
*   **Lombok**: Minimizing boilerplate code (getters, setters, constructors).
*   **Maven**: Managing project dependencies and build lifecycle.

## Project Structure

The project implements a layered architecture:

*   **Controllers**: `AppointmentController` (CRUD) and `AuthenticationController` (login/register).
*   **Services**: `AppointmentService` (business logic) and `AuthenticationService` (Spring Security UserDetailsService).
*   **Repositories**: `AppointmentRepository` and `UserRepository` (Spring Data JPA).
*   **Entities**: `Appointment` and `User` (implements `UserDetails`).
*   **DTOs**: Java Records for request/response data integrity.
*   **Security**: JWT filter, token service, and security configuration.
*   **Exception Handling**: `GlobalExceptionHandler` with structured `ErrorResponse` objects.

## API Endpoints

### Authentication (Public)

| Method | URL              | Description                      |
|--------|------------------|----------------------------------|
| POST   | `/auth/login`    | Authenticate and receive JWT     |
| POST   | `/auth/register` | Register a new user              |

### Appointments (Authenticated — requires Bearer token)

| Method | URL              | Description                      |
|--------|------------------|----------------------------------|
| POST   | `/appointments`  | Create a new appointment         |
| GET    | `/appointments`  | List all or filter by date       |
| PUT    | `/appointments`  | Update an existing appointment   |
| DELETE | `/appointments`  | Delete an appointment            |

### API Documentation

*   **Swagger UI**: `http://localhost:8080/swagger-ui.html`
*   **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

## Environment Variables

The following environment variables **must** be set before running the application:

| Variable      | Description                          | Example                        |
|---------------|--------------------------------------|--------------------------------|
| `DB_HOST`     | PostgreSQL host and port             | `localhost:5432`               |
| `DB_USERNAME` | Database username                    | `postgres`                     |
| `DB_PASSWORD` | Database password                    | `your_password`                |
| `API_SECRET`  | JWT signing secret (min 32+ chars)   | `a-very-long-and-secure-key`   |

## Building and Running

### Prerequisites
*   Java Development Kit (JDK) 21
*   PostgreSQL database running and accessible

### Running the Project

```bash
# Set environment variables
export DB_HOST=localhost:5432
export DB_USERNAME=postgres
export DB_PASSWORD=your_password
export API_SECRET=your-secure-jwt-secret-key-here

# Build and run
./mvnw clean install
./mvnw spring-boot:run
```

By default, the application server initializes on port `8080`.

## Testing with Postman

A ready-to-use Postman collection is included in the project root: `Nexora.postman_collection.json`.

### How to use

1. Import the file into Postman (**Import** → **Upload Files**)
2. Run **Register** to create a user
3. Run **Login** — the JWT token is saved automatically
4. All other requests will use the token automatically

The collection includes success and error test cases for every endpoint.