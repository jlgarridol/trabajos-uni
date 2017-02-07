#!/bin/bash

CLASSPATH=./lib/*
javac -cp "$CLASSPATH" -d ./bin -sourcepath ./src -subpackages juego
