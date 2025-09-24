package com.lms.controller;

import com.lms.model.Assignment;
import com.lms.repository.AssignmentRepository;
import com.lms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentRepository assignmentRepo;

    @Autowired
    private CourseRepository courseRepo;

    private static final String UPLOAD_DIR = "uploads/";

    @GetMapping
    public List<Assignment> getAllAssignments() {
        return assignmentRepo.findAll();
    }

    @GetMapping("/course/{courseId}")
    public List<Assignment> getAssignmentsByCourse(@PathVariable Integer courseId) {
        return assignmentRepo.findByCourseId(courseId);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAssignment(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("assignmentId") Integer assignmentId) {
        Optional<Assignment> optionalAssignment = assignmentRepo.findById(assignmentId);

        if (!optionalAssignment.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignment not found");
        }

        try {
            String fileName = file.getOriginalFilename();
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Assignment assignment = optionalAssignment.get();
            assignment.setUploadPath(filePath.toString());
            assignmentRepo.save(assignment);

            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Could not upload the file: " + e.getMessage());
        }
    }

    @DeleteMapping("/upload/{assignmentId}")
    public ResponseEntity<String> removeUpload(@PathVariable Integer assignmentId) {
        Optional<Assignment> optionalAssignment = assignmentRepo.findById(assignmentId);
        if (!optionalAssignment.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignment not found");
        }

        Assignment assignment = optionalAssignment.get();
        String uploadPath = assignment.getUploadPath();

        if (uploadPath != null) {
            try {
                Files.deleteIfExists(Paths.get(uploadPath));
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error deleting file: " + e.getMessage());
            }
            assignment.setUploadPath(null);
            assignmentRepo.save(assignment);

            return ResponseEntity.ok("Upload removed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No upload found to remove");
        }
    }
}
