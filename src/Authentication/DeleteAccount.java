package Authentication;

import Transport.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class DeleteAccount extends Authentication {

    private static final String USERS_FILE = "C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\users.txt";
    private static final String TEMP_FILE = "C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\temp_users.txt"; // Temporary file for deletion operation
    private Login loginHelper = new Login(); // Create an instance of LogIn to use isEmailRegistered

    // Method to delete the account
    public void deleteAccount() throws NoSuchAlgorithmException {

        System.out.println(" ________________________________");
        System.out.println("|         DELETE ACCOUNT         |");
        System.out.println("|________________________________|");

        Scanner scanner = new Scanner(System.in);
      
        System.out.println("Enter your email: ");
        String email = scanner.nextLine().trim();

        // Check if the email is registered using LogIn's isEmailRegistered
        if (!loginHelper.isEmailRegistered(email)) {
            System.out.println("This email is not registered. Please check the email and try again.");
            deleteAccount();
            return;
        }

        System.out.println("Enter your password: ");
        String password = getPasswordInput();
        String encryptedPassword = encryptPassword(password);

        // Check if the email and password are valid
        if (!isValidUser(email, encryptedPassword)) {
            System.out.println("Invalid email or password. Please try again.");
            return;
        }

        String role = getUserRole(email); // Retrieve the user role based on the email
        if ("Transport".equalsIgnoreCase(role)) {
            TransportDeleteAccount request = new TransportDeleteAccount();
            request.sendDeleteRequest(email);
        } else {
            System.out.println("Are you sure you want to delete your account? \n1. Yes \n2. No");
            String choice = scanner.nextLine().trim();

            if ("1".equals(choice)) {
                if (performDeletion(email)) {
                    System.out.println("Account deleted successfully.");
                } else {
                    System.out.println("An error occurred while deleting the account. Please try again.");
                }
            } else {
                System.out.println("Account deletion cancelled.");
            }
        }
        scanner.close();
    }

    // Method to retrieve user role by email
    private String getUserRole(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(", ");
                
                // Assuming the role is the first element and email is the fourth element
                if (userDetails.length > 3 && userDetails[3].equalsIgnoreCase(email)) {
                    return userDetails[0]; // Returns the role if email matches
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while retrieving the user's role.");
        }
        
        return null; // Returns null if the user is not found
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
                    // Write all users except the one to be deleted to the temporary file
                    writer.write(line);
                    writer.newLine();
                } else {
                    // Mark as deleted
                    isDeleted = true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while processing the user file.");
            return false;
        }

        // Replace the original file with the temporary file
        if (isDeleted) {
            File originalFile = new File(USERS_FILE);
            File tempFile = new File(TEMP_FILE);

            if (originalFile.delete()) {
                if (!tempFile.renameTo(originalFile)) {
                    System.out.println("Failed to update the user file.");
                    return false;
                }
            } else {
                System.out.println("Failed to delete the original user file.");
                return false;
            }
        } else {
            // If no deletion occurred, delete the temporary file
            new File(TEMP_FILE).delete();
        }

        return isDeleted;
    }
}
