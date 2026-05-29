package com.company.service;

import com.company.entity.base.Student;
import com.company.repo.PersonRepo;
import com.company.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo students;
    private final PersonRepo personRepo;

    public StudentService(StudentRepo students, PersonRepo personRepo) {
        super();
        this.students = students;
        this.personRepo = personRepo;
    }

    public Student save(Student s) {
        return students.save(s);
    }

    // Find all students with emails eagerly loaded
    public List<Student> findAllWithEmails() {
        return personRepo.findAllStudentsWithEmails();
    }
}