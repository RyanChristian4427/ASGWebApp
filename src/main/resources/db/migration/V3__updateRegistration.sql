ALTER TABLE candidate
DROP first_name,
DROP surname;

ALTER TABLE instructor
DROP first_name,
DROP surname;

ALTER TABLE user
ADD first_name VARCHAR(10) NOT NULL,
ADD surname VARCHAR(15) NOT NULL;