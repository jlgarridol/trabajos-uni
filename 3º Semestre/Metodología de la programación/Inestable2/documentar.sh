#!/bin/bash

CLASSPATH=./lib/*:./bin
javadoc -cp $CLASSPATH -d ./doc -charset UTF-8 -author -sourcepath ./src -subpackages juego
exec firefox ./doc/index.html 2> /dev/null
