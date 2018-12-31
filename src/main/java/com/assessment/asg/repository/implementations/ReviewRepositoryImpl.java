package com.assessment.asg.repository.implementations;

import com.assessment.asg.domain.ReviewDto;
import com.assessment.asg.repository.interfaces.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository
{
    private JdbcTemplate jdbcTemplate;
    private RowMapper<ReviewDto> reviewMapper;


    @Autowired
    public ReviewRepositoryImpl(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        reviewMapper = (rs, i) -> new ReviewDto(
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getString("reviewText")
        );
    }

    @Override
    public void addReview(ReviewDto reviewDto) {
        jdbcTemplate.update(
                "INSERT INTO review(candidate_number, instructor_id, review_text) " +
                        "VALUES(?,?,?)", reviewDto.getCandidateNumber(), 1L, reviewDto.getReviewText());
    }

    @Override
    public List<ReviewDto> reviewsByInstructor(String surname){
        return jdbcTemplate.query(
                "SELECT review.id, review.candidate_number, review.instructor_id, " +
                        "review.reviewText FROM review " +
                        "INNER JOIN `instructor` ON review.instructor_id=instructor.id " +
                        "WHERE surname LIKE ?",
                new Object[]{"%" + surname + "%"},
                reviewMapper);
    }

}
