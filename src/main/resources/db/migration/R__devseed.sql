INSERT INTO user (email, password, first_name, surname, role, activated) VALUES ('admin@asg.com', '$2a$10$6443CNhV26R6qEMohATMYuleZxiUfr/ya0urCUqheo5STYOU6maqu', 'Erwin', 'Schroedinger', 'admin', 1),
                                                ('admin2@asg.com', '$2a$10$cGXMmSfOg2ZrUXUgCxaiaO4pgjJsSvcFYlKPMcFDBgVp3MqrZ2M96', 'Johannes', 'Kepler', 'admin', 1),
                                                ('client@asg.com', '$2a$10$S4MOcUgShERWJpI1EpTReeNbwKL09wElxbCLLimHdf3yrOG7H2PWG', 'Blaise', 'Pascal', 'candidate', 1),
                                                ('client2@asg.com', '$2a$10$uzGe/I2v.1LtdTPBQg8oDOe/07mlgaGyG0UoF8G5VQD7b6PAvTQHq', 'Caroline', 'Herschel', 'candidate', 1),
                                                ('client3@asg.com', '$2a$10$H2GWvDnlRJD1DuVPkfkSLujsmzSwGmM7ELKpZY0olKQFObp4GWZ56', 'Edmond', 'Halley', 'candidate', 1),
                                                ('client4@asg.com', '$2a$10$1XELQEuTrZ0JbFg0MijjI.4QFWJYMppieYp5XN8Aj6z6LIg9QwgN2', 'Enrico', 'Fermi', 'candidate', 1);


INSERT INTO address (id, line_1, line_2, city, postcode) VALUES (1, 'Studio 103, The Business Centre', '61 Wellfield Road', 'Cardiff', 'CF24 3DG'),
                                                                (2, 'Unit 14, 3 Edgar Buildings', 'George Street', 'Bath', 'BA1 2FJ'),
                                                                (3, 'Box 777, 91 Western Road', 'Brighton', 'East Sussex', 'BN1 2NW'),
                                                                (4, 'Room 67, 14 Tottenham Court Road', '', 'London', 'W1T 1JY');

INSERT INTO contact_info (id, phone_number, address_id) VALUES (1, '7911123456', 1);
INSERT INTO contact_info (id, phone_number, address_id) VALUES (2, '7597268597', 2);
INSERT INTO contact_info (id, phone_number, address_id) VALUES (3, '7911123456', 3);
INSERT INTO contact_info (id, phone_number, address_id) VALUES (4, '7911564856', 4);

INSERT INTO drone (make, model)
VALUES
       ('DJI', 'Matrice'),
       ('DJI', 'Mavic'),
       ('DJI', 'Mavic 2'),
       ('DJI', 'Mavic Air'),
       ('DJI', 'Mavic Pro'),
       ('DJI', 'Mavic Pro Platinum'),
       ('DJI', 'Phantom 1'),
       ('DJI', 'Phantom FC40'),
       ('DJI', 'Phantom 2'),
       ('DJI', 'Phantom 2 Vision'),
       ('DJI', 'Phantom 2 Vision+'),
       ('DJI', 'Phantom 3 Standard'),
       ('DJI', 'Phantom 3 4k'),
       ('DJI', 'Phantom 3 SE'),
       ('DJI', 'Phantom 3 Advanced'),
       ('DJI', 'Phantom 3 Professional'),
       ('DJI', 'Phantom 4'),
       ('DJI', 'Phantom 4 Advanced'),
       ('DJI', 'Phantom 4 Pro'),
       ('DJI', 'Phantom 4 Pro V2.0'),
       ('DJI', 'Inspire 1'),
       ('DJI', 'Inspire 1 Pro/Raw'),
       ('DJI', 'Inspire 2'),
       ('Parrot', 'Anafi'),
       ('Parrot', 'Bebop 2'),
       ('Parrot', 'Bebop 2 Power'),
       ('Parrot', 'Mambo');

INSERT INTO general_info (id, date_of_birth, place_of_birth, company_name, previous_flying_exp, preferred_location, drone_type_id, english_speaking, disability) VALUES (1, '1990/2/28', 'Cardiff', '', 'None', 'Cardiff', 1, 5, 'none');
INSERT INTO general_info (id, date_of_birth, place_of_birth, company_name, previous_flying_exp, preferred_location, drone_type_id, english_speaking, disability) VALUES (2, '1990/10/21', 'Somerset', '', 'Some', 'Somerset', 2, 5, 'none');
INSERT INTO general_info (id, date_of_birth, place_of_birth, company_name, previous_flying_exp, preferred_location, drone_type_id, english_speaking, disability) VALUES (3, '1990/12/31', 'Aberdeen', 'Microsoft', 'A lot', 'Aberdeen', 1, 5, 'none');
INSERT INTO general_info (id, date_of_birth, place_of_birth, company_name, previous_flying_exp, preferred_location, drone_type_id, english_speaking, disability) VALUES (4, '1990/11/15', 'Cardiff', 'Apple', 'A Pro', 'Cardiff', 2, 5, 'none');

INSERT INTO candidate (reference_number, user_id, contact_info_id, general_info_id) VALUES ('ASG-001-18-11', 'client@asg.com', 1, 1);
INSERT INTO candidate (reference_number, user_id, contact_info_id, general_info_id) VALUES ('ASG-002-18-11', 'client2@asg.com', 2, 2);
INSERT INTO candidate (reference_number, user_id, contact_info_id, general_info_id) VALUES ('ASG-003-18-11', 'client3@asg.com', 3, 3);
INSERT INTO candidate (reference_number, user_id, contact_info_id, general_info_id) VALUES ('ASG-001-18-12', 'client4@asg.com', 4, 4);

INSERT INTO instructor (id, user_id) VALUES (1, 'admin@asg.com');
INSERT INTO instructor (id, user_id) VALUES (2, 'admin2@asg.com');

INSERT into operators_manual (candidate_number, submitted_date, file_path)VALUES('ASG-001-18-11', '1990/10/21', '/');

INSERT INTO flight_training (candidate_number, training_type, instructor_id, skills_assessment_date) VALUES ('ASG-001-18-12', 'None', 1, '2018/12/2')