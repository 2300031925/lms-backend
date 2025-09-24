package com.lms.controller;
import com.lms.model.Course;
import com.lms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepo;

    @GetMapping
    public List<Course> getCourses() {
        return courseRepo.findAll();
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Integer id) {
        return courseRepo.findById(id).orElse(null);
    }
}

