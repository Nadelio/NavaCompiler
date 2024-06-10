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
        } else if(td.getCommandToken().equals("ADD")){
            NavaCompiler.compilerVariables[td.getCommandOutputAddress()] = NavaCompiler.compilerVariables[td.getCommandAddress()] + NavaCompiler.compilerVariables[td.getCommandValue()];
        } else if(td.getCommandToken().equals("SUB")){
            NavaCompiler.compilerVariables[td.getCommandOutputAddress()] = NavaCompiler.compilerVariables[td.getCommandAddress()] - NavaCompiler.compilerVariables[td.getCommandValue()];
        } else if(td.getCommandToken().equals("MUL")){
            NavaCompiler.compilerVariables[td.getCommandOutputAddress()] = NavaCompiler.compilerVariables[td.getCommandAddress()] * NavaCompiler.compilerVariables[td.getCommandValue()];
        } else if(td.getCommandToken().equals("DIV")){
            NavaCompiler.compilerVariables[td.getCommandOutputAddress()] = NavaCompiler.compilerVariables[td.getCommandAddress()] / NavaCompiler.compilerVariables[td.getCommandValue()];
        } else if(td.getCommandToken().equals("SIF")){
            String commandValue2 = td.getCommandValue2();

            if ("NEQL".equals(commandValue2)) {
                if(td.getCommandAddress() != td.getCommandValue()){
                    try{run(NavaErrorCheck.checkLine(NavaCompiler.getBR().readLine()));}catch(Exception e){e.printStackTrace();} // run line
                } else {
                    try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
                }
            } else if ("GRTEQL".equals(commandValue2)) {
                if(td.getCommandAddress() >= td.getCommandValue()){
                    try{run(NavaErrorCheck.checkLine(NavaCompiler.getBR().readLine()));}catch(Exception e){e.printStackTrace();}
                } else {
                    try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
                }
            } else if ("LESEQL".equals(commandValue2)) {
                if(td.getCommandAddress() <= td.getCommandValue()){
                    try{run(NavaErrorCheck.checkLine(NavaCompiler.getBR().readLine()));}catch(Exception e){e.printStackTrace();}
                } else {
                    try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
                }
            } else if ("EQL".equals(commandValue2)) {
                if(td.getCommandAddress() == td.getCommandValue()){
                    try{run(NavaErrorCheck.checkLine(NavaCompiler.getBR().readLine()));}catch(Exception e){e.printStackTrace();}
                } else {
                    try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
                }
            } else if ("GRT".equals(commandValue2)) {
                if(td.getCommandAddress() > td.getCommandValue()){
                    try{run(NavaErrorCheck.checkLine(NavaCompiler.getBR().readLine()));}catch(Exception e){e.printStackTrace();}
                } else {
                    try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
                }
            } else if ("LES".equals(commandValue2)) {
                if(td.getCommandAddress() < td.getCommandValue()){
                    try{run(NavaErrorCheck.checkLine(NavaCompiler.getBR().readLine()));}catch(Exception e){e.printStackTrace();}
                } else {
                    try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
                }
            } else {
                try{
                    throw new Exception("Line: " + NavaCompiler.lineNumber +
                                        ", Program Started On Line: " + NavaCompiler.programStartLine + " has an incorrect or unknown comparison!\n" +
                                        "Error type: " + NavaCompiler.getErrorTypes()[3]);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        } else {
            try{
                throw new Exception("Line: " + NavaCompiler.lineNumber +
                                            ", Program Started On Line: " + NavaCompiler.programStartLine + " has an incorrect or unknown comparison!\n" +
                                            "Error type: " + NavaCompiler.getErrorTypes()[td.getErrorType()]);
            }catch(Exception e){e.printStackTrace();}
        }
    }    
}
