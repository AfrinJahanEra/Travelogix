package Authentication;

import Admin.Admin;
import Transport.Transport;
import Traveler.Traveler;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SignUp extends Authentication {

    private static final String USERS_FILE = "src\\TXT_Files\\users.txt";
    private static final int MIN_PASSWORD_LENGTH = 8;
    private final AuthenticationDashboard authDashboard = new AuthenticationDashboard();

    public void signUp() throws NoSuchAlgorithmException, IOException {
        printTitle("Sign Up");

        Scanner scanner = new Scanner(System.in);

        String role = getRoleSelection(scanner);
        if (role == null) return;

        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        String phoneNumber = getValidPhoneNumber(scanner);
        String email = getValidEmail(scanner);
        String password = getValidPassword(scanner);

        String encryptedPassword = encryptPassword(password);

        User user;
        switch (role) {
            case "Admin":
                user = new Admin(name, phoneNumber, email, encryptedPassword);
                break;
            case "Transport":
                user = new Transport(name, phoneNumber, email, encryptedPassword);
                break;
            case "Traveler":
                user = new Traveler(name, phoneNumber, email, encryptedPassword);
                break;
            default:
                printError("Invalid role selected.");
                return;
        }

        saveUserInfo(user);
        printSuccess("Sign Up successful!");
        authDashboard.displayDashboard(user.getRole());
    }

    // Helper method to select a role
    private String getRoleSelection(Scanner scanner) {
        System.out.println("\nPlease choose your role:");
        System.out.println("[1] Admin");
        System.out.println("[2] Traveler");
        System.out.println("[3] Transport Agency");
        System.out.print("Enter your choice (1/2/3): ");

        String roleInput = scanner.nextLine().trim();
        switch (roleInput) {
            case "1":
                return "Admin";
            case "2":
                return "Traveler";
            case "3":
                return "Transport";
            default:
                printError("Invalid role selection. Please enter 1, 2, or 3.");
                return null;
        }
    }

    // Helper method to validate phone number
    private String getValidPhoneNumber(Scanner scanner) {
        while (true) {
            System.out.print("Enter your phone number (11 digits starting with '01'): ");
            String phoneNumber = scanner.nextLine().trim();
            if (isValidPhoneNumber(phoneNumber)) {
                return phoneNumber;
            } else {
                printError("Invalid phone number. It must be 11 digits and start with '01'.");
            }
        }
    }

    // Helper method to validate email
    private String getValidEmail(Scanner scanner) {
        while (true) {
            System.out.print("Enter your email: ");
            String email = scanner.nextLine().trim();
            if (!isValidEmail(email)) {
                printError("Invalid email. Use '@gmail.com' or '@yahoo.com' with lowercase letters.");
            } else if (!isEmailUnique(email)) {
                printError("This email is already registered. Please try a different one.");
            } else {
                return email;
            }
        }
    }

    // Helper method to validate password
    private String getValidPassword(Scanner scanner) {
        while (true) {
            System.out.print("Enter your password (min 8 characters): ");
            String password = getPasswordInput();
            if (password.length() < MIN_PASSWORD_LENGTH) {
                printError("Password must be at least 8 characters long.");
            } else {
                System.out.print("Confirm your password: ");
                String confirmPassword = getPasswordInput();
                if (password.equals(confirmPassword)) {
                    return password;
                } else {
                    printError("Passwords do not match. Try again.");
                }
            }
        }
    }

    // Validate email format
    public boolean isValidEmail(String email) {
        return email.matches("[a-z0-9._%+-]+@(gmail\\.com|yahoo\\.com)");
    }

    // Validate phone number format
    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("01\\d{9}");
    }

    // Print formatted title
    private void printTitle(String title) {
        System.out.println("\n========================================");
        System.out.printf("  %s%n", title);
        System.out.println("========================================\n");
    }

    // Print success message
    private void printSuccess(String message) {
        System.out.println("\n[SUCCESS] " + message + "\n");
    }

    // Print error message
    private void printError(String message) {
        System.out.println("\n[ERROR] " + message + "\n");
    }
}
