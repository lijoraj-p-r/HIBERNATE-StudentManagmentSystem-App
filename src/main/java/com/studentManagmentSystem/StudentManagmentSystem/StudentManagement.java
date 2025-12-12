package com.studentManagmentSystem.StudentManagmentSystem;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentManagement {

    private static SessionFactory factory;

    // Static Initializer Block: Runs once to set up the SessionFactory
    static {
        try {
            factory = new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Student.class)
                    .buildSessionFactory();
            System.out.println("--- SessionFactory Initialized ---");
        } catch (Throwable ex) {
            System.err.println("SessionFactory initialization failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void shutdown() {
        if (factory != null && !factory.isClosed()) {
            factory.close();
            System.out.println("--- SessionFactory Closed ---");
        }
    }

    // --- CRUD Operations ---

    // CREATE (C)
    public static String addStudent(String name, int age, int num, String place, String study) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Student student = new Student(name, age, num, place, study);
            session.save(student);
            transaction.commit();
            return "SUCCESS: Student added with ID: " + student.getId();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            return "FAILURE: " + e.getMessage();
        }
    }

    // READ By ID (R)
    public static Student getStudentById(int id) {
        try (Session session = factory.openSession()) {
            // Note: transaction is not strictly required for a simple GET
            return session.get(Student.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    // READ All (R)
    public static List<Student> getAllStudents() {
        try (Session session = factory.openSession()) {
            Query<Student> query = session.createQuery("from Student", Student.class);
            return query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // UPDATE (U)
    public static String updateStudent(int id, String name, int age, int num, String place, String study) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            
            if (student != null) {
                student.setName(name);
                student.setAge(age);
                student.setNum(num);
                student.setPlace(place);
                student.setStudy(study);
                session.update(student); 
                transaction.commit();
                return "SUCCESS: Student ID " + id + " updated.";
            } else {
                return "FAILURE: Student ID " + id + " not found.";
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            return "FAILURE: " + e.getMessage();
        }
    }

    // DELETE (D)
    public static String deleteStudent(int id) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);

            if (student != null) {
                session.delete(student);
                transaction.commit();
                return "SUCCESS: Student ID " + id + " deleted.";
            } else {
                return "FAILURE: Student ID " + id + " not found.";
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            return "FAILURE: " + e.getMessage();
        }
    }
}