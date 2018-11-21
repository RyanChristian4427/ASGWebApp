SET MODE MySQL;
SET IGNORECASE=TRUE;

CREATE TABLE IF NOT EXISTS `candidate` (
	`number` VARCHAR(20) NOT NULL,
    `course_number` VARCHAR(50) NOT NULL,
    `course_start` DATE NOT NULL,
    `course_end` DATE NOT NULL,
    PRIMARY KEY (`number`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `address` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `line_1` VARCHAR(50) NOT NULL,
    `line_2` VARCHAR(50) NOT NULL,
    `city` VARCHAR(20) NOT NULL,
    `postcode` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `user` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `access_level` VARCHAR(10),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `general` (
	`id` INT NOT NULL,
	`first_name` VARCHAR(20) NOT NULL,
    `surname` VARCHAR(20) NOT NULL,
    `date_of_birth` DATE NOT NULL,
    `place_of_birth` VARCHAR(50) NOT NULL,
    `address_id` INT UNSIGNED NOT NULL,
    `company_name` VARCHAR(20) NOT NULL,
    `previous_flying_exp` TEXT NOT NULL,
    `preferred_location` VARCHAR(50) NOT NULL,
    `drone_type` VARCHAR(50) NOT NULL,
    `candidate_number` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
    FOREIGN KEY (`candidate_number`) REFERENCES `candidate` (`number`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `instructor` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` INT UNSIGNED NOT NULL,
    `first_name` VARCHAR(20) NOT NULL,
    `surname` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`),
	FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `personal_data` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `candidate_number` VARCHAR(20) NOT NULL,
    `telephone_number` INT NOT NULL,
    `email` VARCHAR(20) NOT NULL,
    `review_date` DATE NOT NULL,
    `delete_date` DATE NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`candidate_number`) REFERENCES `candidate` (`number`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `flying_training` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(20) NOT NULL,
    `instructor_id` INT UNSIGNED NOT NULL,
    `skills_assessment_date` DATE NOT NULL,
    `candidate_number` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`candidate_number`) REFERENCES `candidate` (`number`),
    FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `ground_school` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `instructor_id` INT UNSIGNED NOT NULL,
    `completion_date` DATE NOT NULL,
    `question_bank` INT NOT NULL,
    `pass_date` DATE NOT NULL,
    `pass_result` INT NOT NULL,
    `resit` VARCHAR(1) NOT NULL,
    `candidate_number` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`candidate_number`) REFERENCES `candidate` (`number`),
    FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `operators_manual` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `instructor_id` INT UNSIGNED NOT NULL,
    `candidate_number` VARCHAR(20) NOT NULL,
    `submitted_date` DATE NOT NULL,
    `pass_date` DATE NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`candidate_number`) REFERENCES `candidate` (`number`),
    FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `flight_assessment` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `instructor_id` INT UNSIGNED NOT NULL,
    `candidate_number` VARCHAR(20) NOT NULL,
    `insurance` VARCHAR(1) NOT NULL,
    `logged_hours` TIME NOT NULL,
    `suas_category` VARCHAR(20) NOT NULL,
    `assessment_pass_date` DATE NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`candidate_number`) REFERENCES `candidate` (`number`),
    FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `recommendations` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `candidate_number` VARCHAR(20) NOT NULL,
    `asg_recomend_date` DATE NOT NULL,
    `flight_competence_date` DATE NOT NULL,
    `application_data_date` DATE NOT NULL,
    `application_date` DATE NOT NULL,
    `caa_approval_date` DATE NOT NULL,
    `overall_comments_approval_by_caa` DATE NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`candidate_number`) REFERENCES `candidate` (`number`)
) ENGINE=InnoDB;