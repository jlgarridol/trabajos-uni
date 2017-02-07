/*
 * Autores: Luis Miguel Cabrejas - José Luis Garrido Labrador - José Miguel Ramírez Sanz
 * SGBD: Postgresql
 * Plataforma: Linux 4.8.6 - JKArch 
 * Fecha: 10/11/2016
 */
 
DROP TABLE IF EXISTS lineas_de_facturas;
DROP TABLE IF EXISTS pedidos;
DROP TABLE IF EXISTS facturas;
DROP TABLE IF EXISTS contactos;
DROP TABLE IF EXISTS clientesYTipos;
DROP TABLE IF EXISTS clientesYFormasDePago;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS formas_de_pago;
DROP TABLE IF EXISTS ciudad;
DROP TABLE IF EXISTS provincias;
DROP TABLE IF EXISTS comerciales;
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
	existencias	INTEGER		CONSTRAINT CHK_existencias CHECK(existencias>=0),
	descripcion VARCHAR(100),
	precio 		INTEGER CONSTRAINT CHK_precioProducto CHECK(precio>0)
);

CREATE TABLE tipo_de_clientes(
	acronimo	CHAR(3)		CONSTRAINT PK_acronimo PRIMARY KEY,
	descripcion 	VARCHAR(40)
);

CREATE TABLE comerciales(
	DNI	VARCHAR(9)	CONSTRAINT PK_DNI PRIMARY KEY,
	Nombre	VARCHAR(20)	CONSTRAINT NN_NombreComercial NOT NULL,
	Ape1	VARCHAR(20),
	Ape2	VARCHAR(20),
	tfno	NUMERIC(9),
	e_mail	VARCHAR(20),
	CONSTRAINT CHK_TLF_AND_MAIL_NOT_NULL_BOTH CHECK(e_mail IS NOT NULL OR tfno IS NOT NULL),
	responsable	CHAR(3)	CONSTRAINT RF_RESPONSABLE REFERENCES tipo_de_clientes
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
	formaPagoDef	VARCHAR(10) 	CONSTRAINT RF_pago REFERENCES formas_de_pago ON UPDATE CASCADE ON DELETE CASCADE CONSTRAINT NN_pago NOT NULL,
	ciudad		VARCHAR(15) 	CONSTRAINT NN_ciudad NOT NULL,
	acronimo_prov	VARCHAR(5)	CONSTRAINT NN_acronimo NOT NULL,
	CONSTRAINT RF_ciudad FOREIGN KEY (ciudad,acronimo_prov) REFERENCES ciudad ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE clientesYTipos(
	CIF		VARCHAR(8) 	CONSTRAINT FK_CIF1 REFERENCES clientes ON UPDATE CASCADE ON DELETE CASCADE,
	tipoCliente	CHAR(3)		CONSTRAINT FK_tipoCliente REFERENCES tipo_de_clientes ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_CIF_TIPO PRIMARY KEY(CIF,tipoCliente)
);

CREATE TABLE clientesYFormasDePago(
	CIF		VARCHAR(8) 	CONSTRAINT FK_CIF2 REFERENCES clientes ON UPDATE CASCADE ON DELETE CASCADE,
	formaPago	VARCHAR(10)		CONSTRAINT FK_Pago REFERENCES formas_de_pago ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_CIF_Pago PRIMARY KEY(CIF,formaPago)
);
CREATE TABLE facturas(
	cod_factura	INTEGER		CONSTRAINT PK_cod_factura PRIMARY KEY,
	forma_pago	VARCHAR(10) 	CONSTRAINT RF_pago REFERENCES formas_de_pago ON UPDATE CASCADE ON DELETE CASCADE CONSTRAINT NN_pago NOT NULL,
	fecha		DATE		CONSTRAINT CHK_fecha CHECK (fecha<= CURRENT_DATE) CONSTRAINT NN_fecha NOT NULL
);

CREATE TABLE contactos(
	IDC		INTEGER 	CONSTRAINT PK_IDC PRIMARY KEY,
	DNI		VARCHAR(9)	CONSTRAINT RF_DNI REFERENCES comerciales ON UPDATE CASCADE ON DELETE CASCADE,
	CIF		VARCHAR(8)	CONSTRAINT RF_CIF_CONT REFERENCES clientes ON UPDATE CASCADE ON DELETE CASCADE,
	fecha		DATE		CONSTRAINT CHK_FECHA CHECK(FECHA <= CURRENT_DATE)
);

CREATE TABLE pedidos(
	IDP		INTEGER 	CONSTRAINT PK_IDP PRIMARY KEY,
	tipo  		CHAR(2)		CONSTRAINT CHK_tipo CHECK (tipo='CC' OR tipo='SC'), --CC = Con contacto, SC = Sin contacto
	CIF 		VARCHAR(8) 	CONSTRAINT RF_CIF_pedidos REFERENCES clientes ON UPDATE CASCADE ON DELETE CASCADE,		
	CONSTRAINT CHK_CIF CHECK ((CIF IS NULL AND tipo <> 'SC') OR (CIF IS NOT NULL AND tipo = 'SC')),
	IDC     	INTEGER 	CONSTRAINT RF_IDC REFERENCES contactos,
	CONSTRAINT CHK_IDC CHECK ((IDC IS NULL AND tipo <> 'CC')OR(IDC IS NOT NULL AND tipo = 'CC'))
);

CREATE TABLE lineas_de_facturas(
	id_linea	INTEGER,
	pedido		INTEGER		CONSTRAINT RF_pedido REFERENCES pedidos ON UPDATE CASCADE ON DELETE CASCADE,
	factura		INTEGER		CONSTRAINT RF_factura REFERENCES facturas ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_lineas PRIMARY KEY (id_linea,pedido),
	familia_prod	VARCHAR(15)	CONSTRAINT NN_familia NOT NULL,
	color_prod	VARCHAR(15) 	CONSTRAINT NN_color NOT NULL,
	familia_ref	INTEGER 	CONSTRAINT NN_familia_ref NOT NULL,
	CONSTRAINT RF_producto FOREIGN KEY (familia_prod,color_prod,familia_ref) REFERENCES productos ON UPDATE CASCADE ON DELETE CASCADE,
	cantidad	INTEGER		CONSTRAINT CHK_cantidad CHECK(cantidad>0),
	precio		NUMERIC(6,2)	CONSTRAINT CHK_precio CHECK(precio>=0)
);

/* Insertamos valores para probar el borrado */
INSERT INTO color VALUES (255,0,0,'rojo'),
			 (0,255,0,'verde'),
			 (0,0,255,'azul');
INSERT INTO productos VALUES ('cama','rojo',7,85,'Una cama roja',40),
			     ('cama','azul',7,15,'Una cama azul',300),
			     ('silla','verde',8,11,'Una silla',10);
			     
INSERT INTO provincias VALUES ('BU','Burgos');
INSERT INTO ciudad VALUES ('Burgos','BU');
INSERT INTO formas_de_pago VALUES ('CONTA','Al contado'),
				  ('BANCA', 'Transferencia Bancaria');								  
INSERT INTO tipo_de_clientes VALUES('MED','MEDIANO'),
				   ('GRN','GRANDE'),
				   ('PEQ','PEQUEÑO');
INSERT INTO comerciales VALUES ('71301637G','Luis Miguel','Cabrejas','Arce',NULL,'lca0037@alu.ubu.es','GRN'),
			       ('71707244Y','José Luis','Garrido','Labrador',NULL,'jgl0062@alu.ubu.es','MED'),
			       ('71303106R','José Miguel','Ramírez','Sánz',NULL,'jrs0070@alu.ubu.es','PEQ');
INSERT INTO clientes VALUES('678678GA','JKA Network','c/ Null nº 0 3.3',645645645,'0000011111222223333344444',646646646,'contacto@jkanetwork.com','CONTA','Burgos','BU'),
			   ('678678BF','Tejidos Carlos','c/ Null nº 7.7',654654654,'5555566666777778888899999',655655655,'contacto@tejidoscarlos.com','BANCA','Burgos','BU');
INSERT INTO clientesYTipos VALUES('678678GA','MED'),
				 ('678678BF','GRN');
INSERT INTO clientesYFormasDePago VALUES ('678678GA','CONTA'),
					 ('678678BF','BANCA');
INSERT INTO contactos VALUES (1,'71707244Y','678678GA','25-08-2014'),
			     (2,'71707244Y','678678BF','31-10-2014'),
			     (3,'71301637G','678678BF','25-08-2014'),
			     (4,'71707244Y','678678BF','26-08-2014');
			    -- (5,'71303106R','678678BF','27-08-2014');
INSERT INTO pedidos VALUES (1,'CC',null,1),
			   (2,'SC','678678GA',null),
			   (3,'CC',null,3);
INSERT INTO facturas VALUES (1,'BANCA',CURRENT_DATE),
                            (2,'BANCA',CURRENT_DATE);
INSERT INTO lineas_de_facturas VALUES (1,1,null,'cama','azul',7,8,50),
				      (2,1,null,'cama','rojo',7,8,100),
				      (1,2,1,'cama','azul',7,8,200),
				      (1,3,2,'cama','rojo',7,50,25);

/*1ª CONSULTA*/
SELECT nombre,COUNT(IDC),COUNT(DISTINCT CIF) FROM comerciales NATURAL LEFT JOIN contactos GROUP BY nombre;

/*2ª CONSULTA*/
SELECT c.nombre,COALESCE(SUM(cantidad*precio),0) FROM comerciales c NATURAL LEFT JOIN contactos LEFT JOIN pedidos USING (IDC) LEFT JOIN lineas_de_facturas ON(IDP=pedido) GROUP BY c.nombre;

/*3ª CONSULTA*/
SELECT acronimo,COALESCE(SUM(cantidad*precio),0) FROM formas_de_pago LEFT JOIN facturas ON(acronimo=forma_pago) LEFT JOIN lineas_de_facturas ON (cod_factura=factura) GROUP BY acronimo;

/*4ª CONSULTA*/
SELECT p.familia,p.color,p.familia_ref,COALESCE(SUM(cantidad),0) FROM productos p LEFT JOIN lineas_de_facturas l ON(familia=familia_prod AND color=color_prod AND p.familia_ref=l.familia_ref) LEFT JOIN facturas ON(factura=cod_factura) GROUP BY p.familia,p.color,p.familia_ref;