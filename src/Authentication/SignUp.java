package Authentication;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SignUp extends Authentication {

    private static final String USERS_FILE = "src\\TXT_Files\\users.txt";
    private static final int MIN_PASSWORD_LENGTH = 8;
    private final AuthenticationDashboard authDashboard = new AuthenticationDashboard();

    // Main method to handle sign-up
    public void signUp() throws NoSuchAlgorithmException, IOException {
        printTitle("Sign Up");

        Scanner scanner = new Scanner(System.in);

        // Step 1: Role selection
        String role = getRoleSelection(scanner);
        if (role == null) return; // Exit if invalid selection

        // Step 2: Name input
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        // Step 3: Phone number validation
        String phoneNumber = getValidPhoneNumber(scanner);

        // Step 4: Email validation
        String email = getValidEmail(scanner);

        // Step 5: Password setup
        String password = getValidPassword(scanner);

        // Encrypt the password
        String encryptedPassword = encryptPassword(password);

        // Save user information
        saveUserInfo(role, name, phoneNumber, email, encryptedPassword);

        // Success message and dashboard display
        printSuccess("Sign Up successful!");
        authDashboard.displayDashboard(role);
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
