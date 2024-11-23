package Admin;

import Authentication.User;

public class Admin extends User {
    public Admin(String name, String phoneNumber, String email, String encryptedPassword) {
        super("Admin", name, phoneNumber, email, encryptedPassword);
    }
}