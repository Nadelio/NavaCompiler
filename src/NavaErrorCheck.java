package src;
import java.util.Arrays;

public class NavaErrorCheck {
    public static TokenData checkLine(String line){
        String[] commands = NavaCompiler.cmds;

        if(!Arrays.asList(commands).contains(line.substring(0,3))){
            TokenData errorToken1 = new TokenData(line.substring(0, 3), -1, -1); // unknown command error
            errorToken1.setError(0);
            return errorToken1;
        } else if(line.contains("-")) {
            TokenData errorToken2 = new TokenData(line, -1, -1); // negative number error
            errorToken2.setError(1);
            return errorToken2;
        } else {
            TokenData td = new TokenData(line.substring(0, 3), -1, -1);
            if(td.getCommandToken().equals("SET") || td.getCommandToken().equals("MOV")){
                td.setCommandAddress(Integer.parseInt(line.substring(4, line.indexOf(","))));
                td.setCommandValue(Integer.parseInt(line.substring(line.indexOf(",")+1, line.indexOf(")"))));
            } else if(td.getCommandToken().equals("OUT")){
                String value = line.substring(4, line.indexOf(")"));
                try{
                    td.setCommandAddress(Integer.parseInt(value));
                } catch(NumberFormatException e){
                    td.setCommandValue(value);
                }   
            } else {
                td.setCommandAddress(Integer.parseInt(line.substring(4, line.indexOf(")"))));
            }
            return td;
        }
    }
}
