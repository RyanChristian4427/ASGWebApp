package com.assessment.drones.repository;

import com.assessment.drones.domain.Instructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface InstructorRepository {
    Optional<Instructor> findInstructorByID(Long id);
}
