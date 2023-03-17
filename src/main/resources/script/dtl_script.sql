INSERT INTO company (name, coo_id, is_active) VALUES ('Fortitude', null, true);
INSERT INTO company (name, coo_id, is_active) VALUES ('Bitrock', null, true);
INSERT INTO company (name, coo_id, is_active) VALUES ('RadicalBit', null, true);
INSERT INTO company (name, coo_id, is_active) VALUES ('Proactivity', null, true);

INSERT INTO company_role (name, is_active) VALUES ('Ceo', true);
INSERT INTO company_role (name, is_active) VALUES ('Head Of', true);
INSERT INTO company_role (name, is_active) VALUES ('Hr', true);
INSERT INTO company_role (name, is_active) VALUES ('Marketing', true);
INSERT INTO company_role (name, is_active) VALUES ('Backend Engineer', true);
INSERT INTO company_role (name, is_active) VALUES ('Frontend Engineer', true);
INSERT INTO company_role (name, is_active) VALUES ('Data Engineer', true);
INSERT INTO company_role (name, is_active) VALUES ('Mobile Engineer', true);
INSERT INTO company_role (name, is_active) VALUES ('Team Leader', true);
INSERT INTO company_role (name, is_active) VALUES ('Coo', true);
INSERT INTO company_role (name, is_active) VALUES ('Administration', true);
INSERT INTO company_role (name, is_active) VALUES ('Network And System Engineer', true);

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



INSERT INTO job_position (title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES ('Software Engineer', 'Sviluppo software', 'Cerchiamo uno sviluppatore software con esperienza', 'Milano', 'Lombardia', 'Italia', 1, 1, true);

INSERT INTO job_position (title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES ('Software Engineer', 'Sviluppo software', 'Cerchiamo uno sviluppatore software con esperienza', 'Roma', 'Lazio', 'Italia', 2, 1, true);

INSERT INTO job_position (title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES ('Software Engineer', 'Sviluppo software', 'Cerchiamo uno sviluppatore software con esperienza', 'Napoli', 'Campania', 'Italia', 3, 1, true);

INSERT INTO job_position (title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES ('Senior Software Engineer', 'IT', 'We are looking for a Senior Software Engineer to join our team and help us develop innovative solutions for our clients.', 'San Francisco', 'California', 'USA', 1, 1, true);

INSERT INTO job_position (title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES ('Marketing Manager', 'Marketing', 'We are seeking a Marketing Manager to lead our marketing team and develop creative campaigns to promote our products and services.', 'London', 'England', 'UK', 2, 2, true);

INSERT INTO job_position (title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES ('Sviluppatore Front-end', 'Sviluppatore', 'We are looking for a talented Front_end developer to help us to create beutiful ui experience', 'New York', 'New York', 'USA', 3, 1, true);

INSERT INTO job_position (title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES ('Java developer', 'Back-end', 'We are looking for a talented Java developer to help us develop software', 'Milano', 'Lombardia', 'Italia', 4, 1, true);


INSERT INTO job_position (title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES ('Senior Software Engineer', 'IT', 'We are looking for a Senior Software Engineer to join our team and help us develop innovative solutions for our clients.', 'San Francisco', 'California', 'USA', 1, 3, true);

INSERT INTO job_position (title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES ('Marketing Manager', 'Marketing', 'We are seeking a Marketing Manager to lead our marketing team and develop creative campaigns to promote our products and services.', 'London', 'England', 'UK', 2, 3, true);

INSERT INTO job_position (title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES ('Sviluppatore Front-end', 'Sviluppatore', 'We are looking for a talented Front_end developer to help us to create beutiful ui experience', 'New York', 'New York', 'USA', 3, 3, true);

INSERT INTO job_position (title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES ('Java developer', 'Back-end', 'We are looking for a talented Java developer to help us develop software', 'Milano', 'Lombardia', 'Italia', 4, 3, true);