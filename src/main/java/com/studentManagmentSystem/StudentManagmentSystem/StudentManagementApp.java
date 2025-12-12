package com.studentManagmentSystem.StudentManagmentSystem;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class StudentManagementApp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTabbedPane tabbedPane;
    private JTextArea displayArea;

    // Fields for Add Student tab
    private JTextField addNameField, addAgeField, addNumberField, addPlaceField, addStudyField;

    // Fields for Get/Delete/Update Student tab
    private JTextField idField, updateNameField, updateAgeField, updateNumberField, updatePlaceField, updateStudyField;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentManagementApp frame = new StudentManagementApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame (The Constructor).
     */
    public StudentManagementApp() {
        setTitle("Hibernate Student Management System");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 800, 550);

        // Crucial: Add listener to shut down Hibernate factory when the window closes
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                StudentManagement.shutdown();
                dispose();
                System.exit(0);
            }
        });

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        // 1. Setup the "Add Student" Tab
        setupAddStudentTab();

        // 2. Setup the "View All Students" Tab
        setupViewStudentsTab();
        
        // 3. Setup the "Update/Delete Student" Tab
        setupUpdateDeleteTab();
    }

    // --- Tab Setup Methods ---

    private void setupAddStudentTab() {
        JPanel addPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        addPanel.setBorder(new EmptyBorder(20, 50, 20, 50));
        
        addPanel.add(new JLabel("Name:"));
        addNameField = new JTextField();
        addPanel.add(addNameField);

        addPanel.add(new JLabel("Age:"));
        addAgeField = new JTextField();
        addPanel.add(addAgeField);

        addPanel.add(new JLabel("Phone Number:"));
        addNumberField = new JTextField();
        addPanel.add(addNumberField);

        addPanel.add(new JLabel("Place:"));
        addPlaceField = new JTextField();
        addPanel.add(addPlaceField);

        addPanel.add(new JLabel("Study Status (e.g., B.Tech):"));
        addStudyField = new JTextField();
        addPanel.add(addStudyField);
        
        JButton addButton = new JButton("Add Student Data");
        addButton.addActionListener(new AddStudentListener());
        addPanel.add(addButton);
        
        addPanel.add(new JLabel("")); // Spacer
        
        tabbedPane.addTab("Add Student", null, addPanel, "Add a new student record");
    }

    private void setupViewStudentsTab() {
        JPanel viewPanel = new JPanel(new BorderLayout());
        
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        
        JButton refreshButton = new JButton("Refresh Student List");
        refreshButton.addActionListener(e -> loadAllStudents());
        
        viewPanel.add(scrollPane, BorderLayout.CENTER);
        viewPanel.add(refreshButton, BorderLayout.SOUTH);
        
        tabbedPane.addTab("View All", null, viewPanel, "View all student records");
        
        // Load data when the tab is first opened
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedComponent() == viewPanel) {
                loadAllStudents();
            }
        });
    }

    private void setupUpdateDeleteTab() {
        JPanel udPanel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel(new FlowLayout());
        
        idField = new JTextField(5);
        JButton searchButton = new JButton("Search by ID");
        searchButton.addActionListener(new SearchStudentListener());
        
        searchPanel.add(new JLabel("Student ID:"));
        searchPanel.add(idField);
        searchPanel.add(searchButton);
        udPanel.add(searchPanel, BorderLayout.NORTH);
        
        // Form for Update/Delete
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(20, 50, 20, 50));
        
        formPanel.add(new JLabel("Name:"));
        updateNameField = new JTextField();
        formPanel.add(updateNameField);
        
        formPanel.add(new JLabel("Age:"));
        updateAgeField = new JTextField();
        formPanel.add(updateAgeField);

        formPanel.add(new JLabel("Phone Number:"));
        updateNumberField = new JTextField();
        formPanel.add(updateNumberField);

        formPanel.add(new JLabel("Place:"));
        updatePlaceField = new JTextField();
        formPanel.add(updatePlaceField);

        formPanel.add(new JLabel("Study Status:"));
        updateStudyField = new JTextField();
        formPanel.add(updateStudyField);
        
        JButton updateButton = new JButton("Update Record");
        updateButton.addActionListener(new UpdateStudentListener());
        formPanel.add(updateButton);

        JButton deleteButton = new JButton("Delete Record");
        deleteButton.addActionListener(new DeleteStudentListener());
        formPanel.add(deleteButton);
        
        udPanel.add(formPanel, BorderLayout.CENTER);
        tabbedPane.addTab("Update/Delete", null, udPanel, "Find and modify or delete a student");
    }
    
    // --- Data Load Method ---

    private void loadAllStudents() {
        displayArea.setText(""); // Clear previous content
        List<Student> students = StudentManagement.getAllStudents();
        
        if (students.isEmpty()) {
            displayArea.append("No student records found.");
        } else {
            // Header for the output
            displayArea.append(String.format("%-5s | %-20s | %-5s | %-15s | %-15s | %-15s\n", 
                                             "ID", "NAME", "AGE", "NUMBER", "PLACE", "STUDY"));
            displayArea.append("--------------------------------------------------------------------------------------------------\n");
            
            for (Student student : students) {
                displayArea.append(String.format("%-5d | %-20s | %-5d | %-15d | %-15s | %-15s\n", 
                                                 student.getId(), 
                                                 student.getName(), 
                                                 student.getAge(), 
                                                 student.getNum(), 
                                                 student.getPlace(), 
                                                 student.getStudy()));
            }
        }
    }

    // --- Action Listeners (Controllers) ---

    class AddStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = addNameField.getText().trim();
                int age = Integer.parseInt(addAgeField.getText().trim());
                int num = Integer.parseInt(addNumberField.getText().trim());
                String place = addPlaceField.getText().trim();
                String study = addStudyField.getText().trim();

                String result = StudentManagement.addStudent(name, age, num, place, study);

                JOptionPane.showMessageDialog(contentPane, result, "Add Status", JOptionPane.INFORMATION_MESSAGE);
                
                // Clear fields on success
                if (result.startsWith("SUCCESS")) {
                    addNameField.setText("");
                    addAgeField.setText("");
                    addNumberField.setText("");
                    addPlaceField.setText("");
                    addStudyField.setText("");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(contentPane, "Please enter valid numbers for Age and Phone Number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    class SearchStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                Student student = StudentManagement.getStudentById(id);
                
                if (student != null) {
                    updateNameField.setText(student.getName());
                    updateAgeField.setText(String.valueOf(student.getAge()));
                    updateNumberField.setText(String.valueOf(student.getNum()));
                    updatePlaceField.setText(student.getPlace());
                    updateStudyField.setText(student.getStudy());
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Student with ID " + id + " not found.", "Search Result", JOptionPane.WARNING_MESSAGE);
                    // Clear fields if not found
                    clearUpdateFields();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(contentPane, "Please enter a valid Student ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                clearUpdateFields();
            }
        }
    }
    
    class UpdateStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = updateNameField.getText().trim();
                int age = Integer.parseInt(updateAgeField.getText().trim());
                int num = Integer.parseInt(updateNumberField.getText().trim());
                String place = updatePlaceField.getText().trim();
                String study = updateStudyField.getText().trim();
                
                String result = StudentManagement.updateStudent(id, name, age, num, place, study);
                
                JOptionPane.showMessageDialog(contentPane, result, "Update Status", JOptionPane.INFORMATION_MESSAGE);
                
                if (result.startsWith("SUCCESS")) {
                    clearUpdateFields();
                }
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(contentPane, "Please ensure ID, Age, and Number are valid integers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    class DeleteStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                int confirm = JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to delete Student ID " + id + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    String result = StudentManagement.deleteStudent(id);
                    JOptionPane.showMessageDialog(contentPane, result, "Delete Status", JOptionPane.INFORMATION_MESSAGE);
                    if (result.startsWith("SUCCESS")) {
                        clearUpdateFields();
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(contentPane, "Please enter a valid Student ID to delete.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void clearUpdateFields() {
        idField.setText("");
        updateNameField.setText("");
        updateAgeField.setText("");
        updateNumberField.setText("");
        updatePlaceField.setText("");
        updateStudyField.setText("");
    }
}