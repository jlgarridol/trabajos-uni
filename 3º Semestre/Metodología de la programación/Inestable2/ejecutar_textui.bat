@echo off
SET %CLASSPATH%=.\lib\*:.\bin
java -cp %CLASSPATH% juego.textui.Inestable %1 %2 %3 %4 %5 %6
