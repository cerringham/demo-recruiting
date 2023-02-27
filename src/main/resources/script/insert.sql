INSERT INTO company(name, coo_id, is_active)
VALUES('Bitrock', null, true);
INSERT INTO company(name, coo_id, is_active)
VALUES('Proactivity', null, true);
INSERT INTO company(name, coo_id, is_active)
VALUES('Radicalbit', null, true);
INSERT INTO company(name, coo_id, is_active)
VALUES('Fortitude', null, true);

INSERT INTO expertise(name, is_active)
VALUES('Junior', true);
INSERT INTO expertise(name, is_active)
VALUES('Middle', true);
INSERT INTO expertise(name, is_active)
VALUES('Senior', true);

INSERT INTO company_role(name, is_active)
VALUES('COO', true);
INSERT INTO company_role(name, is_active)
VALUES('CEO', true);
INSERT INTO company_role(name, is_active)
VALUES('HR', true);
INSERT INTO company_role(name, is_active)
VALUES('HeadOf', true);
INSERT INTO company_role(name, is_active)
VALUES('Back-End Engineer', true);
INSERT INTO company_role(name, is_active)
VALUES('Front-End Engineer', true);
INSERT INTO company_role(name, is_active)
VALUES('DevOps Engineer', true);
INSERT INTO company_role(name, is_active)
VALUES('Data Engineer', true);

INSERT INTO job_position_status(name, is_active)
VALUES('Open', true);
INSERT INTO job_position_status(name, is_active)
VALUES('Closed', true);

INSERT INTO job_position(title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES('Project Manager','Milan','Governance of delivery through the management of the resources involved in the customer achievement of the turnover and margins objectives assigned to the customer
Management of relations with the various customer contacts
Guarantee the well-being of your work team
Identification, promotion and coordination of the launch of new proposals in terms of projects, services and products, with the involvement of the Sales Department
Optimization of the management of resources on projects (through collaboration with HR and Planning)','Milan','Lombardy','IT',1,1, true);
INSERT INTO job_position(title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES('Devops Engineer 2021','Milan','- As DevOps Engineer you will collaborate closely with the backend and frontend engineering teams to create scalable and self-healing infrastructure for serving our customers real-time insights.
- You will create tools and deployment systems that enable high feature velocity and developers quality of life.﻿','Milan','Lombardy','IT',1,1, true);
INSERT INTO job_position(title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES('Java Developer','Milan','You will deal with several kinds of projects and technology, ranging from traditional JEE to the most advanced real time stream processing and micro service architectures. We work with customer based in several European countries, you will work in an international environment within international teams
Depending on your skills and actual experience, you will implement complex business requirements, or contribute to the definition of a whole micro service architecture','Milan','Lombardy','IT',1,1, true);
INSERT INTO job_position(title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES('Mobile Developer iOS','Milan','In this role you will improve your abilities with different frameworks and you will join a young team of developers who will be happy to share with you their knowledge, passions and enthusiasm. You will design and develop iOS native applications working with SWIFT or Objective-C','Milan','Lombardy','IT',1,1, true);
INSERT INTO job_position(title, area, description, city, region, country, company_id, job_position_status_id, is_active)
VALUES('Data Engineer','Milan','As data engineer you will have to design and build systems that collect, manage and convert data into usable information using with different state-of-the-art big-data frameworks, either on cloud and hybrid/on-prems. You will deal with both green fields projects and evolutions of existing stacks/pipelines.','Milan','Lombardy','IT',1,1, true);

INSERT INTO skill(name, is_active)
VALUES('Java', true);
INSERT INTO skill(name, is_active)
VALUES('Javascript', true);
INSERT INTO skill(name, is_active)
VALUES('Spring Data', true);
INSERT INTO skill(name, is_active)
VALUES('Python', true);
INSERT INTO skill(name, is_active)
VALUES('Spring MVC', true);
INSERT INTO skill(name, is_active)
VALUES('Database Postgress', true);
INSERT INTO skill(name, is_active)
VALUES('HTML', true);

INSERT INTO skill_level(job_position_id, skill_id, level, is_active)
VALUES(3, 3, 'ADVANCED', true);
INSERT INTO skill_level(job_position_id, skill_id, level, is_active)
VALUES(3, 3, 'BASIC', true);
INSERT INTO skill_level(job_position_id, skill_id, level, is_active)
VALUES(3, 3, 'INTERMEDIATE', true);
INSERT INTO skill_level(job_position_id, skill_id, level, is_active)
VALUES(4, 4, 'ADVANCED', true);
INSERT INTO skill_level(job_position_id, skill_id, level, is_active)
VALUES(4, 4, 'BASIC', true);
INSERT INTO skill_level(job_position_id, skill_id, level, is_active)
VALUES(4, 4, 'INTERMEDIATE', true);
INSERT INTO skill_level(job_position_id, skill_id, level, is_active)
VALUES(1, 6, 'ADVANCED', true);
INSERT INTO skill_level(job_position_id, skill_id, level, is_active)
VALUES(1, 6, 'BASIC', true);
INSERT INTO skill_level(job_position_id, skill_id, level, is_active)
VALUES(1, 6, 'INTERMEDIATE', true);