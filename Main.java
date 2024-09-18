import Authentication.AuthService;
import Authentication.AuthenticationHandler;

public class Main {
    public static void main(String[] args) {
        AuthenticationHandler authHandler = new AuthenticationHandler();
        authHandler.start();
    }
}
