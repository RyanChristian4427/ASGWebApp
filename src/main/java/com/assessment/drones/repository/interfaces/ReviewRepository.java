package com.assessment.drones.repository.interfaces;

import com.assessment.drones.domain.ReviewDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReviewRepository
{
    void addReview(ReviewDto reviewDto);

    List<ReviewDto> reviewsByInstructor(String surname);
}
