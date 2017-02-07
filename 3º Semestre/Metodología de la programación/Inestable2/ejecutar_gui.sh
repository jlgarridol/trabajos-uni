#!/bin/bash

CLASSPATH=./lib/*:./bin
java -cp $CLASSPATH -ea juego.gui.Inestable $1 $2 $3 $4 $5 $6
