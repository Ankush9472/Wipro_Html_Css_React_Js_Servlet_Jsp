package com.lms.service;

import com.lms.dto.CourseResponse;
import com.lms.dto.StudentResponse;
import com.lms.entity.Course;
import com.lms.entity.Student;
import com.lms.exception.DuplicateResourceException;
import com.lms.exception.StudentNotFoundException;
import com.lms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseService courseService;

    public List<StudentResponse> getAllStudents() {
        List<Student> arr = studentRepository.findAll();
        List<StudentResponse> res = new ArrayList<>();
        for (Student s : arr) {
            res.add(toDto(s));
        }
        return res;
    }

    public StudentResponse getStudentById(Long id) {
        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " does not exist"));
        return toDto(s);
    }

    public Student getStudentEntity(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " does not exist"));
    }

    public StudentResponse createStudent(Student student) {
        if (studentRepository.findByRollNumber(student.getRollNumber()).isPresent()) {
            throw new DuplicateResourceException("Roll number already exists: " + student.getRollNumber());
        }
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already exists: " + student.getEmail());
        }
        Student saved = studentRepository.save(student);
        return toDto(saved);
    }

    public StudentResponse updateStudent(Long id, Student student) {
        Student existing = getStudentEntity(id);
        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setRollNumber(student.getRollNumber());
        existing.setPhone(student.getPhone());
        Student saved = studentRepository.save(existing);
        return toDto(saved);
    }

    public void deleteStudent(Long id) {
        Student s = getStudentEntity(id);
        studentRepository.delete(s);
    }

    @Transactional
    public StudentResponse enrollCourse(Long studentId, Long courseId) {
        Student s = getStudentEntity(studentId);
        Course c = courseService.getCourseEntity(courseId);
        s.getCourses().add(c);
        Student saved = studentRepository.save(s);
        return toDto(saved);
    }

    @Transactional
    public StudentResponse unenrollCourse(Long studentId, Long courseId) {
        Student s = getStudentEntity(studentId);
        Course c = courseService.getCourseEntity(courseId);
        s.getCourses().remove(c);
        Student saved = studentRepository.save(s);
        return toDto(saved);
    }

    public List<CourseResponse> getStudentCourses(Long studentId) {
        Student s = getStudentEntity(studentId);
        List<CourseResponse> res = new ArrayList<>();
        for (Course c : s.getCourses()) {
            res.add(courseService.toDto(c));
        }
        return res;
    }

    public List<StudentResponse> searchStudents(String keyword) {
        List<Student> arr = studentRepository.findByNameContainingIgnoreCase(keyword);
        List<StudentResponse> res = new ArrayList<>();
        for (Student s : arr) {
            res.add(toDto(s));
        }
        return res;
    }

    public StudentResponse toDto(Student s) {
        StudentResponse r = new StudentResponse();
        r.setId(s.getId());
        r.setName(s.getName());
        r.setEmail(s.getEmail());
        r.setRollNumber(s.getRollNumber());
        r.setPhone(s.getPhone());
        List<CourseResponse> cArr = new ArrayList<>();
        if (s.getCourses() != null) {
            for (Course c : s.getCourses()) {
                cArr.add(courseService.toDto(c));
            }
        }
        r.setCourses(cArr);
        return r;
    }
}
