INSERT INTO company (name, coo_id, is_active) VALUES ('Fortitude', null, true);
INSERT INTO company (name, coo_id, is_active) VALUES ('Bitrock', null, true);
INSERT INTO company (name, coo_id, is_active) VALUES ('RadicalBit', null, true);
INSERT INTO company (name, coo_id, is_active) VALUES ('Proactivity', null, true);

INSERT INTO company_role (name, is_active) VALUES ('CEO', true);
INSERT INTO company_role (name, is_active) VALUES ('Head Of', true);
INSERT INTO company_role (name, is_active) VALUES ('HR', true);
INSERT INTO company_role (name, is_active) VALUES ('Marketing', true);
INSERT INTO company_role (name, is_active) VALUES ('Backend Engineer', true);
INSERT INTO company_role (name, is_active) VALUES ('Frontend Engineer', true);
INSERT INTO company_role (name, is_active) VALUES ('Data Engineer', true);
INSERT INTO company_role (name, is_active) VALUES ('Mobile Engineer', true);
INSERT INTO company_role (name, is_active) VALUES ('Team Leader', true);
INSERT INTO company_role (name, is_active) VALUES ('COO', true);
INSERT INTO company_role (name, is_active) VALUES ('Amministration', true);
INSERT INTO company_role (name, is_active) VALUES ('Network and System Engineer', true);

INSERT INTO job_interview_status (name, description, is_active) VALUES ('New', 'A job interview that is planned but not yet done', true);
INSERT INTO job_interview_status (name, description, is_active) VALUES ('Behavioral', 'A behavioral interview is successfully done', true);
INSERT INTO job_interview_status (name, description, is_active) VALUES ('Technical to do', 'A technical interview is planned but not yet done', true);
INSERT INTO job_interview_status (name, description, is_active) VALUES ('Technical', 'A technical interview is successfully done', true);
INSERT INTO job_interview_status (name, description, is_active) VALUES ('Final step', 'Last step is scheduled but not yet done', true);
INSERT INTO job_interview_status (name, description, is_active) VALUES ('Success', 'Last interview is successfully done', true);
INSERT INTO job_interview_status (name, description, is_active) VALUES ('Failed', 'Used for not good interview ', true);

INSERT INTO job_interview_type (name, is_active) VALUES ('Behavioral', true);
INSERT INTO job_interview_type (name, is_active) VALUES ('Technical', true);
INSERT INTO job_interview_type (name, is_active) VALUES ('Contract proposal', true);

INSERT INTO job_position_status (name, is_active) VALUES ('New', true);
INSERT INTO job_position_status (name, is_active) VALUES ('Closed', true);
INSERT INTO job_position_status (name, is_active) VALUES ('Urgent', true);



INSERT INTO role (name, description, is_active)
VALUES ('admin', 'an account that can do everything', true);
INSERT INTO role (name, description, is_active)
VALUES ('hr', 'an account that is member of hr staff', true);
INSERT INTO role (name, description, is_active)
VALUES ('dev', 'an account that is a sw engineer', true);


INSERT INTO account (name, surname, email, username, password, is_active, role_id)
VALUES ('Amira', 'Smith', 'amirasmith@gmail.com', 'amirasmith@gmail.com', 'amirAsss6&' , true, 1);
INSERT INTO account (name, surname, email, username, password, is_active, role_id)
VALUES ('Valerie', 'Brock', 'valeriebrock@gmail.com', 'valeriebrock@gmail.com', 'Valieri1e&' , true, 2);
INSERT INTO account (name, surname, email, username, password, is_active, role_id)
VALUES ('Guadalupe', 'Barton', 'guadalupebarton@gmail.com', 'guadalupebarton@gmail.com', 'guada4!' , true, 2);
INSERT INTO account (name, surname, email, username, password, is_active, role_id)
VALUES ('Juan', 'Davis', 'juandavis@gmail.com', 'juandavis@gmail.com', 'Juanes9%!' , true, 3);
INSERT INTO account (name, surname, email, username, password, is_active, role_id)
VALUES ('Kenneth', 'Foster', 'kennethfoster@gmail.com', 'kennethfoster@gmail.com', 'kennY8!' , true, 3);
INSERT INTO account (name, surname, email, username, password, is_active, role_id)
VALUES ('Gloria', 'Adams', 'gloriadams@gmail.com', 'gloriadams@gmail.com', 'Gloglo33!' , true, 3);

INSERT INTO job_interview_status(name, description, is_active)
VALUES ('Failed', 'Interview failed', true);
INSERT INTO job_interview_status(name, description, is_active)
VALUES ('Hired', null, true);

INSERT INTO job_interview (date, hour, place, candidate_id, employee_id, job_interview_type_id, job_position_id, rating, note, job_interview_status_id, is_active)
VALUES ('2023-03-10', '09:30:00', 'Room A', 2, 5, 1, 7, 4, 'Good communication skills', 1, true);
INSERT INTO job_interview (date, hour, place, candidate_id, employee_id, job_interview_type_id, job_position_id, rating, note, job_interview_status_id, is_active)
VALUES ('2023-03-11', '14:00:00', 'Conference room', 2, 5, 1, 8, 3, 'Lacks experience', 2, true);
INSERT INTO job_interview (date, hour, place, candidate_id, employee_id, job_interview_type_id, job_position_id, rating, note, job_interview_status_id, is_active)
VALUES ('2023-03-12', '10:00:00', 'Remote', 2, 5, 1, 9, 5, 'Impressive portfolio', 1, true);
INSERT INTO job_interview (date, hour, place, candidate_id, employee_id, job_interview_type_id, job_position_id, rating, note, job_interview_status_id, is_active)
VALUES ('2023-03-13', '10:00:00', 'Remote', 2, 5, 1, 9, 5, 'Impressive portfolio', 5, true);

INSERT INTO job_interview (date, hour, place, candidate_id, employee_id, job_interview_type_id, job_position_id, rating, note, job_interview_status_id, is_active)
VALUES ('2023-04-10', '10:00:00', 'Remote', 4, 5, 1, 9, 5, 'Impressive portfolio', 2, true);
INSERT INTO job_interview (date, hour, place, candidate_id, employee_id, job_interview_type_id, job_position_id, rating, note, job_interview_status_id, is_active)
VALUES ('2023-04-12', '10:00:00', 'Remote', 4, 5, 1, 9, 5, 'Impressive portfolio', 2, true);
INSERT INTO job_interview (date, hour, place, candidate_id, employee_id, job_interview_type_id, job_position_id, rating, note, job_interview_status_id, is_active)
VALUES ('2023-04-13', '10:00:00', 'Remote', 4, 5, 1, 9, 5, 'Impressive portfolio', 5, true);
