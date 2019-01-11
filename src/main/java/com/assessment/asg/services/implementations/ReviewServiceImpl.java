package com.assessment.asg.services.implementations;

import com.assessment.asg.domain.ReviewDto;
import com.assessment.asg.repository.interfaces.ReviewRepository;
import com.assessment.asg.services.interfaces.CandidateService;
import com.assessment.asg.services.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CandidateService candidateService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, CandidateService candidateService) {
        this.reviewRepository = reviewRepository;
        this.candidateService = candidateService;
    }

    public void addReview(ReviewDto reviewDto) {
        //TODO link this to an instructor ID from a given first and last name
        reviewDto.setCandidateNumber(candidateService.findCandidateByCurrentUser().get().getReferenceNumber());
        reviewRepository.addReview(reviewDto);
    }

    public List<ReviewDto> reviewsByInstructor(String surname){
        return reviewRepository.reviewsByInstructor(surname);
    }
}
