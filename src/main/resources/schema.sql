SET MODE MySQL;
SET IGNORECASE=TRUE;

CREATE TABLE IF NOT EXISTS `candidate` (
	`candidate_number` VARCHAR(20) NOT NULL,
  `course_number` VARCHAR(50) NOT NULL,
  `course_start` DATE NOT NULL,
  `course_end` DATE NOT NULL,
  PRIMARY KEY (`candidate_number`),
  UNIQUE KEY (`candidate_number`)
)
ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `review` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `candidate_number` INT UNSIGNED NOT NULL,
  `instructor_id` INT UNSIGNED NOT NULL,
  `review_text` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;