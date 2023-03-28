CREATE DATABASE recruiting;

CREATE TABLE IF NOT EXISTS company (
id SERIAL PRIMARY KEY,
name VARCHAR(20),
coo_id INT,
is_active BOOLEAN NOT NULL);

CREATE TABLE IF NOT EXISTS job_position_status (
id SERIAL PRIMARY KEY,
name VARCHAR(50),
is_active BOOLEAN NOT NULL);

CREATE TABLE IF NOT EXISTS skill (
id SERIAL PRIMARY KEY,
name VARCHAR(100) NOT NULL UNIQUE,
is_active BOOLEAN NOT NULL);

CREATE TABLE IF NOT EXISTS job_position (
id SERIAL PRIMARY KEY,
title VARCHAR(50),
area VARCHAR(20),
description TEXT,
city VARCHAR(50),
region VARCHAR(30),
country VARCHAR(30),
company_id INT,
job_position_status_id INT,
is_active BOOLEAN NOT NULL,
FOREIGN KEY (company_id) REFERENCES company(id),
FOREIGN KEY (job_position_status_id) REFERENCES job_position_status(id)
);

CREATE TABLE IF NOT EXISTS skill_level (
id SERIAL PRIMARY KEY,
job_position_id INT,
skill_id INT,
level VARCHAR(50),
is_active BOOLEAN NOT NULL,
FOREIGN KEY (skill_id) REFERENCES skill(id),
FOREIGN KEY (job_position_id) REFERENCES job_position(id)
);

CREATE TABLE IF NOT EXISTS expertise (
id SERIAL PRIMARY KEY,
name VARCHAR(10) NOT NULL UNIQUE,
is_active BOOLEAN NOT NULL);

CREATE TABLE IF NOT EXISTS candidate (
id SERIAL PRIMARY KEY,
fiscal_code VARCHAR(16) NOT NULL UNIQUE,
name VARCHAR(50),
surname VARCHAR(50),
birth_date DATE NOT NULL,
city_of_birth VARCHAR(50),
country_of_birth VARCHAR(50),
city_of_residence VARCHAR(50),
street_of_residence VARCHAR(50),
region_of_residence VARCHAR(50),
country_of_residence VARCHAR(50),
email VARCHAR(150) NOT NULL UNIQUE,
phone_number VARCHAR(20) UNIQUE,
gender VARCHAR(1),
expertise_id INT,
is_active BOOLEAN NOT NULL,
FOREIGN KEY (expertise_id) REFERENCES expertise(id)
);

CREATE TABLE IF NOT EXISTS curriculum (
id SERIAL PRIMARY KEY,
candidate_id INT,
skill_id INT,
level VARCHAR(50),
is_active BOOLEAN NOT NULL,
FOREIGN KEY (skill_id) REFERENCES skill(id),
FOREIGN KEY (candidate_id) REFERENCES candidate(id)
);

CREATE TABLE IF NOT EXISTS job_interview_status (
id SERIAL PRIMARY KEY,
name VARCHAR(20),
description VARCHAR(100),
is_active BOOLEAN NOT NULL);

CREATE TABLE IF NOT EXISTS job_interview_type (
id SERIAL PRIMARY KEY,
name VARCHAR(20),
is_active BOOLEAN NOT NULL);

CREATE TABLE IF NOT EXISTS company_role (
id SERIAL PRIMARY KEY,
name VARCHAR(30),
is_active BOOLEAN NOT NULL);

CREATE TABLE IF NOT EXISTS employee (
id SERIAL PRIMARY KEY,
fiscal_code VARCHAR(16) NOT NULL UNIQUE,
name VARCHAR(50),
surname VARCHAR(50),
birth_date DATE NOT NULL,
city_of_birth VARCHAR(50),
country_of_birth VARCHAR(50),
city_of_residence VARCHAR(50),
street_of_residence VARCHAR(50),
region_of_residence VARCHAR(50),
country_of_residence VARCHAR(50),
email VARCHAR(150) NOT NULL UNIQUE,
phone_number VARCHAR(20) UNIQUE,
gender VARCHAR(1),
expertise_id INT,
company_id INT,
company_role_id INT,
is_active BOOLEAN NOT NULL,
FOREIGN KEY (expertise_id) REFERENCES expertise(id),
FOREIGN KEY (company_id) REFERENCES company(id),
FOREIGN KEY (company_role_id) REFERENCES company_role(id));

