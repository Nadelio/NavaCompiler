package src;
public class NavaTokenToCommand
{

    public static void run(TokenData td){
        if(td.getCommandToken().equals("DEC")){
            NavaCompiler.compilerIntVariables[td.getCommandAddress()]--;
            if(NavaCompiler.compilerIntVariables[td.getCommandAddress()] < 0){NavaCompiler.compilerIntVariables[td.getCommandAddress()] = 0;}
        } else if(td.getCommandToken().equals("INC")){
            NavaCompiler.compilerIntVariables[td.getCommandAddress()]++;
        } else if(td.getCommandToken().equals("MOV")){
            NavaCompiler.compilerIntVariables[td.getCommandValue()] = NavaCompiler.compilerIntVariables[td.getCommandAddress()];
            NavaCompiler.compilerIntVariables[td.getCommandAddress()] = 0;
        } else if(td.getCommandToken().equals("SET")){
            NavaCompiler.compilerIntVariables[td.getCommandAddress()] = td.getCommandValue();
        } else if(td.getCommandToken().equals("OUT")){
            if(td.isString()){
                System.out.print("\n" + td.getCommandValue2());
            } else {
                System.out.print("\n" + NavaCompiler.compilerIntVariables[td.getCommandAddress()]);
            }
        } else if(td.getCommandToken().equals("ADD")){
            NavaCompiler.compilerIntVariables[td.getCommandOutputAddress()] = NavaCompiler.compilerIntVariables[td.getCommandAddress()] + NavaCompiler.compilerIntVariables[td.getCommandValue()];
        } else if(td.getCommandToken().equals("SUB")){
            NavaCompiler.compilerIntVariables[td.getCommandOutputAddress()] = NavaCompiler.compilerIntVariables[td.getCommandAddress()] - NavaCompiler.compilerIntVariables[td.getCommandValue()];
            if(NavaCompiler.compilerIntVariables[td.getCommandOutputAddress()] < 0){
                td.setError(1);
                try{
                    throw new Exception("Line: " + NavaCompiler.lineNumber +
                                        ", Program Started On Line: " + NavaCompiler.programStartLine + " has an incorrect or unknown command!\n" + 
                                        "Error type: " + NavaCompiler.getErrorTypes()[1]);
                }catch(Exception e) {e.printStackTrace();}
            }
        } else if(td.getCommandToken().equals("MUL")){
            NavaCompiler.compilerIntVariables[td.getCommandOutputAddress()] = NavaCompiler.compilerIntVariables[td.getCommandAddress()] * NavaCompiler.compilerIntVariables[td.getCommandValue()];
        } else if(td.getCommandToken().equals("DIV")){
            NavaCompiler.compilerIntVariables[td.getCommandOutputAddress()] = NavaCompiler.compilerIntVariables[td.getCommandAddress()] / NavaCompiler.compilerIntVariables[td.getCommandValue()];
        } else if(td.getCommandToken().equals("SIF")){
            String commandValue2 = td.getCommandValue2();
            if ("NEQL".equals(commandValue2)) {
                if(NavaCompiler.compilerIntVariables[td.getCommandAddress()] != td.getCommandValue()){
                    try{
                        String line = NavaCompiler.getBR().readLine();
                        run(NavaErrorCheck.checkLine(line));
                    }catch(Exception e){e.printStackTrace();} // run line
                } else {
                    try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
                }
            } else if ("GRTEQL".equals(commandValue2)) {
                if(NavaCompiler.compilerIntVariables[td.getCommandAddress()] >= td.getCommandValue()){
                    try{
                        String line = NavaCompiler.getBR().readLine();
                        run(NavaErrorCheck.checkLine(line));
                    }catch(Exception e){e.printStackTrace();}
                } else {
                    try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
                }
            } else if ("LESEQL".equals(commandValue2)) {
                if(NavaCompiler.compilerIntVariables[td.getCommandAddress()] <= td.getCommandValue()){
                    try{
                        String line = NavaCompiler.getBR().readLine();
                        run(NavaErrorCheck.checkLine(line));
                    }catch(Exception e){e.printStackTrace();}
                } else {
                    try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
                }
            } else if ("EQL".equals(commandValue2)) {
                if(NavaCompiler.compilerIntVariables[td.getCommandAddress()] == td.getCommandValue()){
                    try{
                        String line = NavaCompiler.getBR().readLine();
                        run(NavaErrorCheck.checkLine(line));
                    }catch(Exception e){e.printStackTrace();}
                } else {
                    try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
                }
            } else if ("GRT".equals(commandValue2)) {
                if(NavaCompiler.compilerIntVariables[td.getCommandAddress()] > td.getCommandValue()){
                    try{
                        String line = NavaCompiler.getBR().readLine();
                        run(NavaErrorCheck.checkLine(line));
                    }catch(Exception e){e.printStackTrace();}
                } else {
                    try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
                }
            } else if ("LES".equals(commandValue2)) {
                if(NavaCompiler.compilerIntVariables[td.getCommandAddress()] < td.getCommandValue()){
                    try{
                        String line = NavaCompiler.getBR().readLine();
                        run(NavaErrorCheck.checkLine(line));
                    }catch(Exception e){e.printStackTrace();}
                } else {
                    try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
                }
            } else {
                try{
                    throw new Exception("Line: " + NavaCompiler.lineNumber +
                                        ", Program Started On Line: " + NavaCompiler.programStartLine + " has an incorrect or unknown comparison.\n" +
                                        "Error type: " + NavaCompiler.getErrorTypes()[3]);
                }catch(Exception e){e.printStackTrace();}
            }
        } /*else if(td.getCommandToken().equals("FUN")){
            NavaFunction nf = NavaCompiler.functions.get(td.getCommandValue2());
            TokenData[] tokens = nf.getCommands();
            for(TokenData token : tokens){
                NavaTokenToCommand.run(token);
            }
        }
        */else {
            try{
                throw new Exception("Line: " + NavaCompiler.lineNumber +
                                            ", Program Started On Line: " + NavaCompiler.programStartLine + " has an incorrect or unknown command.\n" +
                                            "Error type: " + NavaCompiler.getErrorTypes()[0]);
            }catch(Exception e){e.printStackTrace();}
        }
    }    
}
