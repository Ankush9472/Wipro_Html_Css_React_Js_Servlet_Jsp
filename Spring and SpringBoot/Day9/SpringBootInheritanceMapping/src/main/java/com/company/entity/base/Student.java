package com.company.entity.base;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DiscriminatorValue("STUDENT")
public class Student extends Person {

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Email> emails = new ArrayList<>();

    public Student() {
    }

    // Method overriding (by calling base class constructor)
    public Student(String name, Address addr) {
        super(name, addr);
    }

    // Helper to add an email and maintain the bidirectional relationship
    public void addEmail(String emailAddress) {
        Email e = new Email(emailAddress, this);
        this.emails.add(e);
    }
}