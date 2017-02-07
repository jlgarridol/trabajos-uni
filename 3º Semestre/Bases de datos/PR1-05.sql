DROP TABLE IF EXISTS factura; -- Eliminamos la tabla factura si existe
CREATE TABLE factura (
	numero	integer,	-- Creamos los campos
	fecha date,
	razon varchar(40),
	importe decimal(8,2)
);

/* INSERT INTO factura (numero, fecha, razon, importe ) VALUES ( 23, '12-08-2016' , 'porque si', 4.20); */	-- Insertamos algunos datos como ejemplo
SELECT * FROM factura;	-- Imprimimos la tabla