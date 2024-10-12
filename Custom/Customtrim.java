package Custom;

public class Customtrim {

    public String customtrim(String input) {
        int start = 0;
        int end = input.length() - 1;

        // Find the first non-space character from the beginning
        while (start <= end && input.charAt(start) == ' ') {
            start++;
        }

        // Find the last non-space character from the end
        while (end >= start && input.charAt(end) == ' ') {
            end--;
        }

        // Build the result manually (substring equivalent)
        StringBuilder result = new StringBuilder();
        for (int i = start; i <= end; i++) {
            result.append(input.charAt(i));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Customtrim customTrim = new Customtrim();
        String input = "    i am ridika   ";
        String trimmed = customTrim.customtrim(input);

        System.out.println("Original: '" + input + "'");
        System.out.println("Trimmed: '" + trimmed + "'");
    }
}

