import java.util.Scanner;

public class SignUp extends Authentication {

    private static final String USERS_FILE = "users.txt";
    private static final int MIN_PASSWORD_LENGTH = 8;

    // Method to sign up as Admin or User do the work to work
    public void signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sign Up");
        System.out.println("Role: \n1. Admin\n2. Traveler\nEnter your role: ");
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

}
