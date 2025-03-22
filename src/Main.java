
import Authentication.UserAccess;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        UserAccess userAccess = new UserAccess();
        userAccess.start();
    }
}
// 2025-03-19 03:35:00