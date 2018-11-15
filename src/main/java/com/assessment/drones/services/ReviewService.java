package com.assessment.drones.services;

import com.assessment.drones.domain.Review;

import java.util.List;

public interface ReviewService {

    String addReview(Review review);

    List<Review> reviewsByInstructor(String lastName);
}
