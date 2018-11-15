package com.assessment.drones.repository;

import com.assessment.drones.domain.Review;
import org.springframework.stereotype.Component;

@Component
public interface ReviewRepository
{
    Integer addReview(Review review);

}
