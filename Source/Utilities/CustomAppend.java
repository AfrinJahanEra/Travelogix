package Source.Utilities;

public class CustomAppend {
    private String result;

    public CustomAppend() {
        result = "";
    }

    public void append(String str) {
        if (str != null) {
            result += str;
        }
    }

    public String getResult() {
        return result;
    }
}