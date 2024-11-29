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

    public void logIn() throws NoSuchAlgorithmException, IOException {
        printTitle("LOG IN");
    
        Scanner scanner = new Scanner(System.in);
    
        // Prompt user for email
        System.out.printf("%-20s: ", "Enter your email");
        String email = scanner.nextLine().trim();
    
        if (isEmailRegistered(email)) {
            // Prompt user for password
            System.out.printf("%-20s: ", "Enter your password");
            String password = getPasswordInput();
            String encryptedPass = encryptPassword(password);
    
            if (isValidUser(email, encryptedPass)) {
                printSuccess("Login successful!");
    
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
            signUpInstance.signUp();
        }
    }
    

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
        return false; 
    }

    private String getUserRole(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(", ");
                if (userDetails.length > 3 && userDetails[3].equalsIgnoreCase(email)) {
                    return userDetails[0].trim();
                }
            }
        } catch (IOException e) {
            printError("An error occurred while retrieving the user role: " + e.getMessage());
        }
        return null;
    }

    private void printTitle(String title) {

        waitForEnterKey();
        clearTerminal();
        System.out.printf("\n═══════════════════════ %s ═════════════════════════\n", title);
    }
    
    
    private void printSuccess(String message) {
        System.out.println("\n[SUCCESS] " + message + "\n");
    }

    private void printError(String message) {
        System.out.println("\n[ERROR] " + message + "\n");
    }

    private void waitForEnterKey() {
        System.out.println("\nPress ENTER to continue...");
        Scanner enterScanner = new Scanner(System.in);
        enterScanner.nextLine();
    }

    private void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to clear terminal.");
        }
    }
}
