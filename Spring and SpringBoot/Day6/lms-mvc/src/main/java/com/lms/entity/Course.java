package com.lms.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    public Course() {}   // constructor

    public Course(String title, String description) {
        this.title = title;            //parameterize constructor
        this.description = description;
    }

    public Long getId()
    
    {
    	return id;
    	}
    public void setId(Long id) 
    { 
    	this.id = id;
    	}
    

    public String getTitle()
    {
    	return title;
    	}
    
    
    public void setTitle(String title) 
    
    { 
    	this.title = title;
    	}
    
    

    public String getDescription() 
    
    {
    	return description;
    	
    }
    
    public void setDescription(String description) 
    
    { 
    	this.description = description; 
    	}

    public Set<Student> getStudents()
    
    
    { 
    	return students;
    	
    }
    public void setStudents(Set<Student> students) 
    {
    	this.students = students; 
    	}

    @Override
    public String toString() {
        return title;
    }
}