package Authentication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Login extends Authentication {
    private static final String USERS_FILE = "users.txt";
    private SignUp signUpInstance = new SignUp(); // Create a SignUp instance

    public void logIn() throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Log In");
        System.out.println("Enter email: ");
        String email = scanner.nextLine().trim();

        if (isEmailRegistered(email)) {
            System.out.println("Enter password: ");
            String password = getPasswordInput();
            String encrypted_pass = encryptPassword(password);
            if (isValidUser(email, encrypted_pass)) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid email or password. Try again.");
                logIn();
            }
        } else {
            // Email is not registered, prompt for signup
            System.out.println("You are not signed up with this email. Sign up first.");
            signUpInstance.signUp(); // Call the signUp method from SignUp class
        }

        scanner.close();
    }

    public boolean isEmailRegistered(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(", ");
                if (userDetails.length > 3 && userDetails[3].equalsIgnoreCase(email)) {
                    // Email is found
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while checking the email: " + e.getMessage());
        }
        // Email is not found
        return false;  
    }
}
