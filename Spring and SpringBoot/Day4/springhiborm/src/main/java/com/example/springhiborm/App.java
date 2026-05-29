package com.example.springhiborm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.springhiborm.entity.Student;
import com.example.springhiborm.service.StudentService;

public class App {

    // Controller — entry point
    public static void main(String[] args) {

        // Load Spring container from XML config
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Get StudentService bean from container (Spring injects everything)
        StudentService service = ctx.getBean(StudentService.class);

        // Creating students using constructor
        service.create(new Student("Ankush", "ankush@gmail.com", "Java full-Stack"));
        service.create(new Student("Nishant", "Nishant@gmail.com", "python"));

        // List all students
        System.out.println("___________All Student Data ________");
        System.out.println("All Students list: " + service.list());

        // Get student by ID
        System.out.println("___________Student based on ID ________");
        Student s = service.get(3L);
        System.out.println("Student with id 1: " + s);
    }
}