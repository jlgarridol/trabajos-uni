@echo off
SET %CLASSPATH%=.\lib\*:.\bin
javadoc -cp $CLASSPATH -d .\doc -charset UTF-8 -author -sourcepath .\src -subpackages juego
