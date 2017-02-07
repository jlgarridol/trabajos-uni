DROP TABLE IF EXISTS examen;	-- Con esta línea borramos la tabla
CREATE TABLE examen (		-- Aquí la volvemos a crear la tabla examen
             nombre    char(10),	-- Creamos los campos nombre y nota
             nota      numeric( 4, 2)
);

INSERT INTO examen ( nombre, nota ) VALUES ( 'Pepe', 5.75);	-- Damos valor a esos campos que hemos creado
INSERT INTO examen ( nombre, nota ) VALUES ( 'Ana',  8);
INSERT INTO examen ( nombre, nota ) VALUES ( 'Juan', 4.5);             
SELECT * FROM examen;		-- Muestra los resultados de la tabla