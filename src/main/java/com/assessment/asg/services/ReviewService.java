package com.assessment.asg.services;

import com.assessment.asg.db.ReviewRepository;
import com.assessment.asg.models.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface ReviewService {

    void addReview(ReviewDto reviewDto);
}

@Service
class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CandidateService candidateService;

    @Autowired
    public ReviewServiceImpl(final ReviewRepository reviewRepository, final CandidateService candidateService) {
        this.reviewRepository = reviewRepository;
        this.candidateService = candidateService;
    }

    public void addReview(final ReviewDto reviewDto) {
        //TODO link this to an instructor ID from a given first and last name
        reviewDto.setCandidateNumber(candidateService.findCandidateByCurrentUser().get().getReferenceNumber());
        reviewRepository.addReview(reviewDto);
    }
}