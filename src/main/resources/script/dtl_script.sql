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