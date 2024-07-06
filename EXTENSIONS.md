# Extension Documentation

Extensions are strictly only allowed to interact with your Nava program and not the compiler. It can only do this by accessing the `compilerIntVariables` from the `NavaRuntime` class. This is to prevent any unwanted behavior from your extension.

The basic template extension looks like this:
```java
public class TemplateExtension extends NavaHook {
    private static String hookName;
    private static String[] incompatibleExtensions;
    private static int[] usedPorts;

    public void setupExtension(){
        hookName = "TemplateExtension"; // change this to your class name
        incompatibleExtensions = new String[]{}; // change the array to hold the extensions that would conflict with yours
        usedPorts = new int[]{}; // change this array to hold all the variable numbers that your program checks
        NavaRuntime.addToHookRegister(this);
        NavaRuntime.addToHookLibrary(hookName);
    }

    public void process(){
        int[] compilerVariables = NavaCompiler.compilerIntVariables;
        // add your extension's behavior here
    }

    public String getHookName(){return hookName;}
    public String[] getIncompatibleExtensions(){return incompatibleExtensions;}
    public int[] getUsedPorts(){return usedPorts;}
}
```
In the `incompatibleExtensions` array, you will add the names of the extensions that have conflicting variable array indices.\
In the `process()` method, you will add all the behavior for your extension.

### Debugging
To debug your extension, you can use the `Debugger.log()` method, which takes in a String as a paramter.\
Make sure you run your code in `Debug` mode by adding `true` to the end of the run command (See: "README/To set up" for more information)

### Compiling Your Extension For Use
To compile your extension into the proper .jar file, you will need these classes:
- `NavaHook.java`
- `NavaRuntime.java`
- `Debugger.java` (only include if you used its methods)

You need these two/three files to be in the `src` package for them to work properly, as when you compile to a .jar and then add it to the `Extensions` folder in your project the extension loader checks for the src.*.java files in your .jar\
###### *THIS RULE DOES NOT APPLY TO YOUR MAIN EXTENSION CLASS AND OTHER CLASSES YOU USE FOR YOUR EXTENSION*