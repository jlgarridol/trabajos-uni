/*
Autor: José Miguel Ramírez Sanz
Autor: José Luis Garrido Labrador
Fecha: 16/09/2016
Descripción: Trabajo con las restricciones
*/
DROP TABLE IF EXISTS Oficinas;

--Quita las marcas de comentario /* */ en aquel create table con el que quieras experimentar
--Otra posibilidad es que selecciones le create table que quieras probar, y des al boton de ejecutar

CREATE TABLE Oficinas (	
	Cod		INTEGER,
	CONSTRAINT PK_Cod PRIMARY KEY (Cod),
	ciudad		CHAR(15) DEFAULT 'BURGOS',
	objetivo	NUMERIC( 5, 2) DEFAULT 0.0 CONSTRAINT NN_objetivo NOT NULL -- El NOT NULL  es especial no tiene que tener coma e ir en la misma linea y por lo cual no hace falta poner de nuevo objetivo (la variable) entre paréntesis
);


DROP TABLE IF EXISTS Empleados;
CREATE TABLE Empleados (
	nombre		char(20),
	CONSTRAINT PK_nombre PRIMARY KEY (nombre),
	estado		char(1),
	salario		numeric( 4, 2),
	CONSTRAINT CHK_salario CHECK (salario > 0),
	fecha		date CONSTRAINT NN_fecha NOT NULL
);


DROP TABLE IF EXISTS Clientes;
CREATE TABLE Clientes (
	codcli		integer,
	CONSTRAINT PK_codcli PRIMARY KEY (codcli),
	NIF		char(8) CONSTRAINT NN_NIF NOT NULL CONSTRAINT UNQ_NIF UNIQUE, 
	razon		char(25) CONSTRAINT NN_razon NOT NULL CONSTRAINT UNQ_razon UNIQUE,
	ciudad		char(13) CONSTRAINT NN_ciudad NOT NULL,
	telefono	char(9)
);
INSERT INTO Clientes (codcli,NIF,razon,ciudad,telefono) VALUES (1,'12345678','fdddfdffdfd','sdfgfdf','12345678');	--Prueba dando valores a una de las tablas
SELECT * FROM Clientes;		-- Imprimos una tabla (Clientes) como ejemplo

	
	