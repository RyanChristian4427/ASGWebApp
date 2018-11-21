package com.assessment.drones.repository;

import com.assessment.drones.domain.Review;
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
                rs.getLong("client_id"),
                rs.getLong("instructor_id"),
                rs.getString("reviewText")
        );
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

    @Override
    public List<Review> reviewsByInstructor(String lastName){
        return jdbcTemplate.query(
                "SELECT review.id, review.client_id, review.instructor_id, " +
                        "review.reviewText FROM review " +
                        "INNER JOIN `instructor` ON review.instructor_id=instructor.id " +
                        "WHERE last_name LIKE ?",
                new Object[]{"%" + lastName + "%"},
                reviewMapper);
    }

}
