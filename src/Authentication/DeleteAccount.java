package Authentication;

import Transport.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class DeleteAccount extends Authentication {

    private static final String USERS_FILE = "src\\TXT_Files\\users.txt";
    private static final String TEMP_FILE = "src\\TXT_Files\\temp_users.txt"; 
    private final Login loginHelper = new Login(); 

    public boolean deleteAccount() throws NoSuchAlgorithmException, IOException {
        printTitle("DELETE ACCOUNT");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your email: ");
        String email = scanner.nextLine().trim();

        if (!loginHelper.isEmailRegistered(email)) {
            printError("This email is not registered. Please check and try again.");
            return false;
        }

        System.out.print("Enter your password: ");
        String password = getPasswordInput();
        String encryptedPassword = encryptPassword(password);

        if (!isValidUser(email, encryptedPassword)) {
            printError("Invalid email or password. Please try again.");
            return false;
        }

        String role = getUserRole(email);
        if ("Transport".equalsIgnoreCase(role)) {
            new TransportDeleteAccount().sendDeleteRequest(email);
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

    public String getUserRole(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(", ");
                if (userDetails.length > 3 && userDetails[3].equalsIgnoreCase(email)) {
                    return userDetails[0];
                }
            }
        } catch (IOException e) {
            printError("An error occurred while retrieving the user's role.");
        }
        return null;
    }

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
                    isDeleted = true;
                }
            }
        } catch (IOException e) {
            printError("An error occurred while processing the user file.");
            return false;
        }

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
            new File(TEMP_FILE).delete(); 
        }

        return isDeleted;
    }

    private void printTitle(String title) {
        waitForEnterKey();
        clearTerminal();
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
