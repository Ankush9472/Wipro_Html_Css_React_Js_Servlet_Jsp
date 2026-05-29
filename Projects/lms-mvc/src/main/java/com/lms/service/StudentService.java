package com.lms.service;

import com.lms.dao.StudentDao;
import com.lms.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public void registerStudent(Student student) {
        studentDao.save(student);
    }

    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

    public Student getStudentById(Long id) {
        return studentDao.findById(id);
    }

    public void enrollStudent(Long studentId, List<Long> courseIds) {
        studentDao.enrollStudent(studentId, courseIds);
    }
}