
--Script di inserimento
--Job_position_status
INSERT INTO job_position_status (name, is_active)
VALUES ('Aperta', true),
('Chiusa', true),
('In corso', true);

-- Inserimento dati nella tabella expertise
INSERT INTO expertise (name, is_active)
VALUES ('Junior', true),
('Mid-level', true),
('Senior', true);

-- Inserimento dati nella tabella job_interview_status
INSERT INTO job_interview_status (name, description, is_active)
VALUES ('Pianificato', 'Il colloquio è stato pianificato ma non è ancora stato effettuato', true),
('In corso', 'Il colloquio è in corso', true),
('Completato', 'Il colloquio è stato completato', true),
('Annullato', 'Il colloquio è stato annullato', true);

-- Inserimento dati nella tabella job_interview_type
INSERT INTO job_interview_type (name, is_active)
VALUES ('Telefonical',true),
('In Office',true),
('Online',true),
('Tecnical',true),
('Contract proposal',true);

-- Inserimento dati nella tabella company_role
INSERT INTO company_role (name, is_active)
VALUES ('CEO', true),
('Manager', true),
('Sviluppatore front-end', true),
('Sviluppatore back-end', true),
('Software enginer', true),
('Project Manager', true),
('Amministratore Delegato', true),
('HR',true),
('DevOps Engineer',true),
('Data Engineer', true);

-- Inserimento di una nuova azienda
INSERT INTO company (name, coo_id, is_active) VALUES ('Proactivity', 1, true);

INSERT INTO company (name, coo_id, is_active) VALUES ('Bitrock', 6, true);

INSERT INTO company (name, coo_id, is_active) VALUES ('Radicalbit', 9, true);

INSERT INTO company (name, coo_id, is_active) VALUES ('Fortitude', 10, true);



--Inserimento di 4 employee tutti ceo

INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('ABCDEF01G23H456I', 'Mario', 'Rossi', '1980-01-01', 'Roma', 'Italia', 'Milano', 'Via Roma 123', 'Lombardia', 'Italia', 'mario.rossi@email.com',
 '1234567890', 'male', 3, 1, 1, true);

INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('NHGTRD56G87J876F', 'Laura', 'Biagi', '1999-01-01', 'Roma', 'Italia', 'Genova', 'Via Roma 123', 'Liguria', 'Italia', 'laura.biagi@email.com',
 '9873333399', 'female', 2, 2, 1, true);

INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('NHGTRk56G87J876F', 'Alessio', 'Massaro', '1979-10-22', 'Taranto', 'Italia', 'Aosta', 'Via Roma 123', 'Valle D''aosta', 'Italia', 'alessio.massaro@email.com',
 '0981091098', 'male', 3, 2, 1, true);

INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('IJKUHG67J99Y111T', 'Ludovico', 'Brignani', '1985-08-12', 'Siracusa', 'Italia', 'Siracusa', 'Via Roma 123', 'Sicilia', 'Italia', 'ludovico.brignani@email.com',
 '2229987654', 'male', 3, 3, 1, true);

INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('WERFVG54T78G123U', 'Barbara', 'Nova', '1999-02-15', 'Venezia', 'Italia', 'Venezia', 'Via venezia 12', 'Veneto', 'Italia', 'barbara.nova@email.com',
 '1119990087', 'female', 1, 4, 1, true);

INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('FGHIJK01L23M456N', 'Giulia', 'Verdi', '1990-05-15', 'Napoli', 'Italia', 'Roma', 'Via Nazionale 50', 'Lazio', 'Italia', 'giulia.verdi@email.com',
'0987654321', 'female', 3, 1, 2, true);

INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('YUIOPL78I09L777J', 'Giulia', 'Verdis', '1991-05-15', 'Napoli', 'Italia', 'Torino', 'Via Nazionale 50', 'Piemonte', 'Italia', 'giulia.verdis@email.com',
'1762839029', 'female', 3, 5, 2, true);

INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('DNANUH99K09L888D', 'Danilo', 'Fraglica', '1990-06-13', 'Gela', 'Italia', 'Gela', 'Via gela 5', 'sicilia', 'Italia', 'danilo.fraglica@email.com',
'6765656543', 'male', 2, 6, 2, true);

INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('OPQRST01U23V456W', 'Marco', 'Bianchi', '1995-10-21', 'Firenze', 'Italia', 'Torino', 'Via Garibaldi 10', 'Piemonte', 'Italia', 'marco.bianchi@email.com',
'3334445555', 'male', 3, 1, 3, true);


INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('HYTRFR09Y23H234K', 'Michele', 'Mazza', '1989-12-11', 'Milano', 'Italia', 'Milano', 'Via Garibaldi 10', 'Lombardia', 'Italia', 'michele.mazza@email.com',
'7384983762', 'male', 3, 1, 4, true);

INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('BRUNGG76H78J765R', 'Bruno', 'Macheda', '1980-03-03', 'Milano', 'Italia', 'Milano', 'Via Garibaldi 20', 'Lombardia', 'Italia', 'brino.macheda@email.com',
'0909098765', 'male', 3, 7, 4, true);

INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('POLMGE12C22N775M', 'Monica', 'Zoffa', '1992-10-11', 'Milano', 'Italia', 'Milano', 'Via bastioni 101', 'Lombardia', 'Italia', 'monica.zoffa@email.com',
'1234123412', 'female', 3, 4, 4, true);


INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('idhfjk98d83n739r', 'Roberto', 'Sasso', '1997-02-20', 'Bologna', 'Italia', 'Bologna', 'Via Garibaldi 10', 'Emilia Romagna', 'Italia', 'roberto.sasso@email.com',
 '9873649039', 'male', 3, 8, 4, true);


INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('UJHGTR65G43H543R', 'Vanessa', 'Valoti', '1980-11-16', 'Palermo', 'Italia', 'Palermo', 'Via Giovanni Falcone 43', 'Sicilia', 'Italia',
'vanessa.valoti@email.com', '8739989865', 'female', 2, 8, 3, true);


INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('TRESDF65K09L876U', 'Paolo', 'Tressoldi', '1987-07-19', 'Palermo', 'Italia', 'Palermo', 'Via Dei Calzoni 11', 'Sicilia', 'Italia', 'paolo.tressoldi@email.com',
'1234567777', 'male', 3, 8, 2, true);


INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('OLPIUY76J89L345I', 'Alessio', 'Cassarino', '1997-12-20', 'Gela', 'Italia', 'Gela', 'Via Recanati 11', 'Sicilia', 'Italia', 'alessio.cassarino@email.com',
'7764890938', 'male', 3, 4, 2, true);

INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('DNALGN56Y54L989O', 'Daniele', 'Gennaro', '1993-12-20', 'Gela', 'Italia', 'Gela', 'Via Torino 1', 'Sicilia', 'Italia', 'daniele.gennaro@email.com',
'3398765439', 'male', 3, 5, 2, true);


INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('LLLOOO87Y54D123H', 'Luigi', 'Spadini', '1994-07-02', 'Gela', 'Italia', 'Gela', 'Via Bisceglie 1', 'Sicilia', 'Italia', 'luigi.spadini@email.com',
'2237658977', 'male', 2, 9, 1, true);


INSERT INTO employee (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence,
country_of_residence, email, phone_number, gender, expertise_id, company_role_id, company_id, is_active)
VALUES ('HUYTJK99L99I222R', 'Roberto', 'Pinsoglio', '1990-11-10', 'Gela', 'Italia', 'Gela', 'Via Bergomi 19', 'Sicilia', 'Italia', 'roberto.pinsoglio@email.com',
'0987263749', 'male', 3, 10, 2, true);

-- Inserimento dati nella tabella skill
INSERT INTO skill (name, is_active)
VALUES ('Java', true),--1
('Python', true),--2
('SQL', true),--3
('C++', true),--4
('JavaScript', true), --5
('HTML', true),--6
('CSS', true),--7
('Angular', true), --8
('React', true),--9
('Vue', true),--10
('Node.js', true),--11
('PHP', true),--12
('Spring', true), --13
('Hibernate', true), --14
('MySql', true),--15
('AWS', true),--16
('Doker', true);--17

--Inserimento di varie job position


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

--Inserimento tabella skill_list
INSERT INTO skill_level(skill_id, job_position_id, is_active, "level")
VALUES(1,1,true, 'ADVANCED'),
(2,1,true, 'BASIC'),
(13,1,true, 'INTERMEDIATE'),
(14,1,true, 'ADVANCED'),
(15,1,true, 'BASIC'),
(1,2,true, 'INTERMEDIATE'),
(8,2,true, 'ADVANCED'),
(9,2,true, 'BASIC'),
(4,3,true, 'ADVANCED'),
(2,3,true, 'INTERMEDIATE'),
(9,3,true, 'INTERMEDIATE'),
(16,3,true, 'ADVANCED'),
(17,3,true, 'BASIC'),
(1,4,true, 'INTERMEDIATE'),
(2,4,true, 'ADVANCED'),
(5,4,true, 'BASIC'),
(3,4,true, 'INTERMEDIATE'),
(9,5,true, 'ADVANCED'),
(10,5,true, 'BASIC'),
(12,5,true, 'INTERMEDIATE'),
(15,5,true, 'ADVANCED'),
(8,6,true, 'BASIC'),
(5,6,true, 'INTERMEDIATE'),
(9,6,true, 'ADVANCED'),
(10,6,true, 'BASIC'),
(1,7,true, 'INTERMEDIATE'),
(14,7,true, 'BASIC'),
(13,7,true, 'ADVANCED');

--Inserimento di vari candidati

INSERT INTO candidate (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence, country_of_residence, email, phone_number, gender, expertise_id, is_active)
VALUES ('BCDEFG02HIJKLMN2', 'Giulia', 'Verdi', '1992-05-15', 'Milano', 'Italia', 'Milano', 'Via Manzoni, 12', 'Lombardia', 'Italia', 'giulia.verdi@gmail.com', '+39 3456789123', 'female', 2, true);

