package com.lms.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    /**
     * MANY-TO-MANY with Course.
     * fetch = FetchType.EAGER → loads courses immediately with student
     * (fixes LazyInitializationException in JSP)
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "enrollments",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    public Student() {
        super();
    }

    public Student(String name) {
        super();
        this.name = name;
    }

    public Student(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }

    /**
     * Helper method to synchronize both sides of the relationship.
     */
    public void addCourse(Course c) {
        this.courses.add(c);
        c.getStudents().add(this);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}