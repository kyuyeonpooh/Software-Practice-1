all:
	find ./src -name *.java > srcList.txt
	javac -cp . -d bin @srcList.txt
	java -cp ./bin main.Main
