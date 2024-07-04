package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Arrays;
import java.net.URL;
import java.net.URLClassLoader;

public class NavaCompiler{
    private static final String compilerVersion = "2.0.1";

    // command data
    public static final String[] cmds = {"DEC", "INC", "MOV", "SET", "OUT", "ADD", "SUB", "MUL", "DIV", "SIF", "FUN", "RPT", "DLY"};
    private static final String[] errorTypes = {"UnknownCommandError", "NegativeIndexError", "IncompleteFunctionError", "IncorrectSyntaxError", "UnknownHookError", "IncompatibleHookError"};
    public static final String[] cmps = {"EQL", "NEQL", "GRT", "LES", "GRTEQL", "LESEQL"};

    // file reader
    private static FileReader fr = null;
    private static BufferedReader reader;

    // compiler data
    public static int programStartLine;
    public static boolean programEnded;
    private static boolean inProgramBody;
    public static int lineNumber;

    // program data
    public static HashMap<String, NavaFunction> functions = new HashMap<String, NavaFunction>();

    public static void compile(){
        programEnded = false;
        try{
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            lineNumber = 1;
            if(!line.substring(0,5).equals("SIZE=")){
                throw new Exception("Line: " + 0 + ", Program Started On Line: N/A has incorrect syntax!\n" + "Error type: " + errorTypes[3]);
            } else {

                int compilerVariablesSize = Integer.parseInt(line.substring(5, line.indexOf(";")));
                if(compilerVariablesSize <= 0){
                    throw new Exception("Line: " + 0 + ", Program Started On Line: N/A has incorrect syntax!\n" + "Error type: " + errorTypes[1]);
                }
                NavaRuntime.compilerIntVariables = new int[compilerVariablesSize];
            }

            line = reader.readLine();
            lineNumber++;
            if(line.length() > 0){
                if(line.substring(0, 5).equals("HOOK=")){
                    String[] hooks = new String[1];
                    if(line.indexOf(',') == -1){
                        hooks[0] = line.substring(5, line.indexOf(';'));
                    } else {
                        hooks = line.substring(5, line.indexOf(';')).split(",");
                    }

                    NavaRuntime.setValidHooks(new String[0]);
                    for(String hook : hooks){
                        if(NavaRuntime.getHookLibrary().containsKey(hook)){
                            NavaRuntime.getHookLibrary().put(hook, true);
                            NavaRuntime.setValidHooks(ArrayUtils.add(NavaRuntime.getValidHooks(), hook));
                        } else {
                            throw new Exception("Line: " + lineNumber + ", Program Started On Line: N/A has an unknown hook: " + hook + "\n" + "Error type: " + errorTypes[4]);
                        }
                    }

                    for(String hook : NavaRuntime.getValidHooks()){
                        hookConflictCheck(hook);
                    }
                } else {
                    throw new Exception("Line: " + lineNumber + ", Program Started On Line: N/A has incorrect syntax!\n" + "Error type: " + errorTypes[3]);
                
                }
            }
            inProgramBody = false;
            boolean inFunctionBody = false;
            String functionName = "";
            TokenData[] commands = new TokenData[0];

            while(line != null && !line.equals(">")){
                functionName = "";
                commands = new TokenData[0];
                while(!line.equals("]")){
                    line = reader.readLine();
                    lineNumber++;
                    if(line.contains("[") && (line.length() - 1 ==  line.indexOf("[")) && !inFunctionBody){
                        functionName = line.substring(0, line.indexOf("["));
                        inFunctionBody = true;
                    } else if(inFunctionBody){
                        if(line.equals("]")){
                            functions.put(functionName, new NavaFunction(commands, functionName));
                            inFunctionBody = false;
                            break;
                        } else if(inFunctionBody && !line.equals("]")){
                            TokenData td = NavaParser.checkLine(line);
                            commands = ArrayUtils.add(commands, td);
                        }
                } else if(!line.equals("")){
                        if(line.equals(">")){break;}
                        throw new Exception("Line: " + lineNumber + ", Program Started On Line: N/A" + " has an incomplete function declaration statement.\n" + "Error type: " + errorTypes[2]);
                    }
                }
                if(line.equals(">")){break;}
                line = reader.readLine();
                lineNumber++;
            }

            while(line != null){
                if(line.equals(">")){
                    inProgramBody = true;
                    programStartLine = lineNumber;
                } else if(line.equals("<") && !inProgramBody){
                    throw new Exception("Line: " + lineNumber + ", Program Started On Line: " + programStartLine + " has incorrect syntax!\n" + "Error type: " + errorTypes[3]);
                } else if(line.equals("<")){
                    inProgramBody = false;
                } else if(inProgramBody){
                    TokenData td = NavaParser.checkLine(line);
                    if(td.getErrorStatus()){
                        throw new Exception("Line: " + lineNumber + ", Program Started On Line: " + programStartLine + " has an incorrect or unknown command!\n" + "Error type: " + errorTypes[td.getErrorType()]);
                    }
                    NavaRunner.run(td);
                }

                line = reader.readLine();
                lineNumber++;
            }

            programEnded = true;
            reader.close();
        }catch(Exception e){e.printStackTrace();}
    }

