CLASSES = $(patsubst src/%.java,bin/%.class,$(wildcard src/*.java))

all: Chess.jar run

clean:
	rm -rf bin doc *.jar

doc:
	javadoc -charset UTF-8 -d doc src/*.java

$(CLASSES): src/*.java
	mkdir -p bin
	javac $^ -d bin

Chess.jar: doc $(CLASSES)
	jar cfe $@ bin.Main bin/*.class src/*.java $<

run: Chess.jar
	java -cp $< -cp bin Main