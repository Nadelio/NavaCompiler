# Documentation:

The documentation for Nava is incredibly simple:\
    You have 10 commands, `INC`, `DEC`, `MOV`, `SET`, `OUT`, `ADD`, `SUB`, `MUL`, `DIV`, `SIF`, and `RPT`.
- `INC` increases the address given in the parenthesis by 1
- `DEC` decreases the address given in the parenthesis by 1
- `MOV` moves the value of the first address to the second address, and sets the first address to `0`
- `SET` sets the address (the first number), to the second number
- `OUT` outputs what is in the middle of the parenthesis
- `ADD` stores the sum of the values of the first and second address into the third address
- `SUB` stores the difference of the values of the first and second address into the third address, throws a negative number error if the difference is negative
- `MUL` stores the product of the values of the first and second address into the third address
- `DIV` stores the whole number quotient of the values of the first and second address into the third address
- `SIF` compares the values of the first and second address using the comparison arguement given, if it results as `true`, it will run the next line, otherwise, it will skip the next line\
    These are the 6 comparison operators:
    - `EQL` -> `==`
    - `NEQL` -> `!=`
    - `GRT` -> `>`
    - `LES` -> `<`
    - `GRTEQL` -> `>=`
    - `LESEQL` -> `<=`
- `FUN` calls a function, function names are case sensitive when you call them
- `RPT` repeats the next line the number of times that is in the parentheses
    - `RPT(n)` will repeat the next line `n` times
- `DLY` delays the program for `n` seconds
    - `DLY(n)`

The basic structure of commands looks like:\
`cmd`(`value`,`value2`,`value3`);\
`value2` and `value3` are only used in `MOV`, `SET`, `ADD`, `SUB`, `MUL`, `DIV`, and `SIF`.\
The comma is not needed if you do not have `value2` or a `value3`.

Your `.nava` file also has a basic structure, it looks like this:\
```
1 SIZE=1;
2 >
3 DEC(0);
4 <
```
*The line numbers are not included in the code, that is for documentation purposes only.* \
`SIZE=` should always be the start of your program, following it will be the number of variables you will use in your program, in the example above, I chose only 1 variable\
Following the *variable number declaration*, or `VND` for short, will be your program body, denoted by a `>`. That symbol tells the compiler that you have started your program body.\
Following that declaration, can be any number of commands, as long as your hardware can handle it.\
When you want to signal the end to the program you will write a `<`.\
This tells the compiler that you are ending your program body, and anything that follows it needs to be whitespace or it will throw an error.\
Comments have to come after a `;` and can only be in the program body.\
Doubles, Floats, Arrays, Loops, and certain features of Assembly are not supported yet inside of Nava.\
All command lines have to end in a `;`, similarly to `Java`. The only exceptions are `>`, `<`, `name[` and `]` lines.\
Functions have to be defined before the main body of the program and after the `VND`.\
Nava supports recursion and calling functions inside of functions.\
You can also use *Extensions*, which are, as the name suggests, extensions of the Nava language\
To add an Extension to your project, create a folder called "Extensions" and add the `.jar` file for the extension you want in there.\
Extensions work by being declared after your `VND`. To declare them, you use this syntax:\
`HOOK=`, then your extension name as it is declared in the extension, make sure if you are using an extension, that the extension properly instantiates its class.\
To use multiple extensions, seperate them with a `,`.\
Make sure to end the *dependent extension declaration* (`DED` for short) with a `;` so as to avoid a syntax error.\
For more information on *creating an extension*, please read the `EXTENSIONS.md` file.

# 
Here is a "Hello World" script in Nava:
```
SIZE=1;
>
OUT(Hello World);
<
```
Super simple, super easy, I can argue even easier than Python! (Just kidding)

#
Here is how to write a function then call it in Nava:
```
SIZE=1;

sayHello[
OUT(Hello World!);
]

>
FUN(sayHello);
<
```
One use for functions is to get around the one line limit for `SIF` and `RPT` commands:
```
SIZE=1;

SIFBODY[
OUT(THEY ARE EQUAL);
INC(0);
]

RPTBODY[
SIF(0,EQL,0);
FUN(SIFBODY);
]

>
RPT(1);
FUN(RPTBODY);
<
```