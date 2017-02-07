/*
--> Autores: Luis Miguel Cabrejas - José Luis Garrido - José Miguel Ramírez
--> Fecha: 07/10/2016 - 09/10/2016
--> Versión: 1.0
--> SGDB: Postgresql
*/

DROP TABLE IF EXISTS Centros;
DROP TABLE IF EXISTS Titulaciones;
DROP TABLE IF EXISTS Departamentos;
DROP TABLE IF EXISTS Asignaturas;

CREATE TABLE Centros(
	n_centro	INTEGER CONSTRAINT PK_n_centro PRIMARY KEY,
	nom_centro	VARCHAR(40) CONSTRAINT UNQ_nom_centro UNIQUE,
	direccion	VARCHAR(50) CONSTRAINT UNQ_direccion UNIQUE
);

CREATE TABLE Titulaciones(
	n_titulaciones	INTEGER,
	nom_titulacion	VARCHAR(30) CONSTRAINT UNQ_n_titulacion UNIQUE,
	n_centro	INTEGER,
	CONSTRAINT PK_titulacion_centro PRIMARY KEY (n_titulaciones, n_centro)
);

CREATE TABLE Departamentos(
	n_departamentos		INTEGER CONSTRAINT PK_n_departamentos PRIMARY KEY,
	nom_departamentos	VARCHAR(30) CONSTRAINT UNQ_nom_departamentos UNIQUE,
	telefono_departamentos	VARCHAR(12) CONSTRAINT UNQ_telefono_departamentos UNIQUE	
);

CREATE TABLE Asignaturas(
	n_asignatura	INTEGER,
	n_departamento	INTEGER CONSTRAINT NN_n_departamento NOT NULL,
	n_centro	INTEGER,
	n_titulacion	INTEGER,
	nom_asignatura	VARCHAR(30) CONSTRAINT NN_nom_asignatura NOT NULL,
	n_creditos	INTEGER CONSTRAINT CHK_n_creditos CHECK (n_creditos > 0) CONSTRAINT NN_n_creditos NOT NULL DEFAULT 6 ,
	n_maximo	INTEGER CONSTRAINT CHK_n_maximo CHECK (n_maximo >= 0),
	n_minimo	INTEGER CONSTRAINT CHK_n_minimo CHECK (n_minimo >= 0),
	CONSTRAINT PK_asignaturas PRIMARY KEY (n_asignatura, n_titulacion, n_centro),
	CONSTRAINT CHK_asignaturas CHECK ((n_maximo IS NULL OR n_minimo IS NULL) OR ((n_maximo > n_minimo) AND (n_maximo IS NOT NULL AND n_minimo IS NOT NULL))),
	CONSTRAINT UNQ_titulaciones UNIQUE (n_titulacion,n_asignatura)
);


-- Ejercicios:
/*
Comprobaciones de los UNIQUE y PRIMARY KEY
--INSERT INTO Centros VALUES (20,'eps','calle adc'), (20, 'eps','calle adc');
INSERT INTO Titulaciones VALUES (1,'Informática','1111111111'), (1,'Informática','1111111111');
INSERT INTO Departamentos VALUES (23,'qwer','2222222222'),(23,'qwer','2222222222');
INSERT INTO Asignaturas VALUES (1,1,1,1,'qwer',1,2,1),(1,1,1,1,'qwer',1,2,1);
*/

/*
INSERT INTO Asignaturas (n_asignatura,n_departamento,n_centro,n_titulacion,nom_asignatura) VALUES (1,1,1,1,'qwer');
SELECT * FROM Asignaturas;
*/

/*
INSERT INTO Asignaturas (n_asignatura,n_departamento,n_centro,n_titulacion,nom_asignatura) VALUES (1,1,1,1,'qwer');
SELECT * FROM Asignaturas;
*/

/*
INSERT INTO Asignaturas VALUES (1,1,1,1,'qwer',-1,-1,-1); --Aqui se comprueban los 3 checks de n_creditos, n_maximo y n_minimo
INSERT INTO Asignaturas VALUES (1,1,1,1,'qwer',1,1,2);
INSERT INTO Asignaturas VALUES (1,1,1,1,'qwer',1);
*/

/*
INSERT INTO Titulaciones VALUES  (1,'qwer',1);
INSERT INTO Asignaturas VALUES (1,1,1,2,'qwer');
*/