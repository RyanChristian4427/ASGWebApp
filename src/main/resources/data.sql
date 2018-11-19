INSERT INTO user (username, password, access_level) VALUES ('admin', 'pass1', 'admin');
INSERT INTO user (username, password, access_level) VALUES ('user', 'pass2', 'user');

insert into client (user_id, progress_id, basic_info_id, address_id) values (1, 1, 1, 1);
insert into temp_basic_info(id, firstName, lastName) values (1, 'Ryan', 'Admin');

insert into client (user_id, progress_id, basic_info_id, address_id) values (2, 2, 2, 2);
insert into temp_basic_info(id, firstName, lastName) values (2, 'Ryan', 'User');