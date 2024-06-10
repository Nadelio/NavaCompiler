# Nava Compiler

### To set up:
1. Create a folder and put the .jar named "NavaCompiler" inside it
2. Create a subfolder and name it "NAVA"
3. Create a file with the suffix `.nava`
4. Begin programming!

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
Comments are not supported in Nava.\
Doubles, Floats, Arrays, Loops, Conditionals, and other features of Assembly are not supported yet inside of Nava.\
I do plan on adding comment support soon.
# 
Here is a "Hello World" script in Nava:
```
SIZE=1;
>
OUT(Hello World);
<
```
Super simple, super easy, I can argue even easier than Python! (Just kidding)
