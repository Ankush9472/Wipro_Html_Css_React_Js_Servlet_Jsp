package com.lms.controller;

import com.lms.entity.Course;
import com.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "course-form";
    }

    @PostMapping("/add")
    public String addCourse(@ModelAttribute Course course) {
        courseService.addCourse(course);
        return "redirect:/student/list";
    }
}