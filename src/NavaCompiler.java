package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class NavaCompiler{

    public static final String[] cmds = {"DEC", "INC", "MOV", "SET", "OUT", "ADD", "SUB", "MUL", "DIV", "SIF"};
    private static final String[] errorTypes = {"UnknownCommandError", "NegativeNumberError", "UnusedError", "IncorrectSyntaxError"};
    public static final String[] cmps = {"EQL", "NEQL", "GRT", "LES", "GRTEQL", "LESEQL"};
    private static FileReader fr = null;
    public static int[] compilerVariables;
    private static BufferedReader reader;
    public static int programStartLine;
    public static int lineNumber;


    public static void compile(){
        try{
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            if(!line.substring(0,5).equals("SIZE=")){
                throw new Exception("Line: " + 0 + ", Program Started On Line: N/A has incorrect syntax!\n" + "Error type: " + errorTypes[3]);
            } else {
                int compilerVariablesSize = Integer.parseInt(line.substring(5, line.indexOf(";")));
                if(compilerVariablesSize <= 0){
                    throw new Exception("Line: " + 0 + ", Program Started On Line: N/A has incorrect syntax!\n" + "Error type: " + errorTypes[1]);
                }
                compilerVariables = new int[compilerVariablesSize];
            }
            lineNumber = 1;

            boolean inProgramBody = false;

            while(line != null){
                if(line.equals(">")){
                    inProgramBody = true;
                    programStartLine = lineNumber;
                } else if(line.equals("<") && !inProgramBody){
                    throw new Exception("Line: " + lineNumber + ", Program Started On Line: " + programStartLine + " has incorrect syntax!\n" + "Error type: " + errorTypes[3]);
                } else if(line.equals("<")){
                    inProgramBody = false;
                } else if(inProgramBody){
                    TokenData td = NavaErrorCheck.checkLine(line);
                    if(td.getErrorStatus()){
                        throw new Exception("Line: " + lineNumber + ", Program Started On Line: " + programStartLine + " has an incorrect or unknown command!\n" + "Error type: " + errorTypes[td.getErrorType()]);
                    }
                    NavaTokenToCommand.run(td);
                }

                line = reader.readLine();
                lineNumber++;
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public static void main(String[] args){
        File folder = new File("./NAVA/");
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