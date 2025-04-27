# Project Setup Guide

## Prerequisites

- [Docker](https://www.docker.com/) installed on your machine

## Getting Started

1. **Clone the repository**  
   Download or clone this repository to your local machine.

2. **Run the application**  
   Navigate to the project directory and run the following command:

   ```bash
   docker compose up -d
   ```

3. **Access the application**  
   Open your browser and visit:

   ```
   http://localhost:8080/
   ```

---

## API Endpoints

### 1. Login

**Request**

- **Endpoint:** `POST http://localhost:8080/api/login`
- **Request Body:**

  ```json
  {
    "email": "teacher@example.com",
    "password": "passwordteacher"
  }
  ```

**Response**

```json
{
  "data": {
    "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhcnVtIiwiaWF0IjoxNzQ1NzIzMzc5LCJleHAiOjMyNTY1NDc0MzczOTg3ODd9.tpoQXTNajx3FZ-eh1Rchy3L-uocA8E_TuY0N1ITOvqrJOioG0sdgslk_bn1yi9zn"
  },
  "errors": null,
  "paging": null
}
```

---

### 2. List Users

**Request**

- **Endpoint:** `GET http://localhost:8080/api/users`
- **Authorization:** Bearer token (from login)

**Response**

```json
{
  "data": [
    {
      "id": 5,
      "email": "student3@example.com",
      "name": "maulana",
      "role": "STUDENT"
    },
    {
      "id": 4,
      "email": "student2@example.com",
      "name": "budi",
      "role": "STUDENT"
    },
    {
      "id": 3,
      "email": "student1@example.com",
      "name": "andi",
      "role": "STUDENT"
    },
    {
      "id": 2,
      "email": "teacher@example.com",
      "name": "arum",
      "role": "TEACHER"
    },
    {
      "id": 1,
      "email": "admin@example.com",
      "name": "admin",
      "role": "ADMIN"
    }
  ],
  "errors": null,
  "paging": {
    "currentPage": 0,
    "size": 25
  }
}
```

---

### 3. Create Class

**Request**

- **Endpoint:** `POST http://localhost:8080/api/classes`
- **Authorization:** Bearer token (from login)
- **Request Body:**

  ```json
  {
    "teacherId": 2,
    "studentsId": [3, 4],
    "grade": "10A",
    "name": "Mathematics"
  }
  ```

**Response**

```json
{
  "name": "Mathematics",
  "grade": "10A",
  "teacherName": "arum",
  "id": 1
}
```

---

### 4. Enroll Student into Class

**Request**

- **Endpoint:** `POST http://localhost:8080/api/enrollments/{classId}`
- **Authorization:** Bearer token (from login)
- **Request Body:**

  ```json
  {
    "studentId": 3
  }
  ```

**Response**

```json
{
  "data": {
    "studentName": "andi",
    "enrollmentDate": "2025-04-27T03:23:07.379626846Z",
    "className": "Mathematics"
  },
  "errors": null,
  "paging": null
}
```

---

## Notes

- Make sure to include the token in the `Authorization` header for all secured endpoints:

  ```
  Authorization: Bearer <your-token-here>
  ```

- Ensure Docker is running properly before executing `docker compose up -d`.
