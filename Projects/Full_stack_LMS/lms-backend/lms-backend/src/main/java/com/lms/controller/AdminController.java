package com.lms.controller;

import com.lms.dto.ApiResponse;
import com.lms.dto.CourseResponse;
import com.lms.dto.DashboardStats;
import com.lms.dto.StudentResponse;
import com.lms.entity.Course;
import com.lms.entity.Student;
import com.lms.repository.CourseRepository;
import com.lms.repository.StudentRepository;
import com.lms.service.CourseService;
import com.lms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:3000"})
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // ---------- Dashboard ----------
    @GetMapping("/dashboard/stats")
    public ResponseEntity<DashboardStats> stats() {
        long n = studentRepository.count();
        long m = courseRepository.count();
        long cnt = 0;
        for (Student s : studentRepository.findAll()) {
            cnt += s.getCourses().size();
        }
        return ResponseEntity.ok(new DashboardStats(n, m, cnt));
    }

    // ---------- Students ----------
    @GetMapping("/students")
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping("/students")
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id,
                                                        @Valid @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(new ApiResponse(true, "Student deleted successfully"));
    }

    @GetMapping("/students/search")
    public ResponseEntity<List<StudentResponse>> searchStudents(@RequestParam String keyword) {
        return ResponseEntity.ok(studentService.searchStudents(keyword));
    }

    // ---------- Courses ----------
    @GetMapping("/courses")
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody Course course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id,
                                                      @Valid @RequestBody Course course) {
        return ResponseEntity.ok(courseService.updateCourse(id, course));
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(new ApiResponse(true, "Course deleted successfully"));
    }

    @GetMapping("/courses/search")
    public ResponseEntity<List<CourseResponse>> searchCourses(@RequestParam String keyword) {
        return ResponseEntity.ok(courseService.searchCourses(keyword));
    }

    // ---------- Enrollments ----------
    @PostMapping("/enroll/{studentId}/{courseId}")
    public ResponseEntity<StudentResponse> enroll(@PathVariable Long studentId,
                                                  @PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.enrollCourse(studentId, courseId));
    }

    @DeleteMapping("/enroll/{studentId}/{courseId}")
    public ResponseEntity<StudentResponse> unenroll(@PathVariable Long studentId,
                                                    @PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.unenrollCourse(studentId, courseId));
    }

    @GetMapping("/students/{studentId}/courses")
    public ResponseEntity<List<CourseResponse>> getStudentCourses(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentCourses(studentId));
    }
}
