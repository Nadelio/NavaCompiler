import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NavaCompiler{

    public static final String[] cmds = {"DEC", "INC", "MOV", "SET", "JMP"}; // decrement index
                                                                             // increment index,
                                                                             // move index to index,
                                                                             // set index to value,
                                                                             // loop from index to end specified number of times
    private static final String[] errorTypes = {"UnknownCommandError", "NegativeNumberError", "InvalidJumpIndexError", "IncorrectSyntaxError"};
    private static FileReader fr = null;
    public static int[] compilerVariables;


    public static void compile(){
        BufferedReader reader;
        try{
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            if(!line.substring(0,6).equals("SIZE=")){
                throw new Exception("Line: " + 0 + "has incorrect syntax!\n" + "Error type: " + errorTypes[3]);
            } else {
                int compilerVariablesSize = Integer.parseInt(line.substring(6, line.indexOf(";")));
                if(compilerVariablesSize <= 0){
                    throw new Exception("Line: " + 0 + "has incorrect syntax!\n" + "Error type: " + errorTypes[1]);
                }
                compilerVariables = new int[compilerVariablesSize];
            }
            int lineNumber = 0;

            while(line != null){
                TokenData td = NavaErrorCheck.checkLine(line);
                if(!td.getErrorStatus()){
                    throw new Exception("Line: " + lineNumber + " has an incorrect command!\n" + "Error type: " + errorTypes[td.getErrorType()]);
                }
                

                lineNumber++;
                line = reader.readLine();
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public static void main(String[] args){
        try{
            fr = new FileReader("prog.nava");
            compile();
            fr.close();
        } catch(IOException e){}
    }
}