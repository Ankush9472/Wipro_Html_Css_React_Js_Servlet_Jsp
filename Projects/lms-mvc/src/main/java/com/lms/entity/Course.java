package com.lms.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Course Entity - mapped to "courses" table.
 * Inverse side of the ManyToMany relationship.
 */
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    /**
     * Inverse side of the ManyToMany relationship.
     * "mappedBy" tells Hibernate that Student is the owner,
     * so it doesn't create another join table.
     */
    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    public Course() {
        super();
    }

    public Course(String title) {
        super();
        this.title = title;
    }

    public Course(String title, String description) {
        super();
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return title;
    }
}