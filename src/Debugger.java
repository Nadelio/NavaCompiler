package src;

public class Debugger
{
    private static boolean debugMode;

    public static void log(String message){
        if(debugMode){
            System.out.println("[DEBUG MESSAGE] " + message);
        }
    }

    public static void setDebugMode(String mode){
        if(mode == "true"){
            debugMode = true;
        } else if(mode == "false"){
            debugMode = false;
        } else {
            throw new IllegalArgumentException("Invalid debug mode: " + mode);
        }
    }
    public static boolean getDebugMode(){return debugMode;}
}
