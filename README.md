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
```bash
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
                         +-----------------------------+       +----------------------+
                         |    Admin Tools (Receipts &  |       |  Kitchen Dashboard   |
                         |    Reports, Meal Consumption)|<---->|    (Real-time data)  |
                         +-----------------------------+       +----------------------+

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

