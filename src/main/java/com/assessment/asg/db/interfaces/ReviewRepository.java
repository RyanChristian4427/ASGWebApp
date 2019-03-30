package com.assessment.asg.db.interfaces;

import com.assessment.asg.models.ReviewDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReviewRepository {
    void addReview(ReviewDto reviewDto);

    List<ReviewDto> reviewsByInstructor(String surname);
}
