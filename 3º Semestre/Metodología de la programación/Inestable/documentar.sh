#!/bin/bash

CLASSPATH=./lib/inestable-gui-lib-1.0.0.jar:./lib/log4j-1.2.17.jar:./lib/slf4j-api-1.7.1.jar:./lib/slf4j-log4j12-1.7.1.jar:./bin
javadoc -cp $CLASSPATH -d ./doc -charset UTF-8 -author ./src/*/*/*
exec firefox ./doc/index.html 2> /dev/null 