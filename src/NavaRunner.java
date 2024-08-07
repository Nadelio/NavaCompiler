package src;

public class NavaRunner
{
    public static int[] IntVariables = NavaRuntime.compilerIntVariables;

    public static void run(TokenData td){
        String commandToken = td.getCommandToken();
        int commandAddress = td.getCommandAddress();
        int commandValue1 = td.getCommandValue();
        int commandOutputAddress = td.getCommandOutputAddress();

        if(commandToken.equals("DEC")){
            IntVariables[commandAddress]--;
            if(IntVariables[commandAddress] < 0){IntVariables[commandAddress] = 0;}
        } else if(commandToken.equals("INC")){
            IntVariables[commandAddress]++;
        } else if(commandToken.equals("MOV")){
            IntVariables[commandValue1] = IntVariables[commandAddress];
            IntVariables[commandAddress] = 0;
        } else if(commandToken.equals("SET")){
            IntVariables[commandAddress] = commandValue1;
        } else if(commandToken.equals("OUT")){
            if(td.isString()){System.out.print("\n" + td.getCommandValue2());}
            else {System.out.print("\n" + IntVariables[commandAddress]);}
        } else if(commandToken.equals("ADD")){
            IntVariables[commandOutputAddress] = IntVariables[commandAddress] + IntVariables[commandValue1];
        } else if(commandToken.equals("SUB")){
            td = SUB(td);
        } else if(commandToken.equals("MUL")){
            IntVariables[commandOutputAddress] = IntVariables[commandAddress] * IntVariables[commandValue1];
        } else if(commandToken.equals("DIV")){
            IntVariables[commandOutputAddress] = IntVariables[commandAddress] / IntVariables[commandValue1];
        } else if(commandToken.equals("SIF")){
            td = SIF(td);
        } else if(commandToken.equals("FUN")){
            td = FUN(td);
        } else if(commandToken.equals("RPT")){
            td = RPT(td);
        } else if(commandToken.equals("DLY")){
            try{Thread.sleep(commandAddress * 1000);}catch(Exception e){e.printStackTrace();}
        } else {
            try{
                throw new Exception("Line: " + NavaCompiler.lineNumber +
                                            ", Program Started On Line: " + NavaCompiler.programStartLine + " has an incorrect or unknown command.\n" +
                                            "Error type: " + NavaCompiler.getErrorTypes()[0]);
            }catch(Exception e){e.printStackTrace();}
        }

        updateHooks();
    }

    public static TokenData SUB(TokenData td)
    {
        int commandAddress = td.getCommandAddress();
        int commandValue1 = td.getCommandValue();
        int commandOutputAddress = td.getCommandOutputAddress();
        IntVariables[commandOutputAddress] = IntVariables[commandAddress] - IntVariables[commandValue1];
        if(IntVariables[commandOutputAddress] < 0){
            td.setError(1);
            try{
                throw new Exception("Line: " + NavaCompiler.lineNumber +
                                    ", Program Started On Line: " + NavaCompiler.programStartLine + " has an incorrect or unknown command!\n" + 
                                    "Error type: " + NavaCompiler.getErrorTypes()[1]);
            }catch(Exception e) {e.printStackTrace();}
        }
        return td;
    }

    public static TokenData SIF(TokenData td)
    {
        int commandAddress = td.getCommandAddress();
        int commandValue1 = td.getCommandValue();
        String commandValue2 = td.getCommandValue2();
        if ("NEQL".equals(commandValue2)) {
            if(IntVariables[commandAddress] != commandValue1){
                try{
                    String line = NavaCompiler.getBR().readLine();
                    run(NavaParser.checkLine(line)); // run line
                }catch(Exception e){e.printStackTrace();}
            } else {
                try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
            }
        } else if ("GRTEQL".equals(commandValue2)) {
            if(IntVariables[commandAddress] >= commandValue1){
                try{
                    String line = NavaCompiler.getBR().readLine();
                    run(NavaParser.checkLine(line));
                }catch(Exception e){e.printStackTrace();}
            } else {
                try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
            }
        } else if ("LESEQL".equals(commandValue2)) {
            if(IntVariables[commandAddress] <= commandValue1){
                try{
                    String line = NavaCompiler.getBR().readLine();
                    run(NavaParser.checkLine(line));
                }catch(Exception e){e.printStackTrace();}
            } else {
                try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
            }
        } else if ("EQL".equals(commandValue2)) {
            if(IntVariables[commandAddress] == commandValue1){
                try{
                    String line = NavaCompiler.getBR().readLine();
                    run(NavaParser.checkLine(line));
                }catch(Exception e){e.printStackTrace();}
            } else {
                try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
            }
        } else if ("GRT".equals(commandValue2)) {
            if(IntVariables[commandAddress] > commandValue1){
                try{
                    String line = NavaCompiler.getBR().readLine();
                    run(NavaParser.checkLine(line));
                }catch(Exception e){e.printStackTrace();}
            } else {
                try{NavaCompiler.getBR().readLine();}catch(Exception e){e.printStackTrace();} // skip line
            }
        } else if ("LES".equals(commandValue2)) {
            if(IntVariables[commandAddress] < commandValue1){
                try{
                    String line = NavaCompiler.getBR().readLine();
                    run(NavaParser.checkLine(line));
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
        return td;
    }

    public static TokenData FUN(TokenData td)
    {
        String commandValue2 = td.getCommandValue2();
        NavaFunction nf = NavaCompiler.functions.get(commandValue2);
        TokenData[] tokens = nf.getCommands();
        for(TokenData token : tokens){
            NavaRunner.run(token);
        }
        return td;
    }

    public static TokenData RPT(TokenData td){
        try{
            String line = NavaCompiler.getBR().readLine();
            TokenData td2 = NavaParser.checkLine(line);
            for(int i = 0; i <= td.getCommandValue(); i++){
                run(td2);
            }
        } catch(Exception e){e.printStackTrace();}
        return td;
    }

    public static void updateHooks(){
        if(NavaRuntime.getHookRegister().isEmpty() || NavaRuntime.getValidHooks() == null){return;}
        for(String hook : NavaRuntime.getValidHooks()){
            NavaRuntime.getHookRegister().get(hook).process();
        }
    }
}