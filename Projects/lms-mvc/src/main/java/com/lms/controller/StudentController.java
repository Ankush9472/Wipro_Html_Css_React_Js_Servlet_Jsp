package com.lms.controller;

import com.lms.entity.Student;
import com.lms.service.CourseService;
import com.lms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

     @Autowired
     private CourseService courseService;

    @GetMapping("/")
    public String home() 
    
    {
        return "redirect:/student/list";
    }
    

    @GetMapping("/register")
     public String showRegisterForm(Model model)
     
    {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student)
    
    {
        studentService.registerStudent(student);
        return "redirect:/student/list";
    }
    
    

    @GetMapping("/enroll")
    public String showEnrollForm(Model model) 
    
    
    {
        model.addAttribute("students", studentService.getAllStudents());
          model.addAttribute("courses", courseService.getAllCourses());
         return "enroll-form";
    }
    
    

    @PostMapping("/enroll")
    public String enrollStudent(@RequestParam("studentId") Long studentId, @RequestParam("courseIds") List<Long> courseIds) 
     {
        studentService.enrollStudent(studentId, courseIds);
          return "redirect:/student/list";
    }
    
    

    @GetMapping("/list")
    public String viewEnrollments(Model model) 
    
    {
        model.addAttribute("students", studentService.getAllStudents());
         return "view";
    }
}