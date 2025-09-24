package com.lms.controller;
import com.lms.model.Enrollment;
import com.lms.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    @Autowired EnrollmentRepository enrollRepo;
    @GetMapping("/user/{userId}")
    public List<Enrollment> getUserEnrollments(@PathVariable Integer userId) {
        return enrollRepo.findByUserId(userId);
    }
    @PostMapping
    public Enrollment enroll(@RequestBody Enrollment enrollment) {
        return enrollRepo.save(enrollment);
    }
    @PutMapping("/{id}")
    public Enrollment updateProgress(@PathVariable Integer id, @RequestBody Map<String, Integer> body) {
        Enrollment e = enrollRepo.findById(id).orElse(null);
        e.setProgress(body.get("progress"));
        return enrollRepo.save(e);
    }
}
