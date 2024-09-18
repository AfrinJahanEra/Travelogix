package Draft;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Authentication {
    private FileManager fileManager = new FileManager();
    private Scanner scanner = new Scanner(System.in);

    public void signUp() {
        System.out.println("Sign Up");

        // Email validation
        String email;
        do {
            System.out.print("Enter your email (example: user@gmail.com): ");
            email = scanner.nextLine();
        } while (!isValidEmail(email));

        // Password validation
        String password;
        String confirmPassword;
        do {
            System.out.print("Enter your password (8 or more characters): ");
            password = hidePassword();
            System.out.print("Confirm your password: ");
            confirmPassword = hidePassword();
        } while (!isValidPassword(password, confirmPassword));

        // Contact number validation
        String contactNumber;
        do {
            System.out.print("Enter your contact number (max 11 digits): ");
            contactNumber = scanner.nextLine();
        } while (!isValidContactNumber(contactNumber));

        // Save user to file
        fileManager.saveUser(email, password, contactNumber, "User");
        System.out.println("Sign up successful! You can now login.");
    }

    public String login() {
        System.out.println("Login");

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        String[] userDetails = fileManager.getUser(email);

        if (userDetails == null) {
            System.out.println("Email not found. Please sign up.");
            return null;
        } else {
            System.out.print("Enter your password: ");
            String password = hidePassword();
            if (password.equals(userDetails[1])) {
                System.out.println("Login successful! Welcome.");
                return userDetails[3]; // Return role (User, TransportAgency, or Admin)
            } else {
                System.out.println("Incorrect password.");
                return null;
            }
        }
    }

    // Email format validation
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }

    // Password validation
    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() >= 8 && password.equals(confirmPassword)) {
            return true;
        } else {
            System.out.println("Passwords do not match or are less than 8 characters.");
            return false;
        }
    }

    // Contact number validation
    private boolean isValidContactNumber(String contactNumber) {
        if (contactNumber.matches("\\d{1,11}")) {
            return true;
        } else {
            System.out.println("Invalid contact number.");
            return false;
        }
    }

    // Hide password input
    private String hidePassword() {
        return new String(System.console().readPassword());
    }
}