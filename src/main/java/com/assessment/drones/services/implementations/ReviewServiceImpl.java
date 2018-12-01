package com.assessment.drones.services.implementations;

import com.assessment.drones.domain.Review;
import com.assessment.drones.repository.interfaces.ReviewRepository;
import com.assessment.drones.services.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public String addReview(Review review) {
        Integer insertResponse = reviewRepository.addReview(review);

        if (insertResponse == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }

    public List<Review> reviewsByInstructor(String surname){
        return reviewRepository.reviewsByInstructor(surname);
    }
}
