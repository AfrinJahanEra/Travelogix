package Authentication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Login extends Authentication {

    private static final String USERS_FILE = "src\\TXT_Files\\users.txt";
    private final SignUp signUpInstance = new SignUp();
    private final AuthenticationDashboard authDashboard = new AuthenticationDashboard();

    // Main login method
    public void logIn() throws NoSuchAlgorithmException, IOException {
        printTitle("LOG IN");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your email: ");
        String email = scanner.nextLine().trim();

        if (isEmailRegistered(email)) {
            System.out.print("Enter your password: ");
            String password = getPasswordInput();
            String encryptedPass = encryptPassword(password);

            if (isValidUser(email, encryptedPass)) {
                printSuccess("Login successful!");

                // Retrieve user role and display the appropriate dashboard
                String role = getUserRole(email);
                if (role != null) {
                    authDashboard.displayDashboard(role);
                } else {
                    printError("User role not found or invalid. Please contact support.");
                }
            } else {
                printError("Invalid email or password. Please try again.");
            }
        } else {
            printError("Email not registered. You need to sign up first.");
            signUpInstance.signUp(); // Redirect to sign-up
        }
    }

    // Check if an email is registered in the system
    public boolean isEmailRegistered(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(", ");
                if (userDetails.length > 3 && userDetails[3].equalsIgnoreCase(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            printError("An error occurred while checking the email: " + e.getMessage());
        }
        return false; // Email not found
    }

    // Retrieve the user role based on their email
    private String getUserRole(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(", ");
                if (userDetails.length > 3 && userDetails[3].equalsIgnoreCase(email)) {
                    return userDetails[0].trim(); // Role is the first item in the line
                }
            }
        } catch (IOException e) {
            printError("An error occurred while retrieving the user role: " + e.getMessage());
        }
        return null; // Role not found
    }

    // Print formatted title
    // private void printTitle(String title) {
    //     System.out.println("\n========================================");
    //     System.out.printf("  %s%n", title);
    //     System.out.println("========================================");
    // }

    private void printTitle(String title) {
        System.out.printf("\n════════════════════ %s ══════════════════════\n", title);
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
