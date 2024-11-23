package Traveler;

import Authentication.User;

public class Traveler extends User {
    public Traveler(String name, String phoneNumber, String email, String encryptedPassword) {
        super("Traveler", name, phoneNumber, email, encryptedPassword);
    }
}