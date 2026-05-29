package com.company.entity.base;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("TEACHER")
public class Teacher extends Person {

    public String subject;

    public Teacher(String name, Address addr, String subject) {
        super(name, addr);
        this.subject = subject;
    }
}