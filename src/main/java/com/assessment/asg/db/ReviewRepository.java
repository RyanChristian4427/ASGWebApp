package com.assessment.asg.db;

import com.assessment.asg.models.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface ReviewRepository {
    void addReview(ReviewDto reviewDto);

    List<ReviewDto> reviewsByInstructor(String surname);
}

@Repository
class ReviewRepositoryImpl implements ReviewRepository {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<ReviewDto> reviewMapper;


    @Autowired
    public ReviewRepositoryImpl(final JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        reviewMapper = (rs, i) -> new ReviewDto(
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getString("reviewText")
        );
    }

    @Override
    public void addReview(final ReviewDto reviewDto) {
        jdbcTemplate.update(
                "INSERT INTO review(candidate_number, instructor_id, review_text) " +
                        "VALUES(?,?,?)", reviewDto.getCandidateNumber(), 1L, reviewDto.getReviewText());
    }

    //TODO broken item left behind from the removal of id from the user table
    @Override
    public List<ReviewDto> reviewsByInstructor(final String surname) {
        return jdbcTemplate.query(
                "SELECT review.id, review.candidate_number, review.instructor_id, " +
                        "review.review_text FROM review " +
                        "INNER JOIN `instructor` ON review.instructor_id=instructor.id " +
                        "INNER JOIN `user` ON instructor.user_id=user.id " +
                        "WHERE surname LIKE ?",
                new Object[]{"%" + surname + "%"},
                reviewMapper);
    }
}