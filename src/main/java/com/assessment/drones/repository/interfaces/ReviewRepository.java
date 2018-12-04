package com.assessment.drones.repository.interfaces;

import com.assessment.drones.domain.Review;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReviewRepository
{
    Integer addReview(Review review);

    List<Review> reviewsByInstructor(String surname);
}
