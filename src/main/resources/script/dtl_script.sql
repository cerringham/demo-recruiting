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

INSERT INTO role(name, description, is_active)
VALUES('admin', 'an account that can do everything', true),
('hr', 'an account that is member of hr staff', true),
('dev', 'an account that in a sw engineer', true);

INSERT INTO account(name, surname, email, username, password, is_active, role_id)
VALUES('Alessio', 'Cassarino', 'alessio.cassarino@proactivity.it', 'alessio.cassarino@proactivity.it', 'cef1fb10f60529028a71f58e54ed07b', true, 3),
('Veronica', 'Zuniga', 'veronica.zuniga@proactivity.it', 'veronica.zuniga@proactivity.it', '22b5ac7ea72a5ee3bfc6b3eb461f2fc', true, 2),
('Luigi', 'Cerrato', 'luigi.cerrato@proactivity.it', 'luigi.cerrato@proactivity.it', '94ca112be7fc3f3934c45c6809875168', true, 1),
('Paolo', 'Bandi', 'paolo.bandi@proactivity.it', 'paolo.bandi@proactivity.it', 'cbdc7572ff7d07cc6807a5b102a3b93', true, 3),
('Martina', 'Salamone', 'martina.salamone@proactivity.it', 'martina.salamone@proactivity.it', '2dc398408a3742f6ca2090dfe0748868', true, 2),
('Daniele', 'Gennaro', 'daniele.gennaro@proactivity.it', 'daniele.gennaro@proactivity.it', 'c8fab6fd5e80259f97763f616a24b7fb', true, 3);