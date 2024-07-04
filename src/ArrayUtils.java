package src;

public class ArrayUtils
{
    public static TokenData[] add(TokenData[] arr, TokenData element) {
        TokenData[] newArray = new TokenData[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        newArray[arr.length] = element;
        return newArray;
    }

    public static String[] add(String[] arr, String element) {
        String[] newArray = new String[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        newArray[arr.length] = element;
        return newArray;
    }
}
