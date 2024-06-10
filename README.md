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
<br/>
<br/>
<br/>
<br/>
## Documentation:

The documentation for Nava is incredibly simple:\
You have 5 commands, `INC`, `DEC`, `MOV`, `SET`, and `OUT`.
- `INC` increments the address given in the parenthesis
- `DEC` *decrements* the address given in the parenthesis
- `MOV` moves the value of the first address to the second address, and sets the first address to `0`
- `SET` sets the address (the first number), to the second number
- `OUT` outputs what is in the middle of the parenthesis
- `ADD` stores the sum of the values of the first and second address into the third address
- `SUB` stores the difference of the values of the first and second address into the third address, throws a negative number error if the difference is negative
- `MUL` stores the product of the values of the first and second address into the third address
- `DIV` stores the whole number quotient of the values of the first and second address into the third address
- `SIF` compares the values of the first and second address using the comparison arguement given, if it results as `true`, it will run the next line, otherwise, it will skip the next line
    - `EQL` -> `==`
    - `NEQL` -> `!=`
    - `GRT` -> `>`
    - `LES` -> `<`
    - `GRTEQL` -> `>=`
    - `LESEQL` -> `<=`

The basic structure of commands looks like:\
`cmd`(`value`,`value2`);\
`value2` is only used in `MOV` and `SET`.\
The comma is not needed if you do not have `value2`.\

Your `.nava` file also has a basic structure, it looks like this:\
```
1 SIZE=1;
2 >
3 DEC(0);
4 <
```
*The line numbers are not included in the code, that is for documentation purposes only.* \
`SIZE=` should always be the start of your program, following it will be the number of variables you will use in your program, in the example above, I chose only 1 variable\
Following the *variable number declaration*, or `VNB` for short, will be your program body, denoted by a `>`. That symbol tells the compiler that you have started your program body.\
Following that declaration, can be any number of commands, as long as your hardware can handle it.\
When you want to signal the end to the program you will write a `<`.\
This tells the compiler that you are ending your program body, and anything that follows it needs to be whitespace or it will through an error.\
Comments have to come after a `;` and can only be in the program body.\
Doubles, Floats, Arrays, Loops, and certain features of Assembly are not supported yet inside of Nava.\
# 
Here is a "Hello World" script in Nava:
```
SIZE=1;
>
OUT(Hello World);
<
```
Super simple, super easy, I can argue even easier than Python! (Just kidding)
