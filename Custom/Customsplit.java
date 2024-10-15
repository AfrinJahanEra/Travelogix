package Custom;

public class Customsplit {
    private String[] parts;
    private int size = 0;

    public Customsplit() {
        parts = new String[0];
    }

    public void setInput(String input) {
        int inputLength = input.length();
        StringBuilder currentPart = new StringBuilder();


        parts = new String[10];
        size = 0;

        for (int i = 0; i < inputLength; i++) {
            char currentChar = input.charAt(i);


            if (currentChar != ',') {
                currentPart.append(currentChar);
            } else {

                addPart(currentPart.toString().trim());
                currentPart.setLength(0);  // Reset for next part
            }
        }


        if (currentPart.length() > 0) {
            addPart(currentPart.toString().trim());
        }
    }

    private void addPart(String part) {

        if (size == parts.length) {
            resizeArray();
        }
        parts[size++] = part;
    }

    public String[] getParts() {

        String[] result = new String[size];
        System.arraycopy(parts, 0, result, 0, size);
        return result;
    }

    private void resizeArray() {
        int newCapacity = parts.length * 2;
        String[] newArray = new String[newCapacity];
        System.arraycopy(parts, 0, newArray, 0, parts.length);
        parts = newArray;
    }

    public static void main(String[] args) {
        Customsplit customSplit = new Customsplit();

        String input = "i,am,ridika";
        customSplit.setInput(input);

        String[] result = customSplit.getParts();

        for (String part : result) {
            System.out.println(part);
        }
    }
}
