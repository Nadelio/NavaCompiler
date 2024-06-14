package src;

public class NavaFunction
{
    private TokenData[] commands;
    private String functionName;

    public NavaFunction(TokenData[] commands, String functionName){
        this.commands = commands;
        this.functionName = functionName;
    }

    public TokenData[] getCommands(){return this.commands;}
    public String getName(){return this.functionName;}

    public void setCommands(TokenData[] newCommands){this.commands = newCommands;}
    public void setName(String newName){this.functionName = newName;}

    public String cmdToString(){
        String result = "Commands: [";
        result += "(Token: " + this.commands[0].getCommandToken() + 
                  " Address: " + this.commands[0].getCommandAddress() +
                  " Value_1: " + this.commands[0].getCommandValue() +
                  " Value_2: " + this.commands[0].getCommandValue2() + 
                  " Output_Address: " + this.commands[0].getCommandOutputAddress() +
                  " Error_Type: " + this.commands[0].getErrorType() +
                  " Error_Status: " + this.commands[0].getErrorStatus() +
                  ")";
        for(int i = 1; i < this.commands.length; i++){
            result += ", (Token: " + this.commands[i].getCommandToken() + 
                      " Address: " + this.commands[i].getCommandAddress() +
                      " Value_1: " + this.commands[i].getCommandValue() +
                      " Value_2: " + this.commands[i].getCommandValue2() + 
                      " Output_Address: " + this.commands[i].getCommandOutputAddress() +
                      " Error_Type: " + this.commands[i].getErrorType() +
                      " Error_Status: " + this.commands[i].getErrorStatus() +
                      ")";
        }
        return result + "]";
    }

    public String toString(){
        return "Function: " + this.functionName + "\n" + cmdToString();
    }
}
