package com.lms.dto;

import java.util.List;

public class StudentResponse {

    private Long id;
    private String name;
    private String email;
    private String rollNumber;
    private String phone;
    private List<CourseResponse> courses;

    public StudentResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public List<CourseResponse> getCourses() { return courses; }
    public void setCourses(List<CourseResponse> courses) { this.courses = courses; }
}
