# Software Practice 1

## Introduction
**Dangkong Project** (2017.04 ~ 2017.06): made by *Kyuyeon Kim* & *Yeonjae Kim*

This is piano program implemented with Java. Simple UIs are provided and sound can be triggered by not only mouse but also keyboard.
- You can simply play the piano with pedal, by **Singleplay** mode.
- You can also play together with your friend online, by **Multiplay** mode.
- You can practice scores you want, by **Song Practice** mode.

For further information, `DangKong.pptx` is provided for your better understanding.

**Professor** - *Hwansu Han*<br>
**Assistant** - *Jeonghwan Park*

## Development Environment
- **Eclipse** with **Java 1.8.0**

## Compilation and Execution (Linux)
Access into the project folder you want (either **DangKongServer** or **DangKongPiano**). Please be sure that `bin` directory exists on current working directory.<br>
Type the following for compilation:
```
$ javac -cp . -d bin $(find ./src -name *.java)
```
Type the following for execution:
- For **DangKongPiano**
```
$ java -cp ./bin main.Main
```
- For **DangKongServer**
```
$ java -cp ./bin server.Server
```
`make` command not only compiles the sources codes but also executes the program (see `Makefile`).
```
$ make
```

## Warnings
- Server should be running before user joins into **Multiplay** mode. If you come across exception problem on server, please shut down all of Dangkong programs and restart from the beginning.
- Server program is very subject to user connection (also disconnection), which means several exception problems would occur for not freeing resources of disconnected users.
- Please keep in mind that **DangKongServer** and **DangKongPiano** are different projects, whose source codes are included in each folder.
