# Nava Compiler

### To set up:
1. Create a folder and put the .jar named "NavaCompiler" inside it
2. Create a subfolder and name it "NAVA"
3. Create a file with the suffix `.nava`
4. Begin programming!

### Running the compiler:
1. Double check that you have the *Java Runtime Environment* downloaded and add to your `PATH` (if you do not know what this is, look up a tutorial to add JRE to your PATH)
2. Open terminal
3. Change your directory to the folder with the NavaCompiler.jar file (using `cd <pathname>`)
4. Run the command `java NavaCompiler.jar`
5. Watch your program run!

### Purpose:
This project was just to do the absolute simpliest compiler I could do, so I made an assembly mimic, it only has 5 commands (6 if you count `SIZE=`).
I plan on adding more in the future, such as `JMP`, the only reason I don't have `JMP` in the V1.0 is because I couldn't figure out how to get the `BufferedReader` to go back to the start of the file without causing a ton of issues and making the language slow.
I probably won't make a syntax highlighting file or error highlighting, mainly because I don't have a EBNF grammar for this language and it's hard to make those without one. 
This was a really fun project, and I'm glad I finally got one of my languages that I am working on complete!
I hope whoever chooses to use this language enjoys messing around with it.
I doubt you'll find any true use in it right now, but maybe in the future when I add more commands and QOL features it'll become more of something people actually might find utility in.