CREATE TABLE IF NOT EXISTS job_interview (
id SERIAL PRIMARY KEY,
date DATE NOT NULL,
hour TIME NOT NULL,
place VARCHAR(20),
candidate_id INT,
employee_id INT,
job_interview_type_id INT,
job_position_id INT,
rating INT,
note VARCHAR(250),
job_interview_status_id INT,
is_active BOOLEAN NOT NULL,
FOREIGN KEY (candidate_id) REFERENCES candidate(id),
FOREIGN KEY (employee_id) REFERENCES employee(id),
FOREIGN KEY (job_interview_type_id) REFERENCES job_interview_type(id),
FOREIGN KEY (job_position_id) REFERENCES job_position(id),
FOREIGN KEY (job_interview_status_id) REFERENCES job_interview_status(id));

--Formattazione delle date su Employee

ALTER TABLE employee
ADD COLUMN birth_date_formatted VARCHAR(10);

UPDATE employee
SET birth_date_formatted = to_char(birth_date, 'YYYY-MM-DD');

ALTER TABLE employee
DROP COLUMN birth_date;

ALTER TABLE employee
RENAME COLUMN birth_date_formatted TO birth_date;

ALTER TABLE employee
ALTER COLUMN birth_date TYPE DATE USING birth_date::DATE;

--Formattazione delle date su Candidate

ALTER TABLE candidate
ADD COLUMN birth_date_formatted VARCHAR(10);

UPDATE candidate
SET birth_date_formatted = to_char(birth_date, 'YYYY-MM-DD');

ALTER TABLE candidate
DROP COLUMN birth_date;

ALTER TABLE candidate
RENAME COLUMN birth_date_formatted TO birth_date;

ALTER TABLE candidate
ALTER COLUMN birth_date TYPE DATE USING birth_date::DATE;

--Formattazione date su JobInterview

ALTER TABLE job_interview
ADD COLUMN date_formatted VARCHAR(10);

UPDATE job_interview
SET date_formatted = to_char(date, 'YYYY-MM-DD');

ALTER TABLE job_interview
DROP COLUMN date;

ALTER TABLE job_interview
RENAME COLUMN date_formatted TO date;

ALTER TABLE job_interview
ALTER COLUMN date TYPE DATE USING date::DATE;

ALTER TABLE job_interview_status
ADD COLUMN sequence INT;

CREATE TABLE IF NOT EXISTS account(
id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL,
surname VARCHAR(50) NOT NULL,
email VARCHAR(150) NOT NULL,
username VARCHAR(150) NOT NULL,
password VARCHAR(12) NOT NULL,
is_active BOOLEAN NOT NULL);

CREATE TABLE IF NOT EXISTS access_token(
id SERIAL PRIMARY KEY,
value VARCHAR(255) NOT NULL,
is_active BOOLEAN NOT NULL,
account_id INT,
FOREIGN KEY(account_id) REFERENCES account(id));

ALTER TABLE account ADD CONSTRAINT email_unique UNIQUE (email);
ALTER TABLE account ALTER COLUMN password TYPE VARCHAR(32);
ALTER TABLE access_token ADD COLUMN token_creation_date_time TIMESTAMP;

CREATE TABLE IF NOT EXISTS role(
id SERIAL PRIMARY KEY,
name VARCHAR(5) NOT NULL,
description VARCHAR(50) NOT NULL,
is_active BOOLEAN NOT NULL);

ALTER TABLE account ADD COLUMN role_id INTEGER REFERENCES role(id);