package com.lms.service;

import com.lms.dao.CourseDao;
import com.lms.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    public void addCourse(Course course) {
        courseDao.save(course);
    }

    public List<Course> getAllCourses() {
        return courseDao.findAll();
    }

    public Course getCourseById(Long id) {
        return courseDao.findById(id);
    }
}