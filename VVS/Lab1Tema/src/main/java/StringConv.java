

public class StringConv {

    public int strToInt(String str) throws NumberFormatException {
        int sum=0;
        for (int i = 0; i < str.length(); i++) {
            int digit = (int)str.charAt(str.length()-i-1) - (int)'0';
            sum = sum + digit * (int)Math.pow(10, i);
        }
        return sum;
    }

}