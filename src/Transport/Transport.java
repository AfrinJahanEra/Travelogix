package Transport;

import Authentication.User;

public class Transport extends User {
    public Transport(String name, String phoneNumber, String email, String encryptedPassword) {
        super("Transport", name, phoneNumber, email, encryptedPassword);
    }
}
