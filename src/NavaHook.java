package src;

public abstract class NavaHook{
    private String hookName;  
    private String[] incompatibleExtensions;  

    public NavaHook(String hookName, String[] incompatibleExtensions){
        this.hookName = hookName;
        this.incompatibleExtensions = incompatibleExtensions;
        NavaCompiler.addToHookRegister(this);
        NavaCompiler.addToHookLibrary(hookName);
    }

    public abstract void process();
    public String getHookName(){return hookName;}
    public String[] getIncompatibleExtensions(){return incompatibleExtensions;}
}
