package com.assessment.asg.repository.interfaces;

import com.assessment.asg.domain.ReviewDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReviewRepository
{
    void addReview(ReviewDto reviewDto);

    List<ReviewDto> reviewsByInstructor(String surname);
}
