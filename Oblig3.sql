-- SQL for en-til-mange-eksemplet gjennomgått i timen tirsdag 26. mars 2019 

DROP SCHEMA IF EXISTS Oblig3 CASCADE;
CREATE SCHEMA Oblig3;
SET search_path TO Oblig3;
    
CREATE TABLE Ansatt
(
	AnsattID SERIAL,
	Brukernavn VARCHAR(4),
	Fornavn VARCHAR(50),
	Etternavn VARCHAR(50),
	Ansettelsedato DATE,
	Stilling VARCHAR(100),
	Mandeslonn NUMERIC, 
	AvdelingID INTEGER NOT NULL,
	CONSTRAINT AnsattPK PRIMARY KEY (AnsattID),
	CONSTRAINT AnsattUnik UNIQUE (AnsattID, Brukernavn)
);

CREATE TABLE Avdeling
(
	AvdelingID SERIAL, 
	Navn VARCHAR(50),
	SjefID INTEGER NOT NULL,
	CONSTRAINT AvdelingPK PRIMARY KEY (AvdelingID),
	CONSTRAINT AnsattFK FOREIGN KEY (SjefID) REFERENCES Ansatt(AnsattID),
	CONSTRAINT AvdelingUnik UNIQUE (AvdelingID)
);

CREATE TABLE Prosjekt
(
	ProsjektID SERIAL,
	Navn VARCHAR(225),
	Beskrivelse VARCHAR(225),
	CONSTRAINT ProsjektPK PRIMARY KEY (ProsjektID),
	CONSTRAINT ProsjektUnik UNIQUE (ProsjektID)
);

CREATE TABLE AnsattProsjekt (
	AnsattProsjektID SERIAL, 
	AnsattID INTEGER,
	ProsjektID INTEGER, 
	Timer INTEGER,
	Rolle VARCHAR(55),
	CONSTRAINT AnsattProsjektPK PRIMARY KEY (AnsattProsjektID),
	CONSTRAINT AnsattProsjektFK FOREIGN KEY (AnsattID) REFERENCES Ansatt(AnsattID),
	CONSTRAINT AnsattProsjekt2FK FOREIGN KEY (ProsjektID) REFERENCES Prosjekt(ProsjektID)
);	

INSERT INTO Ansatt(Brukernavn, Fornavn, Etternavn, Ansettelsedato, Stilling, Mandeslonn, AvdelingID)
VALUES
 	('vkf', 'Vilde', 'Fossum', '2017-08-14', 'Leder', 50000, 1),
    ('lfg', 'Linnea', 'Gustavsen', '2017-08-14', 'Leder', 60000, 2),
    ('gtf', 'Geir', 'Fossum', '2017-09-14', 'Slave', 50000, 3),
    ('btf', 'Brit', 'Fossum', '2017-04-14', 'Assistent', 50000, 1),
    ('ekj', 'Emil', 'Johansen', '2018-02-14', 'Hund', 50000, 2),
    ('ssg', 'Sissel', 'Gustavsen', '2017-08-14', 'Salgsmedarbeider', 50000, 3);
    
INSERT INTO Avdeling(Navn, SjefID)
VALUES
	('Produksjon', 1),
	('Markedsføring',2),
	('Salg',3);
	
INSERT INTO Prosjekt(Navn, Beskrivelse)
VALUES
	('Prosjekt1', 'Et nytt og inovativt prosjekt.');

INSERT INTO AnsattProsjekt(AnsattID, ProsjektID, Timer, Rolle)
VALUES
	(1, 1, 5, 'Leder');


ALTER TABLE Ansatt ADD CONSTRAINT AvdelingFK 
FOREIGN KEY (AvdelingID) REFERENCES Avdeling(AvdelingID);


