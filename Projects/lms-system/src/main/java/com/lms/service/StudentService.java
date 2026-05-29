package com.lms.service;

import com.lms.entity.Course;
import com.lms.entity.Student;
import com.lms.exception.CourseNotFoundException;
import com.lms.exception.StudentNotFoundException;
import com.lms.repository.CourseRepository;
import com.lms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " does not exist"));
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student s = getStudentById(id);
        studentRepository.delete(s);
    }

    @Transactional
    public void assignCourseToStudent(Long studentId, Long courseId) {
        Student s = getStudentById(studentId);
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + courseId + " does not exist"));
        s.getCourses().add(c);
        studentRepository.save(s);
    }

    @Transactional
    public void removeCourseFromStudent(Long studentId, Long courseId) {
        Student s = getStudentById(studentId);
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + courseId + " does not exist"));
        s.getCourses().remove(c);
        studentRepository.save(s);
    }
}