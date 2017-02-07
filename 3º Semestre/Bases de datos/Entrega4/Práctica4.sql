/*
 * Autores: Luis Miguel Cabrejas - José Luis Garrido Labrador - José Miguel Ramírez Sanz
 * SGBD: Postgresql
 * Plataforma: Linux 4.8.6 - JKArch 
 * Fecha: 10/11/2016
 */

DROP TABLE IF EXISTS lineas_de_facturas;
DROP TABLE IF EXISTS facturas;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS formas_de_pago;
DROP TABLE IF EXISTS ciudad;
DROP TABLE IF EXISTS provincias;
DROP TABLE IF EXISTS tipo_de_clientes;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS color;

CREATE TABLE color(
	R	INTEGER			CONSTRAINT CHK_R CHECK(R>=0 AND R<=255) CONSTRAINT NN_R NOT NULL,
	G	INTEGER			CONSTRAINT CHK_G CHECK(G>=0 AND G<=255) CONSTRAINT NN_G NOT NULL,
	B	INTEGER			CONSTRAINT CHK_B CHECK(B>=0 AND B<=255) CONSTRAINT NN_B NOT NULL,
	CONSTRAINT UNQ_RGB UNIQUE (R,G,B),
	nombre		VARCHAR(15)	CONSTRAINT PK_nombre PRIMARY KEY
);

CREATE TABLE productos(
	familia		VARCHAR(15),
	color		VARCHAR(15) CONSTRAINT FK_color REFERENCES color ON DELETE CASCADE ON UPDATE CASCADE,
	familia_ref	INTEGER,
	CONSTRAINT PK_producto PRIMARY KEY (familia,color,familia_ref),
	existencias	INTEGER		CONSTRAINT CHK_existencias CHECK(existencias>=0)
);

CREATE TABLE tipo_de_clientes(
	acronimo	CHAR(3)		CONSTRAINT PK_acronimo PRIMARY KEY,
	descripcion 	VARCHAR(40)
);

CREATE TABLE provincias(
	acronimo	VARCHAR(5)	CONSTRAINT PK_acronimo_PROV PRIMARY KEY CONSTRAINT CHK_acronimo CHECK(upper(acronimo)=acronimo),
	nombre		VARCHAR(20)	CONSTRAINT NN_nombre NOT NULL
);

CREATE TABLE ciudad(
	nombre		VARCHAR(15),
	prov 		VARCHAR(5) 	CONSTRAINT RF_prov REFERENCES provincias ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_ciudad PRIMARY KEY (nombre,prov)
);

CREATE TABLE formas_de_pago(
	acronimo 	VARCHAR(10) 	CONSTRAINT PK_acronimo_pago PRIMARY KEY,
	descripcion 	VARCHAR(40)
);

