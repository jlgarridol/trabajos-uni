@echo off
CLASSPATH=.\lib\*
mkdir bin
javac -cp $CLASSPATH -d .\bin -sourcepath .\src -subpackages juego
