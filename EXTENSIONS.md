# Extension Documentation

Extensions are strictly only allowed to interact with your Nava program and not the compiler. It can only do this by accessing the `compilerIntVariables` from the `NavaCompiler` class. This is to prevent any unwanted behavior from your extension.

The basic template extension looks like this:
```java
public class TemplateExtension extends NavaHook {

    public TemplateExtension(){
        String[] incompatibleExtensions = new String[0];
        super("TemplateExtension", incompatibleExtensions);
    }

    public void process(){
        int[] compilerVariables = NavaCompiler.compilerIntVariables;
        // add your extension's behavior here
    }

}
```
In the `incompatibleExtensions` array, you will add the names of the extensions that have conflicting variable array indices.\
In the `process()` method, you will add all the behavior for your extension.\
I suggest adding a one time if statement that runs your extension's constructor in order to add it to the `hookRegister`:
```java
private static bool initialized = false;

public TemplateExtension(){
    ...
    if(!initialized){
        new TemplateExtension();
        initialized = true;
    }
}
...
```

### Debugging
To debug your extension, you can use the `Debugger.log()` method, which takes in a String as a paramter.\
Make sure you run your code in `Debug` mode by adding `true` to the end of the run command (See: "README/To set up" for more information)