package Custom;

public class Customparseint {


    public static int parseint(String str) throws NumberFormatException {
        if (str == null || str.isEmpty()) {
            throw new NumberFormatException("Input string is null or empty");
        }

        int result = 0;
        boolean isNegative = false;
        int i = 0;


        if (str.charAt(0) == '-') {
            isNegative = true;
            i = 1;
        }


        while (i < str.length()) {
            char c = str.charAt(i);

            if (c >= '0' && c <= '9') {

                result = result * 10 + (c - '0');
            } else {

                throw new NumberFormatException("Invalid character found: " + c);
            }
            i++;
        }


        return isNegative ? -result : result;
    }
}