CREATE TABLE clientes(
	CIF		VARCHAR(8)	CONSTRAINT PK_CIF PRIMARY KEY,
	nombre		VARCHAR(40)	CONSTRAINT NN_nombre NOT NULL,
	direccion	VARCHAR(30)	CONSTRAINT NN_direccion NOT NULL,
	telefono	NUMERIC(9) 	CONSTRAINT UNQ_tlf UNIQUE CONSTRAINT NN_tlf NOT NULL,
	cuenta_bancaria	VARCHAR(25)	CONSTRAINT UNQ_bancaria UNIQUE CONSTRAINT NN_bancaria NOT NULL,
	fax		NUMERIC(9) 	CONSTRAINT UNQ_fax UNIQUE,
	email		VARCHAR(30) 	CONSTRAINT UNQ_email UNIQUE,
	forma_pago	VARCHAR(10) 	CONSTRAINT RF_pago REFERENCES formas_de_pago ON UPDATE CASCADE ON DELETE CASCADE CONSTRAINT NN_pago NOT NULL,
	ciudad		VARCHAR(15) 	CONSTRAINT NN_ciudad NOT NULL,
	acronimo_prov	VARCHAR(5)	CONSTRAINT NN_acronimo NOT NULL,
	CONSTRAINT RF_ciudad FOREIGN KEY (ciudad,acronimo_prov) REFERENCES ciudad ON UPDATE CASCADE ON DELETE CASCADE,
	tipo_cliente	CHAR(3)		CONSTRAINT NN_cliente NOT NULL CONSTRAINT RF_cliente REFERENCES tipo_de_clientes ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE facturas(
	cod_factura	INTEGER		CONSTRAINT PK_cod_factura PRIMARY KEY,
	cliente		VARCHAR(8)	CONSTRAINT NN_cliente NOT NULL CONSTRAINT RF_cliente REFERENCES clientes ON UPDATE CASCADE ON DELETE CASCADE,
	forma_pago	VARCHAR(10) 	CONSTRAINT RF_pago REFERENCES formas_de_pago ON UPDATE CASCADE ON DELETE CASCADE CONSTRAINT NN_pago NOT NULL,
	fecha		DATE		CONSTRAINT CHK_fecha CHECK (fecha<= CURRENT_DATE) CONSTRAINT NN_fecha NOT NULL
);

CREATE TABLE lineas_de_facturas(
	id_linea	INTEGER,
	factura		INTEGER		CONSTRAINT RF_factura REFERENCES facturas ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_lineas PRIMARY KEY (id_linea,factura),
	familia_prod	VARCHAR(15)	CONSTRAINT NN_familia NOT NULL,
	color_prod	VARCHAR(15) 	CONSTRAINT NN_color NOT NULL,
	familia_ref	INTEGER 	CONSTRAINT NN_familia_ref NOT NULL,
	CONSTRAINT RF_producto FOREIGN KEY (familia_prod,color_prod,familia_ref) REFERENCES productos ON UPDATE CASCADE ON DELETE CASCADE,
	cantidad	INTEGER		CONSTRAINT CHK_cantidad CHECK(cantidad>0),
	precio		NUMERIC(6,2)	CONSTRAINT CHK_precio CHECK(precio>=0)
);

/* Insertamos valores para probar el borrado */
INSERT INTO color VALUES (255,0,0,'rojo');
INSERT INTO productos VALUES ('cama','rojo',7,85);
INSERT INTO provincias VALUES ('BU','Burgos');
INSERT INTO ciudad VALUES ('Burgos','BU');
INSERT INTO formas_de_pago VALUES ('CONTA','Al contado');
INSERT INTO tipo_de_clientes VALUES('MUL','Multinacional');
INSERT INTO clientes VALUES('CA1','Major League of Gaming','C\ Thunderbird 25 25',123456789,'0000011111222223333344444',123456790,'mlg@mlg.mlg','CONTA','Burgos','BU','MUL');
INSERT INTO facturas VALUES(1,'CA1','CONTA',CURRENT_DATE);
INSERT INTO lineas_de_facturas VALUES(1,1,'cama','rojo',7,84,420);

DELETE FROM clientes WHERE CIF='CA1'; --Borramos por DELETE CASCADE productos, facturas y lineas_de_facturas
--Comprobamos ahora si clientes y productos existen
SELECT * FROM facturas;

/* Insertamos los valores borrados para probar las actualizaciones  */
INSERT INTO clientes VALUES('CA1','Major League of Gaming','C\ Thunderbird 25 25',123456789,'0000011111222223333344444',123456790,'mlg@mlg.mlg','CONTA','Burgos','BU','MUL');
INSERT INTO facturas VALUES(1,'CA1','CONTA',CURRENT_DATE);
INSERT INTO lineas_de_facturas VALUES(1,1,'cama','rojo',7,84,420);

UPDATE color SET R=0,G=0,B=255,nombre='azul' WHERE nombre='rojo'; --Cambiamos el color rojo por azul
SELECT * FROM productos; --Comprobamos si se actualizó productos
UPDATE provincias SET acronimo='M', nombre='Madrid' WHERE acronimo='BU'; --Cambiamos Burgos por Madrid
UPDATE ciudad SET nombre='Madrid' WHERE prov='M'; --cambiamos en ciudad de Burgos a Madrid tras la actualización de la provincia

SELECT * FROM ciudad; --Vemos el cambio en la ciudad

UPDATE tipo_de_clientes SET acronimo='ENG', descripcion='Englomerado' WHERE acronimo='MUL'; --Cambiamos de Multinacional a Englomerado
UPDATE formas_de_pago SET acronimo='TB', descripcion='Transferencia bancaria' WHERE acronimo='CONTA'; --Convertimos Al contado en trasnferencia bancaria
SELECT * FROM clientes; --Vemos como clientes ha cambiado su provincia, ciudad tipo de cliente y forma de pago

/*Introducimos valores para comprobar que se añaden bien*/
INSERT INTO clientes VALUES('CA2','JKA Network','c\ Spam',645645645,'1234512345123451234512345',647647647,'contacto@jkanetwork.com','TB','Madrid','M','ENG');
INSERT INTO productos VALUES ('armario','azul',8,25);
INSERT INTO facturas VALUES(2,'CA2','TB',CURRENT_DATE);
INSERT INTO lineas_de_facturas VALUES(1,2,'armario','azul',8,25,123);


/*1.*//*INSERT INTO productos VALUES ('armario','rojo',8,40);
2. No se puede borrar algo que rompa con la integridad referencial porque la tabla se ha creado con ON DELETE CASCADE
3.  No se puede borrar algo que rompa con la integridad referencial porque la tabla se ha creado con ON UPDATE CASCADE*/

