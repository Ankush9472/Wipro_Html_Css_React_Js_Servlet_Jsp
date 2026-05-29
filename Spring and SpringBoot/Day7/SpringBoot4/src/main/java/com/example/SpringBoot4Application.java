package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.entity.Student;
import com.example.repository.StudentRepo;

@SpringBootApplication
public class SpringBoot4Application {

    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(SpringBoot4Application.class, args);
        StudentRepo beann = run.getBean(StudentRepo.class);

        Student s1 = new Student();
        s1.setName("Ankush");
        s1.setCity("Arrah");

        Student save = beann.save(s1);

        System.out.println(save);
        
        Student s2=new Student();
        s2.setName("Kunal");
        s2.setCity("Buxar");
        
        beann.save(s2);
        
        System.out.println(s2);
    }
}