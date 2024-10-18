import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SignUp extends Authentication {

    private static final String USERS_FILE = "users.txt";
    private static final int MIN_PASSWORD_LENGTH = 8;

    // Method to sign up as Admin or User
    public void signUp() throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sign Up");
        System.out.println("Role: \n1. Admin\n2. Traveler\nEnter your role: ");
        String role = scanner.nextLine().trim();

        System.out.println("Enter name: ");
        String name = scanner.nextLine().trim();

        String phoneNumber = "";
        while (true) {
            System.out.println("Enter phone number: ");
            phoneNumber = scanner.nextLine().trim();
            if (isValidPhoneNumber(phoneNumber)) {
                break;
            } else {
                System.out.println("Invalid phone number. It must be 11 digits and start with '01'.");
            }
        }

        String email = "";
        while (true) {
            System.out.println("Enter email: ");
            email = scanner.nextLine().trim();
            
            if (!isValidEmail(email)) {
                System.out.println("Invalid email. It must contain '@gmail.com' or '@yahoo.com', and only lowercase letters. Try again.");
            } 
            else if (!isEmailUnique(email)) {
                System.out.println("This email is already registered. Please try a different one.");
            } else {
                break; // Valid, unique, and lowercase email
            }
        }

        String password = "";
        while (true) {
            System.out.println("Enter password (Password must be at least 8 characters long): ");
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

        String encrypted_pass = encryptPassword(password);

        // Save user information
        saveUserInfo(role, name, phoneNumber, email, encrypted_pass);
        System.out.println("Sign Up successful");

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
