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

CREATE TABLE IF NOT EXISTS `general` (
	`id` INT NOT NULL,
	`first_name` VARCHAR(20) NOT NULL,
    `surname` VARCHAR(20) NOT NULL,
    `date_of_birth` DATE NOT NULL,
    `place_of_birth` VARCHAR(50) NOT NULL,
    `address_id` INT NOT NULL,
    `company_name` VARCHAR(20) NOT NULL,
    `previous_flying_exp` TEXT NOT NULL,
    `preferred_location` VARCHAR(50) NOT NULL,
    `drone_type` VARCHAR(50) NOT NULL,
    `candidate_number` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`id`),
    UNIQUE (`candidate_number`),
    FOREIGN KEY (`candidate_number`) REFERENCES `candidate` (`candidate_number`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `review` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `candidate_number` INT UNSIGNED NOT NULL,
  `instructor_id` INT UNSIGNED NOT NULL,
  `review_text` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;