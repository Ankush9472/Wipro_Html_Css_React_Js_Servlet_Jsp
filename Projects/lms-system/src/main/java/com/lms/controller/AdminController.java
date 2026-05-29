package com.lms.controller;

import com.lms.entity.Course;
import com.lms.entity.Student;
import com.lms.service.CourseService;
import com.lms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("studentCount", studentService.getAllStudents().size());
        model.addAttribute("courseCount", courseService.getAllCourses().size());
        return "admin/dashboard";
    }

    // ---------- Students ----------
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "admin/students";
    }

    @GetMapping("/students/add")
    public String showAddStudent(Model model) {
        model.addAttribute("student", new Student());
        return "admin/add-student";
    }

    @PostMapping("/students/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/add-student";
        }
        studentService.saveStudent(student);
        return "redirect:/admin/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/admin/students";
    }

    // ---------- Courses ----------
    @GetMapping("/courses")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "admin/courses";
    }

    @GetMapping("/courses/add")
    public String showAddCourse(Model model) {
        model.addAttribute("course", new Course());
        return "admin/add-course";
    }

    @PostMapping("/courses/save")
    public String saveCourse(@Valid @ModelAttribute("course") Course course,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/add-course";
        }
        courseService.saveCourse(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/admin/courses";
    }

    // ---------- Assign / Remove ----------
    @GetMapping("/assign/{studentId}")
    public String showAssign(@PathVariable Long studentId, Model model) {
        Student s = studentService.getStudentById(studentId);
        model.addAttribute("student", s);
        model.addAttribute("courses", courseService.getAllCourses());
        return "admin/assign";
    }

    @PostMapping("/assign")
    public String assignCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        studentService.assignCourseToStudent(studentId, courseId);
        return "redirect:/admin/assign/" + studentId;
    }

    @GetMapping("/assign/{studentId}/remove/{courseId}")
    public String removeCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.removeCourseFromStudent(studentId, courseId);
        return "redirect:/admin/assign/" + studentId;
    }
}