package com.lms.controller;

import com.lms.dto.CourseResponse;
import com.lms.dto.StudentResponse;
import com.lms.entity.Student;
import com.lms.repository.StudentRepository;
import com.lms.service.CourseService;
import com.lms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:3000"})
public class UserController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/courses")
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // NEW: get courses for the logged-in user, identified by email passed as query param
    @GetMapping("/my-courses")
    public ResponseEntity<List<CourseResponse>> getMyCourses(@RequestParam(required = false) String email,
                                                             Authentication auth) {
        // If email provided, find student by email
        if (email != null && !email.isEmpty()) {
            Optional<Student> opt = studentRepository.findByEmail(email);
            if (opt.isPresent()) {
                return ResponseEntity.ok(studentService.getStudentCourses(opt.get().getId()));
            }
        }
        return ResponseEntity.ok(new ArrayList<>());
    }

    // NEW: check if logged-in user (by email) is enrolled in a course
    @GetMapping("/my-enrolled-ids")
    public ResponseEntity<List<Long>> myEnrolledIds(@RequestParam(required = false) String email) {
        List<Long> ids = new ArrayList<>();
        if (email != null && !email.isEmpty()) {
            Optional<Student> opt = studentRepository.findByEmail(email);
            if (opt.isPresent()) {
                List<CourseResponse> arr = studentService.getStudentCourses(opt.get().getId());
                for (CourseResponse c : arr) {
                    ids.add(c.getId());
                }
            }
        }
        return ResponseEntity.ok(ids);
    }
}