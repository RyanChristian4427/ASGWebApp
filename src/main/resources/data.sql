INSERT INTO user (id, email, password, role) VALUES (1, 'admin@asg.com', '$2a$10$6443CNhV26R6qEMohATMYuleZxiUfr/ya0urCUqheo5STYOU6maqu', 'admin');
INSERT INTO user (id, email, password, role) VALUES (2, 'admin2@asg.com', '$2a$10$cGXMmSfOg2ZrUXUgCxaiaO4pgjJsSvcFYlKPMcFDBgVp3MqrZ2M96', 'admin');
INSERT INTO user (id, email, password, role) VALUES (3, 'client@asg.com', '$2a$10$S4MOcUgShERWJpI1EpTReeNbwKL09wElxbCLLimHdf3yrOG7H2PWG', 'client');
INSERT INTO user (id, email, password, role) VALUES (4, 'client2@asg.com', '$2a$10$uzGe/I2v.1LtdTPBQg8oDOe/07mlgaGyG0UoF8G5VQD7b6PAvTQHq', 'client');
INSERT INTO user (id, email, password, role) VALUES (5, 'client3@asg.com', '$2a$10$H2GWvDnlRJD1DuVPkfkSLujsmzSwGmM7ELKpZY0olKQFObp4GWZ56', 'client');
INSERT INTO user (id, email, password, role) VALUES (6, 'client4@asg.com', '$2a$10$1XELQEuTrZ0JbFg0MijjI.4QFWJYMppieYp5XN8Aj6z6LIg9QwgN2', 'client');

INSERT INTO address (id, line_1, line_2, city, postcode) VALUES (1, 'Studio 103, The Business Centre', '61 Wellfield Road', 'Cardiff', 'CF24 3DG');
INSERT INTO address (id, line_1, line_2, city, postcode) VALUES (2, 'Unit 14, 3 Edgar Buildings', 'George Street', 'Bath', 'BA1 2FJ');
INSERT INTO address (id, line_1, line_2, city, postcode) VALUES (3, 'Box 777, 91 Western Road', 'Brighton', 'East Sussex', 'BN1 2NW');
INSERT INTO address (id, line_1, line_2, city, postcode) VALUES (4, 'Room 67, 14 Tottenham Court Road', '', 'London', 'W1T 1JY');

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

INSERT INTO general_info (id, date_of_birth, place_of_birth, company_name, previous_flying_exp, preferred_location, drone_type_id) VALUES (1, '1990/2/28', 'Cardiff', '', 'None', 'Cardiff', 1);
INSERT INTO general_info (id, date_of_birth, place_of_birth, company_name, previous_flying_exp, preferred_location, drone_type_id) VALUES (2, '1990/10/21', 'Somerset', '', 'Some', 'Somerset', 2);
INSERT INTO general_info (id, date_of_birth, place_of_birth, company_name, previous_flying_exp, preferred_location, drone_type_id) VALUES (3, '1990/12/31', 'Aberdeen', 'Microsoft', 'A lot', 'Aberdeen', 1);
INSERT INTO general_info (id, date_of_birth, place_of_birth, company_name, previous_flying_exp, preferred_location, drone_type_id) VALUES (4, '1990/11/15', 'Cardiff', 'Apple', 'A Pro', 'Cardiff', 2);

INSERT INTO candidate (reference_number, user_id, first_name, surname, contact_info_id, general_info_id) VALUES ('ASG-001-18-11', 3, 'Blaise', 'Pascal', 1, 1);
INSERT INTO candidate (reference_number, user_id, first_name, surname, contact_info_id, general_info_id) VALUES ('ASG-002-18-11', 4, 'Caroline', 'Herschel', 2, 2);
INSERT INTO candidate (reference_number, user_id, first_name, surname, contact_info_id, general_info_id) VALUES ('ASG-003-18-11', 5, 'Edmond', 'Halley', 3, 3);
INSERT INTO candidate (reference_number, user_id, first_name, surname, contact_info_id, general_info_id) VALUES ('ASG-001-18-12', 6, 'Enrico', 'Fermi', 4, 4);

INSERT INTO instructor (id, user_id, first_name, surname) VALUES (1, 1, 'Erwin', 'Schroedinger');
INSERT INTO instructor (id, user_id, first_name, surname) VALUES (2, 2, 'Johannes', 'Kepler');

INSERT into operators_manual (candidate_number, submitted_date)VALUES('ASG-001-18-11', '1990/10/21');