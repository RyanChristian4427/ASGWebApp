ALTER TABLE review
  DROP FOREIGN KEY review_ibfk_1,
  DROP candidate_id,
  ADD candidate_number VARCHAR(13) NOT NULL,
  ADD FOREIGN KEY (candidate_number) REFERENCES candidate (reference_number);