    public static void main(String[] args){
        System.out.println("Using Nava Compiler Version: " + compilerVersion);

        // load debug mode
        if(args.length == 0){args = new String[]{"false"};}
        Debugger.setDebugMode(args[0]);

        // load extensions
        String extensionFolderPath;
        if(Debugger.getDebugMode()){
            extensionFolderPath = "src/Extensions/";
            Debugger.log("Loaded Debug mode");
        } else{extensionFolderPath = "./Extensions/";}
        loadExtensions(extensionFolderPath);

        // run Nava files
        String pathname;
        if(Debugger.getDebugMode()){pathname = "src/NAVA/";} else {pathname = "./NAVA/";}
        File folder = new File(pathname);
        File[] files = folder.listFiles();
        try{
            for(File file : files){
                if(file.isFile() && file.getName().endsWith(".nava")){
                    fr = new FileReader(file);
                    compile();
                    fr.close();
                }
            }
        } catch(IOException e){}
    }

    private static void hookConflictCheck(String hook) throws Exception{
        String[] incompatibleHooks = NavaRuntime.getHookRegister().get(hook).getIncompatibleExtensions();
        for(String validHook : NavaRuntime.getValidHooks()){
            if(Arrays.asList(incompatibleHooks).contains(validHook)){
                throw new Exception("Incompatible Hook: \"" + hook + "\" with Hook: \"" + validHook + "\"\nError type: " + errorTypes[5]);
            }
        }
    }

    private static void loadExtensions(String folderPath){
        Debugger.log("Loading Extensions");
        File folder = new File(folderPath);
        File[] jarFiles = folder.listFiles(new FileFilter() {
            public boolean accept(File file){return file.isFile() && file.getName().endsWith(".jar");}
        });

        for(File file : jarFiles){
            try{
                URL jarURL = file.toURI().toURL();
                URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL});
                String className = file.getName().substring(0, file.getName().length() - 4);
                Class<?> loadedClass = Class.forName(className, true, classLoader);
                if (NavaHook.class.isAssignableFrom(loadedClass)) {
                    NavaHook instance = (NavaHook) loadedClass.getDeclaredConstructor().newInstance();
                    instance.setupExtension();
                    Debugger.log("Loaded Extension: " + className);
                } else {
                    Debugger.log(className + " is not a NavaHook extension");
                }
                classLoader.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static BufferedReader getBR(){return reader;}
    public static FileReader getFR(){return fr;}
    public static String[] getErrorTypes(){return errorTypes;}

    public static void setFR(FileReader fileReader){fr = fileReader;}
    public static void setBR(BufferedReader bufferedReader){reader = bufferedReader;}
}