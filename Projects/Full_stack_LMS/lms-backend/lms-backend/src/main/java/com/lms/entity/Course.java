package com.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course code is required")
    @Column(nullable = false, unique = true)
    private String code;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be 3-100 characters")
    @Column(nullable = false)
    private String title;

    @Size(max = 500)
    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Instructor is required")
    private String instructor;

    @NotBlank(message = "Duration is required")
    private String duration;

    private int fee;

    @Column(length = 500)
    private String courseLink;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

    public Course() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public int getFee() { return fee; }
    public void setFee(int fee) { this.fee = fee; }
    public String getCourseLink() { return courseLink; }
    public void setCourseLink(String courseLink) { this.courseLink = courseLink; }
    public Set<Student> getStudents() { return students; }
    public void setStudents(Set<Student> students) { this.students = students; }
}