all: doc Echec.jar run

clean:
	rm -rf doc src/*.class *.jar

doc:
	javadoc -charset UTF-8 -d doc src/*.java

build: src/*.java
	mkdir -p bin
	javac $^ -d bin

Chess.jar: doc build
	jar cfe $@ . ./bin/Main.class $<

run: Chess.jar
	java -jar $<