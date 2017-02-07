/*
Autor: José Miguel Ramírez Sanz
Autor: José Luis Garrido Labrador
Fecha: 16/09/2016
Descripción: Trabajo con las restricciones
*/
DROP TABLE IF EXISTS articulo;	--Borramos si existe la tabla articulo
CREATE TABLE articulo (
	cod_Seccion integer CONSTRAINT NN_cod_Seccion NOT NULL,
	cod_Articulo integer CONSTRAINT NN_cod_Articulo NOT NULL CONSTRAINT PK_cod_Articulo PRIMARY KEY,	
	cod_Armario integer CONSTRAINT NN_cod_Armario NOT NULL,
	CONSTRAINT UNQ_Seccion_Armario UNIQUE (cod_Seccion, cod_Armario),
	existencias integer CONSTRAINT NN_existencias NOT NULL CONSTRAINT CHK_existencias CHECK(existencias >= 0),
	precio decimal(5,2) CONSTRAINT NN_precio NOT NULL,
	coste decimal (5,2) CONSTRAINT NN_coste NOT NULL,
	CONSTRAINT CHK_precio CHECK(precio <= coste)
);