package com.lms.repository;

import com.lms.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    List<Assignment> findByCourseId(Integer courseId);
}
