package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NavaCompiler{

    public static final String[] cmds = {"DEC", "INC", "MOV", "SET", "OUT", "ADD", "SUB", "MUL", "DIV", "SIF", "FUN", "RPT"};
    private static final String[] errorTypes = {"UnknownCommandError", "NegativeIndexError", "IncompleteFunctionError", "IncorrectSyntaxError"};
    public static final String[] cmps = {"EQL", "NEQL", "GRT", "LES", "GRTEQL", "LESEQL"};
    private static FileReader fr = null;
    public static int[] compilerIntVariables;
    private static BufferedReader reader;
    public static int programStartLine;
    public static int lineNumber;
    public static HashMap<String, NavaFunction> functions = new HashMap<String, NavaFunction>();

    public static void compile(){
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
                compilerIntVariables = new int[compilerVariablesSize];
            }

            boolean inProgramBody = false;
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
        }catch(Exception e){e.printStackTrace();}
    }

    public static void main(String[] args){
        File folder = new File("src/NAVA/");
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

    public static BufferedReader getBR(){return reader;}
    public static FileReader getFR(){return fr;}
    public static String[] getErrorTypes(){return errorTypes;}

    public static void setFR(FileReader fileReader){fr = fileReader;}
    public static void setBR(BufferedReader bufferedReader){reader = bufferedReader;}
}