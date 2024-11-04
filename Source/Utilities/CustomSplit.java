package Source.Utilities;

public class CustomSplit {
    private CustomList<String> parts;

    public CustomSplit() {
        parts = new CustomList<>();
    }

    public void setInput(String input) {
        String currentPart = "";

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (currentChar == ',') {
                if (currentPart.length() > 0) {
                    parts.add(currentPart);
                    currentPart = "";
                }
            } else {
                currentPart += currentChar;
            }
        }

        if (currentPart.length() > 0) {
            parts.add(currentPart);
        }
    }

    public CustomList<String> getParts() {
        return parts;
    }
}
