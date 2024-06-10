public class TokenData
{
    private boolean error = false;
    private int errorType;
    private String commandToken;
    private int commandAddress;
    private int commandValue;
    
    public TokenData(String token, int address, int value){
        this.commandToken = token;
        this.commandAddress = address;
        this.commandValue = value;
    }

    public boolean getErrorStatus(){return this.error;}
    public int getErrorType(){return this.errorType;}
    public String getCommandToken(){return this.commandToken;}
    public int getCommandAddress(){return this.commandAddress;}
    public int getCommandValue(){return this.commandValue;}

    public void setCommandAddress(int newCA){this.commandAddress = newCA;}
    public void setCommandValue(int newCV){this.commandValue = newCV;}
    public void setError(int errorType){this.errorType = errorType;}
}
