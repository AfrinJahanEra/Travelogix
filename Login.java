import java.io.*;
import java.util.*;

public class Login {
    private static final String USERS_FILE = "users.txt";
    private static final int MIN_PASSWORD_LENGTH = 8;

    // Method to sign up as Admin or User do the work to work
    public void signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sign Up");
        System.out.println("Role: \n1. Admin\n2. User\nEnter your role: ");
        String role = scanner.nextLine().trim();
        

        System.out.println("Enter name: ");
        String name = scanner.nextLine().trim();

        System.out.println("Enter phone number: ");
        String phoneNumber = scanner.nextLine().trim();

        String email = "";
        while (true) {
            System.out.println("Enter email: ");
            email = scanner.nextLine().trim();
            if (isEmailUnique(email)) {
                break;
            } else {
                System.out.println("This email is already registered. Please try a different one.");
            }
        }

        String password = "";
        while (true) {
            System.out.println("Enter password (minimum 8 characters): ");
            password = getPasswordInput();
            if (password.length() < MIN_PASSWORD_LENGTH) {
                System.out.println("Password must be at least 8 characters long.");
            } else {
                System.out.println("Confirm password: ");
                String confirmPassword = getPasswordInput();
                if (password.equals(confirmPassword)) {
                    break;
                } else {
                    System.out.println("Passwords do not match. Try again.");
                }
            }
        }

        // Save user information
        saveUserInfo(role, name, phoneNumber, email, password);
        System.out.println("Sign up successful!");

        scanner.close();
    }

    // Method to log in
    public void logIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Log In");
        System.out.println("Enter email: ");
        String email = scanner.nextLine().trim();
        System.out.println("Enter password: ");
        String password = getPasswordInput();

        if (isValidUser(email, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password.");
        }

        scanner.close();
    }

    // Method to check if the email is unique
    private boolean isEmailUnique(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length > 0 && userDetails[0].equalsIgnoreCase(email)) {
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the user file.");
        }
        return true;
    }

    // Method to save user information
    private void saveUserInfo(String role, String name, String phoneNumber, String email, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            if(role =="1") role= "Admin";
            else role ="User";
            writer.write(role + ", " + name + ", " + phoneNumber + ", " + email + ", " + password);
            writer.newLine();
        } catch (IOException e) {
            System.out.println(e + " occurred while saving user information.");
        }
    }

    // Method to check if the user credentials are valid
    private boolean isValidUser(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(", ");
                if (userDetails.length > 1 && userDetails[3].equalsIgnoreCase(email) && userDetails[4].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Invalid email or password.");
        }
        return false;
    }

    // Method to get password input with masking
    public String getPasswordInput() {
        /*Console console = System.console();
        if (console == null) {
            // If the console is not available (like in some IDEs), use Scanner as fallback
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine().trim();
        } else {
            char[] passwordArray = console.readPassword();
            return new String(passwordArray);
        }
    }*/
        Console console = System.console();

        if (console == null) {
            System.out.println("No console available");

        }

        // Prompt user to enter a password
        char[] passwordArray = console.readPassword(); // Masks the input with *

        // Convert char array to String
        String password = new String(passwordArray);

        // Display the entered password (for demonstration purposes)
        return password;
    }

    
}
