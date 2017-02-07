DROP TABLE IF EXISTS Oficinas;

--Quita las marcas de comentario /* */ en aquel create table con el que quieras experimentar
--Otra posibilidad es que selecciones le create table que quieras probar, y des al boton de ejecutar

CREATE TABLE Oficinas (	
	Cod		INTEGER PRIMARY KEY NOT NULL,
	ciudad		CHAR(15) DEFAULT 'BURGOS',
	objetivo	NUMERIC( 5, 2) DEFAULT 0.0 NOT NULL
);




DROP TABLE IF EXISTS Empleados;
CREATE TABLE Empleados (
	nombre		char(20) PRIMARY KEY,
	estado		char(1),
	salario		numeric( 4, 2) CHECK(salario > 0),
	fecha		date NOT NULL
);


DROP TABLE IF EXISTS Clientes;
CREATE TABLE Clientes (
	codcli		integer PRIMARY KEY,
	NIF		char(8) NOT NULL UNIQUE,
	razon		char(25) UNIQUE	NOT NULL,
	ciudad		char(13) NOT NULL,
	telefono	char(9)
);
INSERT INTO Clientes (codcli,NIF,razon,ciudad,telefono) VALUES (1,'12345678','fdddfdffdfd','sdfgfdf','12345678');	--Prueba dando valores a una de las tablas
SELECT * FROM Clientes;		-- Imprimos una tabla (Clientes) como ejemplo

	
	