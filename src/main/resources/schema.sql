CREATE SCHEMA IF NOT EXISTS ASG;
USE ASG;

CREATE TABLE IF NOT EXISTS candidate (
	reference_number VARCHAR(20) NOT NULL,
    user_id INT UNSIGNED NOT NULL,
    first_name VARCHAR(10) NOT NULL,
    surname VARCHAR(15) NOT NULL,
    contact_info_id INT UNSIGNED NOT NULL,
    general_info_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (reference_number),
    FOREIGN KEY (contact_info_id) REFERENCES contact_info (id),
    FOREIGN KEY (general_info_id) REFERENCES general_info (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS user (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    access_level VARCHAR(10),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS contact_info (
	id INT UNSIGNED NOT NULL,
    telephone_number INT NOT NULL,
    review_date DATE NOT NULL,
    delete_date DATE NOT NULL,
    address_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (address_id) REFERENCES address(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS address (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    line_1 VARCHAR(50) NOT NULL,
    line_2 VARCHAR(50) NOT NULL,
    city VARCHAR(20) NOT NULL,
    postcode VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS general_info (
	id INT UNSIGNED NOT NULL,
    date_of_birth DATE NOT NULL,
    place_of_birth VARCHAR(50) NOT NULL,
    company_name VARCHAR(20) NOT NULL,
    previous_flying_exp TEXT NOT NULL,
    preferred_location VARCHAR(50) NOT NULL,
    drone_type_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (drone_type_id) REFERENCES drone (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS drone (
	id INT UNSIGNED NOT NULL,
    make VARCHAR(15) NOT NULL,
    model VARCHAR(20) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS instructor (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id INT UNSIGNED NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS course (
	course_number VARCHAR(20) NOT NULL,
    course_name VARCHAR(20) NOT NULL,
    course_start DATE NOT NULL,
    course_end DATE NOT NULL,
    PRIMARY KEY (course_number)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS flying_training (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    candidate_number VARCHAR(20) NOT NULL,
    training_type VARCHAR(20) NOT NULL,
    instructor_id INT UNSIGNED NOT NULL,
    skills_assessment_date DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (candidate_number) REFERENCES candidate (reference_number),
    FOREIGN KEY (instructor_id) REFERENCES instructor (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS ground_school (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    candidate_number VARCHAR(20) NOT NULL,
    instructor_id INT UNSIGNED NOT NULL,
    completion_date DATE NOT NULL,
    question_bank INT NOT NULL,
    pass_date DATE NOT NULL,
    pass_result INT UNSIGNED NOT NULL,
    resit TINYINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (candidate_number) REFERENCES candidate (reference_number),
    FOREIGN KEY (instructor_id) REFERENCES instructor (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS operators_manual (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    candidate_number VARCHAR(20) NOT NULL,
    instructor_id INT UNSIGNED NOT NULL,
    submitted_date DATE NOT NULL,
    pass_date DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (candidate_number) REFERENCES candidate (reference_number),
    FOREIGN KEY (instructor_id) REFERENCES instructor (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS flight_assessment (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    candidate_number VARCHAR(20) NOT NULL,
    instructor_id INT UNSIGNED NOT NULL,
    insurance VARCHAR(1) NOT NULL,
    logged_hours TIME NOT NULL,
    suas_category VARCHAR(20) NOT NULL,
    assessment_pass_date DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (candidate_number) REFERENCES candidate (reference_number),
    FOREIGN KEY (instructor_id) REFERENCES instructor (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS recommendations (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    candidate_number VARCHAR(20) NOT NULL,
    asg_recomend_date DATE NOT NULL,
    flight_competence_date DATE NOT NULL,
    application_data_date DATE NOT NULL,
    application_date DATE NOT NULL,
    caa_approval_date DATE NOT NULL,
    overall_comments_approval_by_caa DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (candidate_number) REFERENCES candidate (reference_number)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS review (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    candidate_number VARCHAR(20) NOT NULL,
    instructor_id INT UNSIGNED NOT NULL,
    review_text TEXT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (candidate_number) REFERENCES candidate (reference_number),
    FOREIGN KEY (instructor_id) REFERENCES instructor (id)
) ENGINE = InnoDB;
