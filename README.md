# Nexora

## Overview

Nexora is a backend application developed with Spring Boot designed to manage appointments. It provides a RESTful API to schedule, update, retrieve, and delete appointments efficiently. 

## Technology Stack

The project utilizes the following core technologies:

*   **Java 21**: Leveraging the latest LTS features.
*   **Spring Boot**: Serving as the foundational framework.
*   **Spring Web MVC**: Implementing the REST APIs.
*   **Spring Data JPA**: Handling database interactions and Object-Relational Mapping (ORM).
*   **H2 Database**: Providing an embedded, in-memory relational database.
*   **Lombok**: Minimizing boilerplate code (getters, setters, constructors).
*   **Maven**: Managing project dependencies and build lifecycle.

## Project Structure

The project implements a standardized layered architecture focused exclusively on the **Appointment** domain:

*   **Controller (`AppointmentController`)**: Exposes the REST endpoints under the `/appointments` resource.
*   **Service (`AppointmentService`)**: Contains the core business logic for appointment operations.
*   **Repository (`AppointmentRepository`)**: Manages database persistence using Spring Data JPA.
*   **Entity (`Appointment`)**: Maps the appointment data to the database table, including unique constraints to ensure a client has a single unique appointment at a specific date and time.
*   **Exception Handling**: Utilizes a `GlobalExceptionHandler` returning `ErrorResponse` objects to standardize API error structures.

## API Endpoints

The application exposes the following endpoints for managing appointments:

### 1. Create Appointment
*   **URL**: `/appointments`
*   **Method**: `POST`
*   **Description**: Registers a new appointment.
*   **Request Body**: Expects an `Appointment` JSON object (fields: `service`, `professional`, `scheduledDateTime`, `client`, `clientPhone`).

### 2. Retrieve Appointments by Day
*   **URL**: `/appointments`
*   **Method**: `GET`
*   **Description**: Fetches a list of all appointments scheduled for a specific date.
*   **Query Parameters**:
    *   `date`: The target date (e.g., `YYYY-MM-DD`).

### 3. Update Appointment
*   **URL**: `/appointments`
*   **Method**: `PUT`
*   **Description**: Updates an existing appointment.
*   **Query Parameters**:
    *   `client`: The name of the client.
    *   `scheduledDateTime`: The originally scheduled date and time.
*   **Request Body**: Expects an `Appointment` JSON object containing the updated payload.

### 4. Delete Appointment
*   **URL**: `/appointments`
*   **Method**: `DELETE`
*   **Description**: Deletes an appointment.
*   **Query Parameters**:
    *   `scheduledDateTime`: The scheduled date and time.
    *   `client`: The name of the client.

## Building and Running the Application

### Prerequisites
*   Java Development Kit (JDK) 21.

### Running the Project

You can resolve dependencies, build, and initiate the application using the included Maven Wrapper:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

By default, the application server initializes on port `8080`.

### Database Access

The application runs exclusively using an H2 in-memory database. While the application is running, the database can typically be accessed via a web browser using the standard Spring Boot H2 console URL (e.g., `http://localhost:8080/h2-console`) dynamically configured within standard Spring `application.properties`.
