DROP TABLE IF EXISTS articulos;

CREATE TABLE Articulos(	
	cod_Seccion	integer not null,
	cod_Armario	integer not null,
	cod_Articulo	integer primary key,	
	precio 		numeric(5,2) not null,
	coste  		numeric(5,2) not null,
	existencias	integer check  (existencias > 0),
	constraint ck_precio_mayor_coste check (precio >= coste),	
	constraint unq_seccion_y_armario unique( cod_seccion, cod_armario)
);
--Quita las marcas de comentario /* */ en las sentencias con las que quieras experimentar
--Otra posibilidad es que selecciones la(s) sentencia(s) que quieras probar, y des al boton de ejecutar

--Caso 1
--insert into articulos values ( 1, 1, 1, 100, 50, 2, 3 ); -- No funciona hay más valores que variables 

--Caso 2
--Que valdra el codigo de seccion?
/*
insert into articulos values ( 1.5, 1, 1, 100, 50, 2 );
select * from articulos;
*/
-- Redondea hacia arriba, de 1.5 a 2

--Caso 3
--insert into articulos values ( null, 1, 1, 100, 50, 2 );
--No puede ser ya que está definido como NOT NULL

--Caso 4
/*
insert into articulos values ( 1, 1, 1, 100, 50, 2 );
insert into articulos values ( 2, 1, 1, 100, 50, 2 );
*/
-- cod_Articulo es PRIMARY KEY, no se puede repetir su valor en la columna

--Caso 5
/*
insert into articulos values ( 1, 1, 1, 100, 50, 2 );
insert into articulos values ( 1, 1, 2, 100, 50, 2 );
*/
-- No puede haber dos filas con los mismos valores ya que son unicos entre si

--Caso 6
/*
insert into articulos values ( 1, 1, 1, 100, 500, 2 );
*/
--El coste ha de ser mayor o igual que el precio

--Caso 7
--insert into articulos values ( 1, 1, 1, 100, 50, -2 );
-- Existencias no puede ser negativo

--Caso 8
-- insert into articulos (cod_armario, cod_articulo, precio, coste, existencias) values ( 1, 1, 100, 50, 2 );
-- Falta un valor que no puede ser nulo


