package com.example.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @JoinColumn(name = "reportcard_id")  // FK in Student table
    private ReportCard reportcard;

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

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", reportcard=" + reportcard + "]";
    }
}