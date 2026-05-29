package com.lms.controller;

import com.lms.service.CourseService;
import com.lms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("students", studentService.getAllStudents());
        return "user/dashboard";
    }
}