INSERT INTO candidate (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence, country_of_residence, email, phone_number, gender, expertise_id, is_active)
VALUES ('DEFGHI04JKLMN4', 'Alessia', 'Bianchi', '1994-12-31', 'Firenze', 'Italia', 'Firenze', 'Via dei Tornabuoni, 50', 'Toscana', 'Italia', 'alessia.bianchi@gmail.com', '+39 3331234567', 'female', 1, true);

INSERT INTO candidate (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence, country_of_residence, email, phone_number, gender, expertise_id, is_active)
VALUES ('CDEFGH03IJKLMN3', 'Vincenzo', 'Franchi', '1995-01-01', 'Napoli', 'Italia', 'Napoli', 'Via Toledo, 100', 'Campania', 'Italia', 'mario.rossi2@gmail.com', '+39 4675894022', 'male', 3, true);

INSERT INTO candidate (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence, country_of_residence, email, phone_number, gender, expertise_id, is_active)
VALUES ('ABCDEF01GHIJKLN1', 'Mario', 'Rossi', '1990-01-01', 'Roma', 'Italia', 'Roma', 'Via del Corso, 123', 'Lazio', 'Italia', 'mario.rossi@gmail.com', '+39 64773892836', 'male', 1, true);

INSERT INTO candidate (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence, country_of_residence, email, phone_number, gender, expertise_id, is_active)
VALUES ('RSSMRA90E41A001A', 'Samanta', 'Rossini', '1990-08-12', 'Bologna', 'Italia', 'Milano', 'Via Manzoni', 'Lombardia', 'Italia', 'samanta.rossini@gmail.com', '+39 3321234567', 'female', 2, true);

INSERT INTO candidate (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence, country_of_residence, email, phone_number, gender, expertise_id, is_active)
VALUES ('BRTGNN91H54G333X', 'Hassan', 'Bertogna', '1991-11-23', 'Napoli', 'Italia', 'Roma', 'Via Appia Nuova', 'Lazio', 'Italia', 'hassan.bertogna@gmail.com', '+39 3479876543', 'male', 3, true);

INSERT INTO candidate (fiscal_code, name, surname, birth_date, city_of_birth, country_of_birth, city_of_residence, street_of_residence, region_of_residence, country_of_residence, email, phone_number, gender, expertise_id, is_active)
VALUES ('LCNCST92P47H501Z', 'Paola', 'Liconasti', '1992-05-04', 'Palermo', 'Italia', 'Firenze', 'Via  Tornabuoni', 'Toscana', 'Italia', 'paola.liconasti@gmail.com', '+39 3204567890', 'female', 1, true);

INSERT INTO job_interview(date, hour, place, candidate_id, employee_id, job_position_id, rating, note, job_interview_status_id, job_interview_type_id, is_active)
VALUES ('2023-03-01', '10:00:00', 'Milano', 1, 15, 3, 4, 'Ottimo candidato', 1, 1, true);

INSERT INTO job_interview(date, hour, place, candidate_id, employee_id, job_position_id, rating, note, job_interview_status_id, job_interview_type_id, is_active)
VALUES ('2023-03-05', '9:00:00', 'Firenze', 2, 13, 2, 3, 'Non ha le competenze richieste', 3, 2, true);

INSERT INTO job_interview(date, hour, place, candidate_id, employee_id, job_position_id, rating, note, job_interview_status_id, job_interview_type_id, is_active)
VALUES ('2023-03-04', '16:30:00', 'Napoli', 4, 14, 1, 5, 'Eccellente esperienza', 1, 3, true);

INSERT INTO job_interview(date, hour, place, candidate_id, employee_id, job_position_id, rating, note, job_interview_status_id, job_interview_type_id, is_active)
VALUES ('2023-03-02', '11:30:00', 'Roma', 3, 15, 4, 3, 'Non adatto alla posizione', 3, 2, true);

INSERT INTO job_interview(date, hour, place, candidate_id, employee_id, job_position_id, rating, note, job_interview_status_id, job_interview_type_id, is_active)
VALUES ('2023-03-03', '14:00:00', 'Torino', 5, 15, 6, 2, 'Buone capacità comunicative', 1, 3, true);

INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (1, 2, true, 'BASIC');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (1, 4, false, 'INTERMEDIATE');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (1, 17, true, 'ADVANCED');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (1, 16, true, 'ADVANCED');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (4, 1, true, 'BASIC');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (4, 13, false, 'ADVANCED');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (4, 14, true, 'INTERMEDIATE');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (4, 15, false, 'ADVANCED');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (4, 2, true, 'BASIC');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (5, 5, true, 'INTERMEDIATE');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (5, 8, true, 'ADVANCED');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (5, 9, true, 'BASIC');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (5, 10, true, 'ADVANCED');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (2, 7, true, 'INTERMEDIATE');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (2, 17, true, 'BASIC');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (2, 11, true, 'ADVANCED');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (3, 1, true, 'INTERMEDIATE');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (6, 7, true, 'ADVANCED');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (6, 5, true, 'BASIC');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (6, 4, true, 'INTERMEDIATE');
INSERT INTO curriculum (candidate_id, skill_id, is_active, level) VALUES (6, 3, true, 'ADVANCED');


