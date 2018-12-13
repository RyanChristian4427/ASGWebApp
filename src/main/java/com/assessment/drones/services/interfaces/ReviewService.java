package com.assessment.drones.services.interfaces;

import com.assessment.drones.domain.ReviewDto;

import java.util.List;

public interface ReviewService {

    void addReview(ReviewDto reviewDto);

    List<ReviewDto> reviewsByInstructor(String surname);
}
