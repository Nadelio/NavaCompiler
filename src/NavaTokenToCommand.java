package src;
public class NavaTokenToCommand
{

    public static void run(TokenData td){
        if(td.getCommandToken().equals("DEC")){
            NavaCompiler.compilerVariables[td.getCommandAddress()]--;
            if(NavaCompiler.compilerVariables[td.getCommandAddress()] < 0){NavaCompiler.compilerVariables[td.getCommandAddress()] = 0;}
        } else if(td.getCommandToken().equals("INC")){
            NavaCompiler.compilerVariables[td.getCommandAddress()]++;
        } else if(td.getCommandToken().equals("MOV")){
            NavaCompiler.compilerVariables[td.getCommandValue()] = NavaCompiler.compilerVariables[td.getCommandAddress()];
            NavaCompiler.compilerVariables[td.getCommandAddress()] = 0;
        } else if(td.getCommandToken().equals("SET")){
            NavaCompiler.compilerVariables[td.getCommandAddress()] = td.getCommandValue();
        } else if(td.getCommandToken().equals("OUT")){
            if(td.isString()){
                System.out.print("\n" + td.getCommandValue2());
            } else {
                System.out.print("\n" + NavaCompiler.compilerVariables[td.getCommandAddress()]);
            }
        } else {
            try{
                throw new Exception("Line: " + NavaCompiler.lineNumber + ", Program Started On Line: "
                                             + NavaCompiler.programStartLine + " has an incorrect or unknown command!\n" + "Error type: " 
                                             + NavaCompiler.getErrorTypes()[td.getErrorType()]);
            }catch(Exception e){e.printStackTrace();}
        }
    }    
}
