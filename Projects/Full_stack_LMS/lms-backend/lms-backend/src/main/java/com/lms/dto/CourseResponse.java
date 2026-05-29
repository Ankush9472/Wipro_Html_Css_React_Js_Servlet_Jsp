package com.lms.dto;

public class CourseResponse {

    private Long id;
    private String code;
    private String title;
    private String description;
    private String category;
    private String instructor;
    private String duration;
    private int fee;
    private String courseLink;
    private int studentCount;

    public CourseResponse() {}

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
    public int getStudentCount() { return studentCount; }
    public void setStudentCount(int studentCount) { this.studentCount = studentCount; }
}