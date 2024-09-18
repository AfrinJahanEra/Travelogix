package Authentication;

import java.util.Scanner;

public class AuthenticationHandler {
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the Travel Software");
        System.out.println("1. Sign Up");
        System.out.println("2. Login");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (choice == 1) {
            signUp();
        } else if (choice == 2) {
            login();
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void signUp() {
        System.out.println("Sign Up Process");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        // Email validation logic
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            System.out.println("Invalid email format.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters.");
            return;
        }

        System.out.print("Confirm password: ");
        String confirmPassword = scanner.nextLine();

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }

        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine();

        if (contactNumber.length() != 11 || !contactNumber.matches("\\d+")) {
            System.out.println("Contact number must be 11 digits.");
            return;
        }

        System.out.println("Select role: ");
        System.out.println("1. User");
        System.out.println("2. Transport Agency");
        System.out.println("3. Admin");

        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String role = "";
        if (roleChoice == 1) role = "User";
        else if (roleChoice == 2) role = "TransportAgency";
        else if (roleChoice == 3) role = "Admin";
        else {
            System.out.println("Invalid role choice.");
            return;
        }

        boolean success = AuthService.signUp(email, password, contactNumber, role);
        if (success) {
            System.out.println("Successfully signed up. You can now log in.");
        }
    }

    private void login() {
        System.out.println("Login Process");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("Select role: ");
        System.out.println("1. User");
        System.out.println("2. Transport Agency");
        System.out.println("3. Admin");

        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String role = "";
        if (roleChoice == 1) role = "User";
        else if (roleChoice == 2) role = "TransportAgency";
        else if (roleChoice == 3) role = "Admin";
        else {
            System.out.println("Invalid role choice.");
            return;
        }

        String userEmail = AuthService.login(email, password, role);
        if (userEmail != null) {
            System.out.println("Login successful as " + role + ".");
            // Redirect based on role
            switch (role) {
                case "User":
                    // Load UserModule
                    break;
                case "TransportAgency":
                    // Load TransportAgencyModule
                    break;
                case "Admin":
                    // Load AdminModule
                    break;
            }
        } else {
            System.out.println("Invalid email or password.");
        }
    }
}
