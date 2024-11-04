package Source.Utilities;

public class CustomParseInt {
    private String input;

    public CustomParseInt(String input) {
        this.input = input;
    }

    public int parseInt() {
        if (input == null || input.isEmpty()) {
            throw new NumberFormatException("Invalid input: " + input);
        }

        int result = 0;
        boolean isNegative = false;
        int i = 0;

        if (input.charAt(0) == '-') {
            isNegative = true;
            i++;
            if (i == input.length()) {
                throw new NumberFormatException("Invalid input: " + input);
            }
        }

        for (; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar < '0' || currentChar > '9') {
                throw new NumberFormatException("Invalid input: " + input);
            }
            result = result * 10 + (currentChar - '0');
        }

        return isNegative ? -result : result;
    }
}
