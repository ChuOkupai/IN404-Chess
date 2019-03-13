all: jar run

build: src/*.java
	mkdir -p bin
	javac $^ -d bin

Echec.jar: build
	jar cfe $@ . ./bin/Echec.class

run: Echec.jar
	java -jar $<
