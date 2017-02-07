/*
 * Autores: Luis Miguel Cabrejas - José Luis Garrido Labrador - José Miguel Ramírez Sanz
 * SGBD: Postgresql
 * Plataforma: Linux 4.7.4 - JKArch 
 * Fecha: 21/09/2016
*/


DROP TABLE IF EXISTS Polizas; --Eliminamos la tabla si existía
CREATE TABLE Polizas(
	Matricula	varchar(7) CONSTRAINT NN_Matricula NOT NULL CONSTRAINT UNQ_Matricula UNIQUE 
				CONSTRAINT CHK_Matricula CHECK (Matricula SIMILAR TO '[0-9][0-9][0-9][0-9][A-Z][A-Z][A-Z]'),
	Bastidor	varchar(17) CONSTRAINT UNQ_Bastidor UNIQUE,
	Fecha		date CONSTRAINT NN_Fecha NOT NULL 
				CONSTRAINT CHK_Fecha CHECK(fecha>='01-01-2000' AND fecha <= CURRENT_DATE) 
				DEFAULT CURRENT_DATE,
	Precio		decimal(7,2) CONSTRAINT CHK_Precio CHECK(Precio>=0),
	No_Pol		integer CONSTRAINT PK_No_Pol PRIMARY KEY,
	Tipo_Veh	char(1) CONSTRAINT CHK_Tipo_Veh CHECK (Tipo_Veh = 'T' OR Tipo_Veh = 'M' OR Tipo_Veh = 'F' OR Tipo_Veh = 'C')
				CONSTRAINT NN_Tipo_Veh NOT NULL DEFAULT 'T',
	Tomador		varchar(40) CONSTRAINT NN_Tomador NOT NULL --Consideramos 40 ya que el valor es libre
);
