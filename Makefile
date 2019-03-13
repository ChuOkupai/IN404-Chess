all: jar run

build: src/*.java
	javac $^ -d bin
	
Echec.jar: build
	jar cfe Echec.jar . ./bin/Echec.class

run: jar
	java -jar Echec.jar
	
