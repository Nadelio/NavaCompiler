package src;
import java.util.Arrays;

public class NavaParser {
    public static TokenData checkLine(String line){
        String[] commands = NavaCompiler.cmds;

        if(!Arrays.asList(commands).contains(line.substring(0,3))){
            TokenData errorToken1 = new TokenData(line.substring(0, 3), -1, -1); // Unknown Command error
            errorToken1.setError(0);
            return errorToken1;
        } else if(line.contains("-")) {
            TokenData errorToken2 = new TokenData(line, -1, -1); // Negative Index error
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
            } else if (td.getCommandToken().equals("RPT")){
                int firstIndex = line.indexOf(",");
                if(firstIndex == -1){
                    td.setIndex(false);
                    td.setCommandValue(Integer.parseInt(line.substring(4, line.indexOf(")"))));
                } else {
                    td.setIndex(true);
                    td.setCommandValue(Integer.parseInt(line.substring(4,firstIndex)));
                    td.setCommandValue(line.substring(firstIndex+1, line.indexOf(")")));
                }
            } else if(td.getCommandToken().equals("DIV")||td.getCommandToken().equals("MUL")||td.getCommandToken().equals("ADD")||td.getCommandToken().equals("SUB")){
                int firstIndex = line.indexOf(",");
                int secondIndex = line.indexOf(",", firstIndex+1);
                td.setCommandAddress(Integer.parseInt(line.substring(4, firstIndex)));
                td.setCommandValue(Integer.parseInt(line.substring(firstIndex+1, secondIndex)));
                td.setCommandOutputAddress(Integer.parseInt(line.substring(secondIndex+1, line.indexOf(")"))));
            } else if(td.getCommandToken().equals("SIF")) {
                int firstIndex = line.indexOf(",");
                int secondIndex = line.indexOf(",", firstIndex+1);
                td.setCommandAddress(Integer.parseInt(line.substring(4, firstIndex)));
                td.setCommandValue(line.substring(firstIndex+1, secondIndex));
                td.setCommandValue((Integer.parseInt(line.substring(secondIndex+1, line.indexOf(")")))));
                if(!Arrays.asList(NavaCompiler.cmps).contains(td.getCommandValue2())){
                    td.setError(3); // Comparison Syntax Error
                }
            } else if(td.getCommandToken().equals("FUN")) {
                td.setCommandValue(line.substring(4, line.indexOf(")")));
            } else {
                td.setCommandAddress(Integer.parseInt(line.substring(4, line.indexOf(")"))));
            }
            return td;
        }
    }
}
