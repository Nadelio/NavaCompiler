package src;

public abstract class NavaHook{ 
    public abstract void setupExtension();
    public abstract void process();

    public abstract String getHookName();
    public abstract String[] getIncompatibleExtensions();
    public abstract int[] getUsedPorts();
}
