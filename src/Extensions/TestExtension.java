package src.Extensions;

import src.Debugger;
import src.NavaHook;

public class TestExtension extends NavaHook{
    public TestExtension(){super("TestExtension", new String[]{});}

    public void process(){
        Debugger.log("Test Extension Hook Process");
    }
}
