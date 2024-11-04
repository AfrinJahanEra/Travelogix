package Source.Utilities;

public class CustomTrim {
    private String input;

    public CustomTrim(String input) {
        this.input = input;
    }

    public String trim() {
        int start = 0;
        int end = input.length();


        while (start < end && input.charAt(start) == ' ') {
            start++;
        }


        while (end > start && input.charAt(end - 1) == ' ') {
            end--;
        }

        char[] trimmedChars = new char[end - start];
        for (int i = start; i < end; i++) {
            trimmedChars[i - start] = input.charAt(i);
        }


        String result = "";
        for (char c : trimmedChars) {
            result += c;
        }

        return result;
    }
}
