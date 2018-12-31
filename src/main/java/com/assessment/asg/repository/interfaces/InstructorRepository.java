package com.assessment.asg.repository.interfaces;

import com.assessment.asg.domain.Instructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface InstructorRepository {
    Optional<Instructor> findInstructorByID(Long id);
}
