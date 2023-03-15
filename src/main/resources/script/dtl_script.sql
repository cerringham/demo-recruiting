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

INSERT INTO default_company_role (name, is_active) VALUES ('Ceo', true);
INSERT INTO default_company_role (name, is_active) VALUES ('Head Of', true);
INSERT INTO default_company_role (name, is_active) VALUES ('Hr', true);
INSERT INTO default_company_role (name, is_active) VALUES ('Marketing', true);
INSERT INTO default_company_role (name, is_active) VALUES ('Backend Engineer', true);
INSERT INTO default_company_role (name, is_active) VALUES ('Frontend Engineer', true);
INSERT INTO default_company_role (name, is_active) VALUES ('Data Engineer', true);
INSERT INTO default_company_role (name, is_active) VALUES ('Mobile Engineer', true);
INSERT INTO default_company_role (name, is_active) VALUES ('Team Leader', true);
INSERT INTO default_company_role (name, is_active) VALUES ('Coo', true);
INSERT INTO default_company_role (name, is_active) VALUES ('Administration', true);
INSERT INTO default_company_role (name, is_active) VALUES ('Network And System Engineer', true);

INSERT INTO default_company (name, coo_id, is_active) VALUES ('Fortitude', null, true);
INSERT INTO default_company (name, coo_id, is_active) VALUES ('Bitrock', null, true);
INSERT INTO default_company (name, coo_id, is_active) VALUES ('RadicalBit', null, true);
INSERT INTO default_company (name, coo_id, is_active) VALUES ('Proactivity', null, true);

INSERT INTO application_constant(name, value)
VALUES('company_role_name', 30),
('job_position_minimum_skill', 2),
('job_position_title', 50),
('job_position_area', 20),
('job_position_city', 50),
('job_position_region', 30),
('job_position_country', 30),
('company_name', 20),
('job_position_status_name', 50),
('max_companies', 4);