# Alumni Connect (Alumni Management System)

A modern, full-stack **Alumni Management and Institutional Analytics Platform** built with **Spring Boot 3.5**, **Thymeleaf**, **Bootstrap 5**, and **MySQL**. Designed to bridge the gap between students, alumni, and institutions through career tracking, structured mentorship workflows, and interactive placement analytics.

---

## 🚀 Key Features

* **Role-Based Onboarding & Profiles:** Dedicated entities and registration workflows for **Admins**, **Students**, and **Alumni**, featuring dynamic profile fields.
* **Interactive Dashboard & Analytics:** Real-time metrics and dynamic **Chart.js** visualizations tracking user role distributions and placement package trends (LPA).
* **Alumni Directory:** Searchable professional directory showcasing graduate departments, degrees, current companies, designations, and bios.
* **Mentorship Program:** End-to-end connection workflow allowing students to send introductory mentorship requests and alumni/admins to track and update statuses (`PENDING`, `ACCEPTED`, `REJECTED`).
* **Placement Statistics:** Departmental and yearly historical tracking of total graduates, placement percentages, average packages, and highest packages.

---

## 🛠️ Tech Stack

* **Backend:** Java 21, Spring Boot 3.5, Spring Data JPA / Hibernate, Spring Web, Spring Validation
* **Frontend:** Thymeleaf Templates, Bootstrap 5, Chart.js, Custom CSS (Obsidian & Zinc SaaS Theme)
* **Database:** MySQL 8.x
* **Build Tool:** Maven

---

## 📊 Database Schema & Entity Relationships

The relational database is structured with strict normalization and foreign key constraints to maintain data integrity:

```text
┌────────────────────────────────────────────────────────┐
│                        users                           │
│  - id (PK)                                             │
│  - email (Unique)                                      │
│  - role (ENUM: ADMIN, STUDENT, ALUMNI)                 │
└───────────┬────────────────────────┬───────────────────┘
            │ 1:1                    │ 1:1
            ▼                        ▼
┌───────────────────────┐   ┌────────────────────────┐
│   student_profiles    │   │    alumni_profiles     │
│  - id (PK)            │   │  - id (PK)             │
│  - user_id (FK)       │   │  - user_id (FK)        │
│  - enrollment_year    │   │  - graduation_year     │
│  - current_semester   │   │  - current_company     │
└──────────▲────────────┘   └──────────┬─────────────┘
           │                         │ 1:N
           │                         ▼
           │                 ┌────────────────────────┐
           │                 │   employment_history   │
           │                 │  - id (PK)             │
           │                 │  - alumni_id (FK)      │
           │                 │  - company_name        │
           │                 └────────────────────────┘
           │                         │
           └────────────┬────────────┘
                        │ M:N (via Junction Table)
                        ▼
            ┌────────────────────────┐
            │   mentorship_requests  │
            │  - id (PK)             │
            │  - student_id (FK)     │
            │  - alumni_id (FK)      │
            │  - status (ENUM)       │
            └────────────────────────┘

            ┌────────────────────────┐
            │    placement_stats     │  <-- Standalone Analytics
            │  - id (PK)             │      (Feeds Dashboard Charts)
            │  - year, department    │
            │  - avg/highest package │
            └────────────────────────┘
```

### Relationship Summary
1. **`users` ➔ `alumni_profiles` / `student_profiles` (One-to-One):** Every user account maps directly to an academic or professional profile depending on their role. Cascading deletes ensure data cleanup.
2. **`alumni_profiles` ➔ `employment_history` (One-to-Many):** Allows recording multiple career progression milestones and past/current employment entries for an alumnus.
3. **`student_profiles` & `alumni_profiles` ➔ `mentorship_requests` (Many-to-Many via Junction):** Connects students seeking guidance with multiple alumni mentors while tracking approval states.
4. **`placement_stats` (Standalone):** Manages historical institutional summaries to render statistical charts independently of live user transactions.

---

## ⚙️ Getting Started & Installation

### Prerequisites
* Java Development Kit (JDK) 21 installed
* Maven installed
* MySQL Server running locally

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/alumni-management.git
cd alumni-management
```

### 2. Configure MySQL Database
Create a database named `alumni_management` in MySQL:
```sql
CREATE DATABASE alumni_management;
```

Update your `src/main/resources/application.properties` with your database credentials:
```properties
spring.application.name=alumni-management

spring.datasource.url=jdbc:mysql://localhost:3306/alumni_management
spring.datasource.username=root
spring.datasource.password=your_mysql_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
```

### 3. Build and Run the Application
Using Maven wrapper:
```bash
# Windows
mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

### 4. Access the Application
Open your browser and navigate to:
👉 **`http://localhost:8080/`**