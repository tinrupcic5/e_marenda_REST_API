# emarenda

*emarenda* is the backend application for managing school lunch participation. Built with **Java** and **Spring Boot**, this REST API provides the core functionalities to support the mobile and web applications used by parents, students, school kitchens, and administrators.

---

## Features

### Core Functionality:
- **User Management**:
    - Handles parent, student, kitchen staff, and admin roles.
    - Secure login and authentication.
- **Lunch Participation**:
    - API endpoints to record, update, and fetch lunch participation for students.
    - Allows parents to mark days when children will not participate in lunch.
- **Kitchen Dashboard**:
    - Real-time data for meal planning based on participation records.
- **Admin Tools**:
    - Generate monthly receipts for consumed meals.
    - Access detailed reports on participation and food consumption.

### Additional Features:
- **Efficient Data Handling**:
    - Tracks participation data to optimize food preparation and reduce waste.
- **Secure APIs**:
    - JWT-based authentication for secure access to endpoints.
- **Scalable Architecture**:
    - Built for scalability and easy integration with the frontend applications.

---
```commandline
                     +------------------+       +-----------------------+
                     |     User         |<----->|   Kitchen             |
                     | (Parent,Student) |       |   (Meal planning)     |
                     |                  |       |                       |
                     +--------+---------+       +-----------------------+
                              |                        |
                              | marks participation    | monitors participation
                              v                        v
               +------------------------------+      +-----------------------+
               |  Lunch Participation System  |----->|  Meal Planning (Based |
               |    (Mark attendance for      |      |   on participation)   |
               |     students for lunch)      |      +-----------------------+
               +------------------------------+                |
                       |                                       |
                       | fetches data                          |
                       v                                       v
               +------------------------------+       +----------------------+
               |    Admin Tools (Receipts &   |       |  Kitchen Dashboard   |
               |    Reports, Meal Consumption)|<----->|    (Real-time data)  |
               +------------------------------+       +----------------------+

```
---

## Technologies Used
- **Framework**: Spring Boot
- **Language**: Java
- **Database**: PostgreSQL
- **Authentication**: JWT (JSON Web Tokens)
- **Build Tool**: Maven 
- **Hosting**: ...

---

## API Documentation (Swagger)

The API documentation is available through Swagger UI, providing interactive documentation for all endpoints.

### Accessing Swagger UI

1. Start the application
2. Open your browser and navigate to:
   ```
   http://localhost:8081/swagger-ui.html
   ```

### Using Swagger UI

1. **Authentication**:
   - Click the "Authorize" button at the top
   - Use the `/users/authenticate` endpoint to get a JWT token
   - Enter the token as: `Bearer your-token-here`

2. **Exploring Endpoints**:
   - Browse endpoints by tags (User, School, Grade, Lunch)
   - Click on any endpoint to expand its details
   - Use the "Try it out" button to test endpoints directly

3. **Features**:
   - Interactive API testing
   - Request/response schemas
   - Authentication support
   - Search and filter capabilities

### OpenAPI Specification

The raw OpenAPI specification is available at:
```
http://localhost:8081/api-docs
```

This can be used with other API tools or for generating client code.

---