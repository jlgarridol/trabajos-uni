#!/bin/bash

CLASSPATH=./lib/inestable-gui-lib-1.0.0.jar:./lib/log4j-1.2.17.jar:./lib/slf4j-api-1.7.1.jar:./lib/slf4j-log4j12-1.7.1.jar
javac -cp $CLASSPATH -d ./bin ./src/*/*/*