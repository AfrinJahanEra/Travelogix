package Source.Utilities;

public class CustomUpperCase {
    private CustomList<Character> input;

    public CustomUpperCase(CustomList<Character> input) {
        this.input = input;
    }

    public String toUpperCase() {
        String result = "";

        for (int i = 0; i < input.size(); i++) {
            char currentChar = input.get(i);

            if (currentChar >= 'a' && currentChar <= 'z') {
                result += (char) (currentChar - 32);
            } else {
                result += currentChar;
            }
        }

        return result;
    }
}
