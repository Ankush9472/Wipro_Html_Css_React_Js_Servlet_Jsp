# LMS Backend - Spring Boot REST API

A complete Spring Boot REST backend for a Learning Management System.
This is API-only — ready for a React frontend to consume.

## Tech Stack
- Spring Boot 3.2.0
- Spring Security (HTTP Basic + role-based)
- Spring Data JPA
- Jakarta Validation
- H2 (in-memory, default) / MySQL (optional)
- CORS enabled for React (5173, 5174, 3000)

## Run

```bash
cd lms-backend
mvn spring-boot:run
```

Server starts on `http://localhost:8080`

## Default Login Credentials

| Role  | Username | Password   |
|-------|----------|------------|
| ADMIN | admin    | admin123   |
| USER  | user     | user123    |

## Authentication

Send `Authorization: Basic <base64(username:password)>` header on every protected request.

The `/api/auth/login` endpoint returns a `token` field which is exactly that Base64 string — the frontend stores it and sends it back as `Authorization: Basic <token>`.

---

## API ENDPOINTS

### Auth (public)
| Method | URL                  | Description           | Body |
|--------|----------------------|-----------------------|------|
| POST   | `/api/auth/login`    | Login, returns token  | `{ "username": "admin", "password": "admin123" }` |
| GET    | `/api/auth/me`       | Current logged-in user | - |

### Admin - Dashboard (ROLE_ADMIN)
| Method | URL                          | Description                        |
|--------|------------------------------|------------------------------------|
| GET    | `/api/admin/dashboard/stats` | Total students, courses, enrollments |

### Admin - Students (ROLE_ADMIN)
| Method | URL                                   | Description           |
|--------|---------------------------------------|-----------------------|
| GET    | `/api/admin/students`                 | List all students     |
| GET    | `/api/admin/students/{id}`            | Get student by id     |
| POST   | `/api/admin/students`                 | Create student        |
| PUT    | `/api/admin/students/{id}`            | Update student        |
| DELETE | `/api/admin/students/{id}`            | Delete student        |
| GET    | `/api/admin/students/search?keyword=` | Search by name        |
| GET    | `/api/admin/students/{id}/courses`    | Get student's courses |

**Create Student body:**
```json
{
  "name": "John Doe",
  "email": "john@test.com",
  "rollNumber": "R001",
  "phone": "9999999999"
}
```

### Admin - Courses (ROLE_ADMIN)
| Method | URL                                  | Description       |
|--------|--------------------------------------|-------------------|
| GET    | `/api/admin/courses`                 | List all courses  |
| GET    | `/api/admin/courses/{id}`            | Get course by id  |
| POST   | `/api/admin/courses`                 | Create course     |
| PUT    | `/api/admin/courses/{id}`            | Update course     |
| DELETE | `/api/admin/courses/{id}`            | Delete course     |
| GET    | `/api/admin/courses/search?keyword=` | Search by title   |

**Create Course body:**
```json
{
  "code": "JAVA101",
  "title": "Java Full Stack",
  "description": "Complete Java course",
  "category": "Development",
  "instructor": "Rohit Kumar",
  "duration": "12 Weeks",
  "credits": 4,
  "rating": 4.8
}
```

### Admin - Enrollments (ROLE_ADMIN)
| Method | URL                                       | Description                |
|--------|-------------------------------------------|----------------------------|
| POST   | `/api/admin/enroll/{studentId}/{courseId}` | Enroll student in course   |
| DELETE | `/api/admin/enroll/{studentId}/{courseId}` | Unenroll student from course |

### User - Read-Only (ROLE_USER or ROLE_ADMIN)
| Method | URL                                 | Description         |
|--------|-------------------------------------|---------------------|
| GET    | `/api/user/courses`                 | View all courses    |
| GET    | `/api/user/courses/{id}`            | View course details |
| GET    | `/api/user/courses/search?keyword=` | Search courses      |
| GET    | `/api/user/students`                | View all students   |
| GET    | `/api/user/students/{id}`           | View student        |

---

## Features Implemented

| Requirement | Where |
|-------------|-------|
| Spring Boot REST | All controllers |
| Validation | `@NotBlank`, `@Email`, `@Size`, `@Positive`, `@Valid` |
| Inheritance | `Student extends Person` (`@MappedSuperclass`) |
| Many-to-Many | `Student` ↔ `Course` via `student_course` join table |
| CRUD (Add/View/Delete/Update) | Admin endpoints for students + courses |
| Global Exception Handler | `GlobalExceptionHandler` with `@RestControllerAdvice` |
| Two Dashboards | Admin endpoints (full CRUD) + User endpoints (read-only) |
| Spring Security | Role-based: ROLE_ADMIN vs ROLE_USER |
| CORS for React | Configured for ports 5173, 5174, 3000 |

## Test with curl

```bash
# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'

# Get all courses (using basic auth)
curl -X GET http://localhost:8080/api/admin/courses \
  -H "Authorization: Basic YWRtaW46YWRtaW4xMjM="

# Create a course
curl -X POST http://localhost:8080/api/admin/courses \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic YWRtaW46YWRtaW4xMjM=" \
  -d '{"code":"ML101","title":"Machine Learning","category":"AI","instructor":"Dr. Kumar","duration":"10 Weeks","credits":4,"rating":4.5,"description":"Intro to ML"}'
```

## H2 Console
Browser: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:lmsdb`
- Username: `sa`
- Password: (empty)
