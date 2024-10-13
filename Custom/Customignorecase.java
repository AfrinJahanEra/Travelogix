package Custom;

public class Customignorecase {


    public static boolean equalIgnoreCase(String str1, String str2) {

        if (str1 == null && str2 == null) {
            return true;
        }


        if (str1 == null || str2 == null) {
            return false;
        }

        if (str1.length() != str2.length()) {
            return false;
        }


        for (int i = 0; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);


            if (toLowerCase(c1) != toLowerCase(c2)) {
                return false;
            }
        }


        return true;
    }


    private static char toLowerCase(char c) {

        if (c >= 'A' && c <= 'Z') {
            return (char) (c + ('a' - 'A'));
        }

        return c;
    }
}

