package com.assessment.drones.repository.implementations;

import com.assessment.drones.domain.Review;
import com.assessment.drones.repository.interfaces.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepositoryJdbc implements ReviewRepository
{
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Review> reviewMapper;


    @Autowired
    public ReviewRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        reviewMapper = (rs, i) -> new Review(
                rs.getLong("id"),
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getString("reviewText")
        );
    }

    @Override
    public Integer addReview(Review review) {
        ArrayList<Object> params = new ArrayList<>();
        params.add(review.getCandidateNumber());
        params.add(review.getInstructorID());
        params.add(review.getReviewText());
        return jdbcTemplate.update(
                "INSERT INTO review(candidate_number, instructor_id, reviewText) " +
                        "VALUES(?,?,?)",
                params.toArray());
    }

    @Override
    public List<Review> reviewsByInstructor(String surname){
        return jdbcTemplate.query(
                "SELECT review.id, review.candidate_number, review.instructor_id, " +
                        "review.reviewText FROM review " +
                        "INNER JOIN `instructor` ON review.instructor_id=instructor.id " +
                        "WHERE surname LIKE ?",
                new Object[]{"%" + surname + "%"},
                reviewMapper);
    }

}
