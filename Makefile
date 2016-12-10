all:
	javac *.java
clean:
	rm -rf *.class
clear:
	clear
run:
	java Emulator $(file)
sample:
	java Emulator hello_world.gb
