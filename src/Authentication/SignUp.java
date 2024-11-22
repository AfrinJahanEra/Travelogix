package Authentication;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SignUp {

    private static final String USERS_FILE = "C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\users.txt";
    private static final int MIN_PASSWORD_LENGTH = 8;
    private AuthenticationDashboard authDashboard = new AuthenticationDashboard();
    private Authentication auth = new Authentication();

    // Method to sign up as Admin, Traveler, or Transport Agency
    public void signUp() throws NoSuchAlgorithmException, IOException {

        System.out.println(" ________________________________");
        System.out.println("|            SIGN UP             |");
        System.out.println("|________________________________|");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Role: \n1. Admin\n2. Traveler\n3. Transport Agency\nEnter your role: ");
        String roleInput = scanner.nextLine().trim();
        String role;

        switch (roleInput) {
            case "1":
                role = "Admin";
                break;
            case "2":
                role = "Traveler";
                break;
            case "3":
                role = "Transport";
                break;
            default:
                System.out.println("Invalid role selection. Please enter 1, 2, or 3.");
                return;
        }

        System.out.println("Enter name: ");
        String name = scanner.nextLine().trim();

        String phoneNumber;
        while (true) {
            System.out.println("Enter phone number: ");
            phoneNumber = scanner.nextLine().trim();
            if (isValidPhoneNumber(phoneNumber)) {
                break;
            } else {
                System.out.println("Invalid phone number. It must be 11 digits and start with '01'.");
            }
        }

        String email;
        while (true) {
            System.out.println("Enter email: ");
            email = scanner.nextLine().trim();

            if (!isValidEmail(email)) {
                System.out.println("Invalid email. It must contain '@gmail.com' or '@yahoo.com', and only lowercase letters. Try again.");
            } else if (!auth.isEmailUnique(email)) {
                System.out.println("This email is already registered. Please try a different one.");
            } else {
                break; // Valid, unique, and lowercase email
            }
        }

        String password;
        while (true) {
            System.out.println("Enter password (Password must be at least 8 characters long): ");
            password = auth.getPasswordInput();
            if (password.length() < MIN_PASSWORD_LENGTH) {
                System.out.println("Password must be at least 8 characters long.");
            } else {
                System.out.println("Confirm password: ");
                String confirmPassword = auth.getPasswordInput();
                if (password.equals(confirmPassword)) {
                    break;
                } else {
                    System.out.println("Passwords do not match. Try again.");
                }
            }
        }

        String encryptedPass = auth.encryptPassword(password);

        // Save user information with role as a string
        auth.saveUserInfo(role, name, phoneNumber, email, encryptedPass);
        System.out.println("Sign Up successful");

        // Display dashboard based on role
        authDashboard.displayDashboard(role);

        scanner.close();
    }


    // Validate email to check if it contains only lowercase letters and ends with '@gmail.com' or '@yahoo.com'
    private boolean isValidEmail(String email) {
        return email.matches("[a-z0-9._%+-]+@(gmail\\.com|yahoo\\.com)");
    }

    // Validate phone number to check if it is 11 digits and starts with '01'
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("01\\d{9}");
    }
}
