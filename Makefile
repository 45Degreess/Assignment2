JC = javac
JAVA = java

#Flags for java command
JCFLAGS = -cp Assignment_2/lib/\* -d "bin"
JFLAGS =  -cp Assignment_2/lib/\*:bin
SOURCES = $(wildcard Assignment_2/src/*.java)

build:
	$(JC) $(JCFLAGS) $(SOURCES)

run: 
	$(JAVA) $(JFLAGS) Assignment_2.src.GenericsKbAVLApp > output.txt

Graph:
	$(JAVA) $(JFLAGS) Assignment_2.src.Graph

Experiment: 
	$(JAVA) $(JFLAGS) Assignment_2.src.Experiment

defult:
	build

clean:
	rm bin/Assignment_2/src/*.class
