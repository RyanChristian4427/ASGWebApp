package com.assessment.drones.repository;

import com.assessment.drones.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ReviewRepositoryJdbc implements ReviewRepository
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;
    }

    @Override
    public Integer addReview(Review review) {
        ArrayList<Object> params = new ArrayList<>();
        params.add(review.getClientID());
        params.add(review.getInstructorID());
        params.add(review.getReviewText());
        return jdbcTemplate.update(
                "INSERT INTO review(client_id, instructor_id, reviewText) " +
                        "VALUES(?,?,?)",
                params.toArray());
    }

}
