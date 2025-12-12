package com.studentManagmentSystem.StudentManagmentSystem;

import java.util.List;

public class StudentManagerApp {

    public static void main(String[] args) {
        try {
            // --- C: Create ---
            System.out.println(StudentManagement.addStudent("Alice Smith", 21, 987654321, "London", "B.Tech"));
            System.out.println(StudentManagement.addStudent("Bob Johnson", 20, 112233445, "Paris", "M.Sc"));

            // --- R: Read All ---
            System.out.println("\n--- All Students ---");
            List<Student> students = StudentManagement.getAllStudents();
            students.forEach(System.out::println);
            
            // Assuming Alice is ID 1 (based on auto-increment)
            if (!students.isEmpty()) {
                int aliceId = students.get(0).getId();

                // --- U: Update ---
                System.out.println("\n--- Updating Alice ---");
                String updateResult = StudentManagement.updateStudent(
                    aliceId, "Alice Jones", 22, 987654321, "New York", "B.Tech"
                );
                System.out.println(updateResult);
                
                // --- R: Read By ID ---
                System.out.println("\n--- Reading Alice (ID " + aliceId + ") ---");
                Student updatedAlice = StudentManagement.getStudentById(aliceId);
                System.out.println(updatedAlice);

                // --- D: Delete ---
                System.out.println("\n--- Deleting Bob (Assuming ID 2) ---");
                // Note: In a real system, you should determine Bob's ID dynamically
                System.out.println(StudentManagement.deleteStudent(aliceId + 1)); 

            }
            
            System.out.println("\n--- Final Student List ---");
            StudentManagement.getAllStudents().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the SessionFactory is closed when the application finishes
            StudentManagement.shutdown();
        }
    }
}