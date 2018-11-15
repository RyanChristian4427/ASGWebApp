package com.assessment.drones.services;

import com.assessment.drones.domain.Review;
import com.assessment.drones.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository aReviewRepository) {
        reviewRepository = aReviewRepository;
    }

    public String addReview(Review review) {
        Integer insertResponse = reviewRepository.addReview(review);

        if (insertResponse == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }
}
