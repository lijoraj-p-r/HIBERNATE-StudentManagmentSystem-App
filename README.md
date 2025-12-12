
````markdown
# HIBERNATE-StudentManagementSystem-App

**A comprehensive Student Management System (SMS) built with Java Swing for the GUI and Hibernate ORM for efficient data persistence using a MySQL database.**

This project demonstrates a clean, functional desktop application that utilizes industry-standard best practices, including a dedicated Data Access Object (DAO) layer and Maven for dependency management.

---

## ✨ Features

* **Complete CRUD Functionality:** Full support for Create (Add), Read (View All/By ID), Update, and Delete student records through the Swing GUI.
* **Hibernate ORM (5.4.30.Final):** Used as the persistence framework to handle all object-to-relational mapping.
* **Decoupled Architecture:** The database logic (`StudentManagement.java`) is separated from the UI (`StudentManagementApp.java`), ensuring a maintainable codebase.
* **Robust Session Handling:** The `SessionFactory` is initialized via a static block to prevent `NullPointerException` errors upon application launch and is gracefully shut down when the window closes.
* **Maven Integration:** Simplifies dependency management, automatically resolving complex Hibernate dependencies (Dom4j, JBoss Logging, etc.).

---

## 🛠️ Technology Stack

| Component | Technology | Version | Description |
| :--- | :--- | :--- | :--- |
| **Backend ORM** | Hibernate Core | 5.4.30.Final | Handles persistence and data mapping. |
| **Database** | MySQL | 8.0.x | Relational database storage. |
| **Frontend** | Java Swing | JDK 8+ | Desktop application GUI framework. |
| **Build Tool** | Apache Maven | 3.x | Manages dependencies and build lifecycle. |

---

## 🚀 Getting Started

Follow these steps to set up and run the project locally.

### Prerequisites

1.  **Java Development Kit (JDK):** Version 8 or higher.
2.  **Apache Maven:** Installed and configured in your system's PATH.
3.  **MySQL Server:** Running (default port 3306 is assumed).

### 1. Database Setup

Ensure you have created the database specified in the configuration. Hibernate will automatically create the `student` table if `hbm2ddl.auto` is set to `update`.

```sql
-- SQL Command to create the database
CREATE DATABASE my_student_management_db;

-- Update the credentials in src/main/resources/hibernate.cfg.xml if necessary:
-- <property name="hibernate.connection.username">root</property>
-- <property name="hibernate.connection.password">lijo</property>
````

### 2\. Build and Run

1.  **Clone the Repository:**

    ```bash
    git clone [https://github.com/lijoraj-p-r/HIBERNATE-StudentManagmentSystem-App.git](https://github.com/lijoraj-p-r/HIBERNATE-StudentManagmentSystem-App.git)
    cd HIBERNATE-StudentManagmentSystem-App
    ```

2.  **Build and Download Dependencies (using Maven):**

    ```bash
    mvn clean install
    ```

3.  **Run the Application:**
    Execute the `main` method in the `StudentManagementApp.java` class from your IDE.

-----

## 📂 Project Structure

The project follows the standard Maven structure:

```
HIBERNATE-StudentManagmentSystem-App/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.studentManagmentSystem.StudentManagmentSystem/
│   │   │       ├── Student.java           <-- Entity (Database Model)
│   │   │       ├── StudentManagement.java <-- DAO Layer (CRUD Logic)
│   │   │       └── StudentManagementApp.java<-- Main GUI Application
│   │   └── resources/
│   │       └── hibernate.cfg.xml          <-- Hibernate Configuration
│   └── ...
└── pom.xml                               <-- Maven Dependencies and Configuration
```

-----

## 📸 Screenshots


-----

## 🤝 Contribution

Contributions are welcome\! Please feel free to fork the repository, submit bug reports, or propose feature enhancements.

-----

## 📄 License

This project is licensed under the MIT License.

```
```
