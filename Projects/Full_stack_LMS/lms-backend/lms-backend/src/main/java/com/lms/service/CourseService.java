package com.lms.service;

import com.lms.dto.CourseResponse;
import com.lms.entity.Course;
import com.lms.exception.CourseNotFoundException;
import com.lms.exception.DuplicateResourceException;
import com.lms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseResponse> getAllCourses() {
        List<Course> arr = courseRepository.findAll();
        List<CourseResponse> res = new ArrayList<>();
        for (Course c : arr) {
            res.add(toDto(c));
        }
        return res;
    }

    public CourseResponse getCourseById(Long id) {
        Course c = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id " + id + " does not exist"));
        return toDto(c);
    }

    public Course getCourseEntity(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id " + id + " does not exist"));
    }

    public CourseResponse createCourse(Course course) {
        if (courseRepository.findByCode(course.getCode()).isPresent()) {
            throw new DuplicateResourceException("Course code already exists: " + course.getCode());
        }
        Course saved = courseRepository.save(course);
        return toDto(saved);
    }

    public CourseResponse updateCourse(Long id, Course course) {
        Course existing = getCourseEntity(id);
        existing.setCode(course.getCode());
        existing.setTitle(course.getTitle());
        existing.setDescription(course.getDescription());
        existing.setCategory(course.getCategory());
        existing.setInstructor(course.getInstructor());
        existing.setDuration(course.getDuration());
        existing.setFee(course.getFee());
        existing.setCourseLink(course.getCourseLink());
        Course saved = courseRepository.save(existing);
        return toDto(saved);
    }

    public void deleteCourse(Long id) {
        Course c = getCourseEntity(id);
        courseRepository.delete(c);
    }

    public List<CourseResponse> searchCourses(String keyword) {
        List<Course> arr = courseRepository.findByTitleContainingIgnoreCase(keyword);
        List<CourseResponse> res = new ArrayList<>();
        for (Course c : arr) {
            res.add(toDto(c));
        }
        return res;
    }

    public CourseResponse toDto(Course c) {
        CourseResponse r = new CourseResponse();
        r.setId(c.getId());
        r.setCode(c.getCode());
        r.setTitle(c.getTitle());
        r.setDescription(c.getDescription());
        r.setCategory(c.getCategory());
        r.setInstructor(c.getInstructor());
        r.setDuration(c.getDuration());
        r.setFee(c.getFee());
        r.setCourseLink(c.getCourseLink());
        r.setStudentCount(c.getStudents() != null ? c.getStudents().size() : 0);
        return r;
    }
}