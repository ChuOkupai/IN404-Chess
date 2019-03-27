# Temporaire pour remplacer la cible all
all-debug: debug

all: doc Chess.jar run

clean:
	rm -rf bin doc *.jar

doc:
	javadoc -charset UTF-8 -d doc src/*.java

build: src/*.java
	mkdir -p bin
	javac $^ -d bin

Chess.jar: doc build
	jar cfe $@ . ./bin/Main.class $<

run: Chess.jar
	java -jar $<

# Temporaire pour compiler rapidement
debug: src/Main.java
	mkdir -p bin
	javac -sourcepath src -d bin $<
	clear; java -cp bin Main; clear