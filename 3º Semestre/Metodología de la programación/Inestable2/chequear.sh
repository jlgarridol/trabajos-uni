#/bin/bash
export CLASSPATH=./lib/*:./bin

javadoc -doclet com.sun.tools.doclets.doccheck.DocCheck -docletpath ./lib/doccheck.jar -d doccheck -private -sourcepath ./src -subpackages juego
