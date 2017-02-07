@echo off
SET %CLASSPATH%=.\lib\*:.\bin
java -cp %CLASSPATH% juego.gui.Inestable %1 %2 %3 %4 %5 %6