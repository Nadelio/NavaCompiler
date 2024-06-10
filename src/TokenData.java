package src;
public class TokenData
{
    private boolean error = false;
    private int errorType;
    private String commandToken;
    private int commandAddress;
    private int commandValue;
    private String commandValue2;
    private int commandOutputAddress;
    private boolean isString;
    
    public TokenData(String token, int address, int value){
        this.commandToken = token;
        this.commandAddress = address;
        this.commandValue = value;
        this.isString = false;
    }

    public boolean getErrorStatus(){return this.error;}
    public int getErrorType(){return this.errorType;}
    public String getCommandToken(){return this.commandToken;}
    public int getCommandAddress(){return this.commandAddress;}
    public int getCommandValue(){return this.commandValue;}
    public String getCommandValue2(){return this.commandValue2;}
    public int getCommandOutputAddress(){return this.commandOutputAddress;}
    public boolean isString(){return this.isString;}

    public void setCommandAddress(int newCA){this.commandAddress = newCA;}
    public void setCommandValue(int newCV){this.commandValue = newCV;}
    public void setCommandValue(String newCV){this.commandValue2 = newCV; this.isString = true;}
    public void setCommandOutputAddress(int newCOA){this.commandOutputAddress = newCOA;}
    public void setError(int errorType){this.errorType = errorType; this.error = true;}
}
