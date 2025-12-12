package com.studentManagmentSystem.StudentManagmentSystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "phone_number")
    private int num; // Renamed to 'num' for consistency with previous code

    @Column(name = "place")
    private String place;

    @Column(name = "study_status")
    private String study; // e.g., "Undergraduate", "Postgraduate"

    // Default no-argument constructor (required by Hibernate)
    public Student() {
    }

    // Constructor for saving new students (excluding auto-generated ID)
    public Student(String name, int age, int num, String place, String study) {
        this.name = name;
        this.age = age;
        this.num = num;
        this.place = place;
        this.study = study;
    }

    // --- Getters and Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", age=" + age + ", num=" + num + ", place=" + place + ", study=" + study + "]";
    }
}