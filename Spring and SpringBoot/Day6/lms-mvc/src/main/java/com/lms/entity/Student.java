package com.lms.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Student Entity - mapped to the "students" table.
 *
 * @Entity → tells Hibernate this is a database entity
 * @Table  → custom table name
 */
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    /**
     * MANY-TO-MANY with Course. Creates "enrollments" join table.
     */
    @ManyToMany(fetch = FetchType.EAGER,
                cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "enrollments",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    public Student() {}  // non-param constructor
    

    public Student(String name, String email)
    
    {
        this.name = name;
        this.email = email;
    }

    public Long getId()
    
    {
    	return id;
    	
    }
    public void setId(Long id)
    { 
    	this.id = id;
    	
    }

    public String getName()
    {
    	return name; 
    	
    }
    public void setName(String name) 
    { 
    	this.name = name;
    	
    }

      public String getEmail() 
    { 
    	return email;
    	
     }
    public void setEmail(String email)
    { 
    	this.email = email;
    	
      }

    public Set<Course> getCourses() 
    
     { 
    	return courses;
    	
    }
    public void setCourses(Set<Course> courses)
    {
    	this.courses=courses;
    	
    }
}