drop table if exists vehiculos, alquiler, sucursales cascade;

create table vehiculos(
	matricula 	varchar(5) primary key,
	tipo	  	char(1) not null check (tipo in ('T'/*turismo*/, 'F' /*furgoneta*/)),
	alquilerXhora	numeric(5,2)
);

insert into vehiculos values
( '1234X', 'T', 10),
( '1234Y', 'F', 15),
( '1234Z', 'T', 20);

create table sucursales(
	id	integer primary key,
	ciudad	varchar(15),
	cp	char(5)
);

insert into sucursales values
( 1, 'BURGOS', '09001'),
( 2, 'BURGOS', '09002'),
( 3, 'LEON',   '24001'),
( 4, 'VALLADOLID', '47001');

create table alquiler(
	matricula 	varchar(5) references vehiculos,
	fechaSalida	date not null,
	fechaEntrega	date not null,
	sucursalSalida	integer references sucursales not null,
	sucursalLlegada	integer references sucursales not null,
	nhoras		integer not null,
	primary key (matricula, fechaSalida), --un vehiculo no se puede alquilar mas de 1 vez al dia
	unique (matricula, fechaEntrega)      --un vehiculo no se puede alquilar mas de 1 vez al dia
);


insert into alquiler values

--Vehiculo X es un turismo que siempre se ha alquilado en las 2 sucursales que hay en Burgos
( '1234X', date '1-1-2015', date '1-1-2015', 1, 2, 8), -->BU 1 a BU 2
( '1234X', date '2-1-2015', date '2-1-2015', 2, 1, 8), -->BU 2 a BU 1
( '1234X', date '3-1-2015', date '3-1-2015', 1, 2, 8), -->BU 1 a BU 2 repetida
( '1234X', date '13-1-2015', date '13-1-2015', 3, 3, 8), -->LEON 3 a LEON 3
( '1234X', date '14-1-2015', date '14-1-2015', 1, 1, 8), -->BU 1 a BU 1
( '1234X', date '24-1-2015', date '24-1-2015', 2, 2, 8), -->BU 2 a BU 2

--Vehiculo Y es una furgoneta que siempre se ha alquilado en las 2 sucursales que hay en Burgos
( '1234Y', date '1-1-2015', date '1-1-2015', 1, 2, 8), -->BU 1 a BU 2
( '1234Y', date '2-1-2015', date '2-1-2015', 2, 3, 8), -->BU 2 a LEON 3
( '1234Y', date '14-1-2015', date '14-1-2015', 1, 1, 8), -->BU 1 a BU 1
( '1234Y', date '24-1-2015', date '24-1-2015', 2, 2, 8), -->BU 2 a BU 2

--Vehiculo Z es un turismo que nunca ha sido alquilado en la sucursal 2 de Burgos
( '1234Z', date '1-1-2015', date '1-1-2015', 1, 2, 8), -->BU 1 a BU 2
--( '1234Z', date '2-1-2015', date '2-1-2015', 2, 1, 8), -->BU 2 a BU 1
( '1234Z', date '14-1-2015', date '14-1-2015', 3, 1, 8), -->LEON a BU 1
( '1234Z', date '24-1-2015', date '24-1-2015', 1, 2, 8); -->BU 2 a BU 2


--Preg 1: vehiculos que han sido alquilados en mas del 50% de las sucursales
/*
Solucion
"1234X"
*/
SELECT DISTINCT matricula FROM alquiler GROUP BY matricula HAVING COUNT(DISTINCT sucursalSalida) > ((SELECT COUNT(id) FROM sucursales)/2);

--Preg 2: discutir la necesidad de hacer count distintc tanto en la consulta principal, como en la subconsulta

--En la subconsulta no es necesario el distinct count debido a que se cuentan las primary key y siempre serán distintas
--Es importante realizarlo en el having debido a que existe la posibilidad que un coche sea alquilado más de una vez en la misma sucursal

--Preg 3: vehiculo y tipo de los vehiculos que han sido alquilados en mas del 50% de las sucursales

/*
Solucion:
"1234X";"T"
*/
SELECT DISTINCT matricula,tipo FROM alquiler NATURAL JOIN vehiculos GROUP BY matricula,tipo HAVING COUNT(DISTINCT sucursalSalida) > ((SELECT COUNT(id) FROM sucursales)/2);

--Preg 4: vehiculos alquilados en todas las sucursales de origen con codigo postal q empiece por 09

/*
"1234X"
"1234Y"
*/
SELECT DISTINCT matricula FROM alquiler as a
WHERE
((SELECT COUNT(DISTINCT sucursalSalida) FROM sucursales JOIN alquiler ON (id=sucursalSalida) WHERE cp LIKE '09%' AND a.matricula = matricula))
 = 
((SELECT COUNT(id) FROM sucursales WHERE cp LIKE '09%' )) GROUP BY matricula ;


--Preg 5: turismos alquilados en sucursales de origen con codigo postal q empiece por 09 

/*
"1234X" (1234Z también cumple con el enunciado teniendo en cuenta los datos)
*/
--SELECT DISTINCT matricula FROM sucursales JOIN alquiler ON(id=sucursalSalida) NATURAL JOIN vehiculos WHERE (cp LIKE '09%' AND tipo = 'T');


--Preg 6: Se pide interpretar esta consulta correlacionada 

/*select ciudad
from sucursales s
where not exists ( select *
		from sucursales
		where s.id!=id
		and s.ciudad=ciudad);*/
		

/*¿Cual/es es/son la/s ref/s externa/s? (0.15 puntos)
	Son referencias externas s.id y s.ciudad
  ¿para que crees que se hace el alias de tabla "s"?. Si no hiciera falta justificarlo igualmente (0.15 puntos)
	Hace falta porque los atributos tienen el mismo nombre al igual que las tablas, si no se hiciera se compararían campos de la tabla sucursales
	llamada en la subconsulta no a la externa
  ¿Que se muestra para la sucursal 1 y por que?, ¿qué se muestra para la sucirsal 3 y por qué? (razonalo deshaciendo la correlacion para esas 2 sucursales) (1 punto)
	No se muestra la sucursal 1 porque existe en la misma ciudad otra sucursal ya que la subconsulta lo que realiza es una busqueda de sucursales
	que compartan ciudad y no sea la misma. Esto provoca que la subconsulta devuelva 1 tupla y por tanto existe, al hacer la función not se niega
	el resultado diciendo así que no se seleccione esa tupla

	Con la sucursal 3 pasa lo mismo solo que esta al no tener otra sucursal en su ciudad la subconsulta no devuelve tuplas y con la negación not 
	la correlación devuelve true haciendo que esta tupla sea seleccionada.
  ¿Que enunciado darías a la consulta? (0.7 puntos)
	Mostrar todas las ciudades con una sola sucursal
*/
		