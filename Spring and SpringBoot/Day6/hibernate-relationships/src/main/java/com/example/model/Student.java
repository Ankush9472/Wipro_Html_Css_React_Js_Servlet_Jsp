package com.example.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Unidirectional one-to-one mapping with ReportCard
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reportcard_id")   // FK in Student table
    private ReportCard reportcard;

    // Unidirectional one-to-many mapping with Courses
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")      // FK in Course table
    private List<Course> courses = new ArrayList<>();

    /* Bidirectional alternative (uncomment if needed):
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();
    */

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

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

    public ReportCard getReportcard() {
        return reportcard;
    }

    public void setReportcard(ReportCard reportcard) {
        this.reportcard = reportcard;
    }

    // Helper method to add a course to the student
    public void addCourse(Course c) {
        this.courses.add(c);

        /* For bidirectional:
        c.setStudent(this);
        */
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", reportcard=" + reportcard + "]";
    }
}