DROP TABLE IF EXISTS Almacen;

CREATE TABLE Almacen(
        n_Producto      INTEGER CONSTRAINT PK_n_Producto PRIMARY KEY,
        nom_Producto    VARCHAR(40) CONSTRAINT UNQ_nom_Producto UNIQUE NOT NULL,
        coste           INTEGER NOT NULL,
        venta           INTEGER NOT NULL,
        fecha_ultima    DATE NOT NULL
);

INSERT INTO Almacen VALUES (0,'Agua',50,60,'25-08-2016'),
                           (1,'Patatas',150,25,'19-09-2016'),
                           (2,'Azucar',250,80,'31-12-1957'),
                           (3,'Trigo',25,190,'01-01-2016'),
                           (4,'Centeno',120,150,'16-10-2016'),
                           (5,'Maiz',250,125,'20-04-2004'),
                           (6,'Botellas',15,235,'10-10-2013'),
                           (7,'Elo',100,300,'25-12-2000'),
                           (8,'JKArch',299,300,'30-06-2015');

SELECT * FROM Almacen WHERE (coste < 200 AND venta <= 100) OR NOT(NOT(venta >= 100 AND venta < 200 AND coste >= 100) AND NOT(venta >= 200 AND 
venta <=300 AND coste < 200));
SELECT * FROM Almacen WHERE (coste < 100 AND NOT(venta > 100 AND NOT(venta >= 200 AND venta <= 300)) OR NOT(NOT(coste >= 100 AND coste < 200) 
AND NOT(coste >= 200 AND coste < 300 AND Venta >= 100 AND Venta < 200)));
SELECT * FROM Almacen WHERE NOT(coste >= 200 AND NOT( NOT venta <= 100 AND NOT venta >= 200) OR coste < 100 AND venta >= 100 AND venta < 200);
SELECT * FROM Almacen WHERE NOT(coste >= 200  AND venta <= 100 OR NOT(NOT(venta >= 100 AND venta < 200 AND coste < 100) AND NOT(venta >= 200 
AND venta <= 300 AND coste >= 200)));
SELECT * FROM Almacen WHERE NOT((coste < 100 AND venta >= 100 AND venta <200) OR NOT(NOT(venta <= 100 AND coste >= 200 AND coste <300) AND 
NOT(venta >= 200 AND venta <= 300 AND coste >= 200 AND coste <300)));
