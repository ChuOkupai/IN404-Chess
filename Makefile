all: jar run

build: src/*.java
	javac $^ -d bin
	
jar: build
	jar cfe Echec.jar . ./bin/Echec.class

run: jar
	java -jar Echec.jar
	
