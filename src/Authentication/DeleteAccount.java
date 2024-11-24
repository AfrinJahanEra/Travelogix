package Authentication;

import Transport.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class DeleteAccount extends Authentication {

    private static final String USERS_FILE = "src\\TXT_Files\\users.txt";
    private static final String TEMP_FILE = "src\\TXT_Files\\temp_users.txt"; // Temporary file for deletion operation
    private final Login loginHelper = new Login(); // Use LogIn for email validation

    // Method to delete the account
    public boolean deleteAccount() throws NoSuchAlgorithmException, IOException {
        printTitle("DELETE ACCOUNT");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your email: ");
        String email = scanner.nextLine().trim();

        // Check if email is registered
        if (!loginHelper.isEmailRegistered(email)) {
            printError("This email is not registered. Please check and try again.");
            return false;
        }

        System.out.print("Enter your password: ");
        String password = getPasswordInput();
        String encryptedPassword = encryptPassword(password);

        // Validate user credentials
        if (!isValidUser(email, encryptedPassword)) {
            printError("Invalid email or password. Please try again.");
            return false;
        }

        // Retrieve user role
        String role = getUserRole(email);
        if ("Transport".equalsIgnoreCase(role)) {
            new TransportDeleteAccount().sendDeleteRequest(email); // Delegate deletion to Transport system
        } else {
            System.out.println("\nAre you sure you want to delete your account?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            if ("1".equals(choice)) {
                if (performDeletion(email)) {
                    printSuccess("Account deleted successfully.");
                    return true;
                } else {
                    printError("An error occurred while deleting the account. Please try again.");
                    return false;
                }
            } else {
                printInfo("Account deletion cancelled.");
                return false;
            }
        }
        return false;
    }

    // Retrieve user role by email
    public String getUserRole(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(", ");
                if (userDetails.length > 3 && userDetails[3].equalsIgnoreCase(email)) {
                    return userDetails[0]; // Assuming role is the first field
                }
            }
        } catch (IOException e) {
            printError("An error occurred while retrieving the user's role.");
        }
        return null; // Return null if role not found
    }

    // Perform the deletion operation
    private boolean performDeletion(String email) {
        boolean isDeleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(", ");
                if (userDetails.length > 3 && !userDetails[3].equalsIgnoreCase(email)) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    isDeleted = true; // Mark as deleted
                }
            }
        } catch (IOException e) {
            printError("An error occurred while processing the user file.");
            return false;
        }

        // Replace the original file with the updated one
        if (isDeleted) {
            File originalFile = new File(USERS_FILE);
            File tempFile = new File(TEMP_FILE);

            if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                return true;
            } else {
                printError("Failed to update the user file.");
                return false;
            }
        } else {
            new File(TEMP_FILE).delete(); // Clean up temp file
        }

        return isDeleted;
    }

    // Utility methods for standardized output
    private void printTitle(String title) {
        System.out.printf("\n════════════════════ %s ══════════════════════\n", title);
    }
    

    private void printError(String message) {
        System.out.println("\n[ERROR] " + message + "\n");
    }

    private void printInfo(String message) {
        System.out.println("\n[INFO] " + message + "\n");
    }

    private void printSuccess(String message) {
        System.out.println("\n[SUCCESS] " + message + "\n");
    }
}
