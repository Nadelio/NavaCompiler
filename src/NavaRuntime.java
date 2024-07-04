package src;

import java.util.HashMap;
import java.util.ArrayList;

public class NavaRuntime
{
    // hook data
    private static final HashMap<String, Boolean> hookLibrary = new HashMap<String, Boolean>();
    private static ArrayList<NavaHook> extensionRegister = new ArrayList<NavaHook>();
    private static HashMap<String, NavaHook> hookRegister = new HashMap<String, NavaHook>();
    private static String[] validHooks;

    // runtime data
    public static int[] compilerIntVariables;

    public static String[] getValidHooks(){return validHooks;}
    public static HashMap<String, NavaHook> getHookRegister(){return hookRegister;}
    public static HashMap<String, Boolean> getHookLibrary(){return hookLibrary;}

    public static void addToHookRegister(NavaHook hook){hookRegister.put(hook.getHookName(), hook);}
    public static void addToHookLibrary(String hook){hookLibrary.put(hook, false);}
    public static void addToExtensionRegister(NavaHook hook){extensionRegister.add(hook);}

    public static void setValidHooks(String[] hooks){validHooks = hooks;}
}
