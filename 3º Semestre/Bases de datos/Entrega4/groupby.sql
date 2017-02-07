/*
 * Autores: Luis Miguel Cabrejas - José Luis Garrido Labrador - José Miguel Ramírez Sanz
 * SGBD: Postgresql
 * Plataforma: Linux 4.8.6 - JKArch 
 * Fecha: 10/11/2016
 */

DROP TABLE IF EXISTS prestamos CASCADE;
DROP TABLE IF EXISTS libros CASCADE;
DROP TABLE IF EXISTS socios CASCADE;

CREATE TABLE libros(
  codLibro	INTEGER PRIMARY KEY,
  titulo CHAR(20) NOT NULL
);

insert into libros values ( 1, 'Libro 1'), (2, 'Libro 2'), (3, 'Libro 3');

CREATE TABLE socios(
	nombre		CHAR(10) PRIMARY KEY,
	direccion	VARCHAR(50),
	CP		NUMERIC(5));	

insert into socios values ('Juan', 'C/X nº 1', '09001'), ('Luis', 'C/X nº 2', '09001'), ('Pepe', 'C/Y nº 3', '09002'), ('Maria', 'C/Z nº 4', '09003');

CREATE TABLE prestamos(
  codLibro INTEGER REFERENCES libros NOT NULL,
  socio    CHAR(10),
  fecha    DATE,
  PRIMARY KEY (codLibro, socio, fecha)
);

insert into prestamos values ( 1, 'Pepe', CURRENT_DATE - 10), 
  ( 1, 'Juan', CURRENT_DATE - 9), 
  ( 1, 'Luis', CURRENT_DATE - 8), 
  ( 2, 'Pepe', CURRENT_DATE - 10); 


--Titulos de los libros prestados junto el número de veces que fueron prestados
/*Sol
"Libro 2             ";1
"Libro 1             ";3
*/
SELECT titulo, COUNT(codLibro) FROM prestamos JOIN libros USING(codLibro) GROUP BY titulo;

--Titulos de los libros prestados y CP de donde fueron prestados, junto el número de veces que fueron prestados en ese CP
/*Sol
"Libro 1             ";9002;1
"Libro 1             ";9001;2
"Libro 2             ";9002;1
*/
SELECT titulo,CP,COUNT(codLibro) FROM libros JOIN prestamos USING(codLibro) JOIN socios ON(socio=nombre) GROUP BY titulo,CP;

--Titulos de todos los libros y CP de donde fueron prestados, junto el número de veces que fueron prestados en ese CP, si un libro no fue prestado que apareca 'NO PRESTADO' en el CP y cero en el número de veces
/*Sol
"Libro 3             ";"NO PRESTADO";0
"Libro 1             ";"9002 ";1
"Libro 1             ";"9001 ";2
"Libro 2             ";"9002 ";1
*/
SELECT titulo,CAST(CP AS CHAR(11)),COUNT(codLibro) FROM libros JOIN prestamos USING(codLibro) JOIN socios ON(socio=nombre) GROUP BY titulo,CP
UNION
(SELECT titulo,'NO PRESTADO',0 FROM (libros LEFT JOIN prestamos USING(codLibro))
WHERE fecha IS NULL GROUP BY titulo) ORDER BY COUNT ASC; --Si no está prestado la cantidad será siempre 0

--Diferencia entre el número de veces máximo y el número de veces mínimo que fue prestado un libro
/*Sol, los libros fueron prestados Lib2: 1 vez, Lib1: 3 veces y Lib3: 0 veces
El máximo nro de veces es 3
El mínimo es 0
La diferencia entre el máximo y el mínimo es 3-0=3*/

DROP TABLE IF EXISTS maximoYMinimo;
CREATE TABLE maximoYMinimo AS(
SELECT COUNT(codLibro) FROM libros JOIN prestamos USING(codLibro) JOIN socios ON(socio=nombre) GROUP BY titulo
UNION
(SELECT 0 FROM (libros LEFT JOIN prestamos USING(codLibro))
WHERE fecha IS NULL GROUP BY titulo)
);
SELECT MAX(count)-MIN(count) FROM maximoYMinimo;


