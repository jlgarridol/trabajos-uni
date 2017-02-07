#!/bin/bash

CLASSPATH=./lib/inestable-gui-lib-1.0.0.jar:./lib/log4j-1.2.17.jar:./lib/slf4j-api-1.7.1.jar:./lib/slf4j-log4j12-1.7.1.jar:./bin
mkdir bin 2> /dev/null #Si no existe la carpeta para que no de error el CLASSPATH

case $1 in
	"-c") #Compilación
		echo "Compilando"
		javac -cp $CLASSPATH -d ./bin ./src/*/*/*
		if [ $? -eq 0 ];then
			echo "Compilación correcta"
		fi
		;;
	"-d") #Documentación
		echo "Generado documentación"
		javadoc -cp $CLASSPATH -d ./doc -charset UTF-8 -author ./src/*/*/*
		exec firefox ./doc/index.html 2> /dev/null 
		;;
	"-g") #Ejecución gráfica
		echo "Ejecutando gui"
		java -cp $CLASSPATH juego.gui.Inestable $2 $3 $4 $5
		;;
	"-t") #Ejecución texto
		echo "Ejecutando cli"
		java -cp $CLASSPATH juego.textui.Inestable $2 $3 $4 $5
		;;
	"-h") #Ayuda
		echo "Opciones:"
		echo "----------------------------------------------------------------------------"
		echo "-c	: Compila las fuentes"
		echo "-d	: Generar documentación - Si se tiene firefox instalado se abrirá además en el navegador"
		echo "----------------------------------------------------------------------------"
		echo "Las siguientes opciones solamente funcionarán si se ha compilado previamente"
		echo "-g	: Ejecuta la versión gráfica"
		echo "-t	: Ejecuta al versión texto"
		echo "Los parameros de nombre de los jugadores, y tamaño del tablero tambíen se pueden dar como flags adicionales"
		echo "----------------------------------------------------------------------------"
		echo "-h	: Muestra la ayuda"
		echo "-v	: Muestra la versión"
		;;
	"-v") #Version
		echo "Inestable - Versión 20161103"
		echo "Práctica Obligatoria 1 - Metodología de la Programación"
		echo "Profesor: Raúl Marticorena Sánchez"
		echo "Alumnos: José Luis Garrido Labrador y José Miguel Ramírez Sánz"
		;;
	*)
		echo "Parámetros incorrectos, utilice el flag -h para más información"
esac			