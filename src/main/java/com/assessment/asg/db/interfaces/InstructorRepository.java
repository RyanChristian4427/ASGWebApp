package com.assessment.asg.db.interfaces;

import com.assessment.asg.models.Instructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface InstructorRepository {
    Optional<Instructor> findInstructorByID(Long id);
}
