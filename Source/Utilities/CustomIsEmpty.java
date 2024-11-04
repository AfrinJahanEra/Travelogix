package Source.Utilities;

public class CustomIsEmpty {

    public boolean isEmpty(String s) {
        if (s == null) {
            return true;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }
}
