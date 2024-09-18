package Draft;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static FileManager fileManager = new FileManager();
    
    public static void main(String[] args) {
        System.out.println("Welcome to the Travel Booking System!");

        while (true) {
            System.out.println("\n1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    signUp();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // User Sign-Up
    private static void signUp() {
        scanner.nextLine(); // Consume newline
        System.out.println("Enter Email (format: example@gmail.com): ");
        String email = scanner.nextLine();
        if (!validateEmail(email)) {
            System.out.println("Invalid email format!");
            return;
        }

        System.out.println("Enter Password (min 8 characters): ");
        String password = scanner.nextLine();
        if (!validatePassword(password)) {
            System.out.println("Password must be at least 8 characters.");
            return;
        }

        System.out.println("Confirm Password: ");
        String confirmPassword = scanner.nextLine();
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match!");
            return;
        }

        System.out.println("Enter Contact Number (11 digits): ");
        String contact = scanner.nextLine();
        if (!validateContactNumber(contact)) {
            System.out.println("Invalid contact number!");
            return;
        }

        System.out.println("Are you a (1) User, (2) Transport Agency, or (3) Admin?");
        int role = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (role == 1) {
            fileManager.writeToFile("user_info.txt", email + ", " + contact);
            System.out.println("User Sign-Up Successful!");
        } else if (role == 2) {
            fileManager.writeToFile("agency_info.txt", email + ", " + contact);
            System.out.println("Transport Agency Sign-Up Successful!");
        } else if (role == 3) {
            fileManager.writeToFile("admin_info.txt", email + ", " + contact);
            System.out.println("Admin Sign-Up Successful!");
        } else {
            System.out.println("Invalid role selected.");
        }
    }

    // User Login
    private static void login() {
        scanner.nextLine(); // Consume newline
        System.out.println("Enter Email: ");
        String email = scanner.nextLine();

        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        // Check if login details are valid for any role
        if (validateLogin(email, password, "user_info.txt")) {
            User user = new User(email, password);
            user.displayUserMenu();
        } else if (validateLogin(email, password, "agency_info.txt")) {
            TransportAgency agency = new TransportAgency(email, password);
            agency.displayAgencyMenu();
        } else if (validateLogin(email, password, "admin_info.txt")) {
            Admin admin = new Admin(email, password);
            admin.displayAdminMenu();
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    // Validate email format
    private static boolean validateEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }

    // Validate password length
    private static boolean validatePassword(String password) {
        return password.length() >= 8;
    }

    // Validate contact number (11 digits)
    private static boolean validateContactNumber(String contact) {
        return contact.matches("\\d{11}");
    }

    // Validate login credentials
    private static boolean validateLogin(String email, String password, String fileName) {
        String[] lines = fileManager.readFileLines(fileName);
        if (lines == null) {
            return false;
        }

        for (String line : lines) {
            String[] parts = line.split(", ");
            if (parts[0].equals(email)) {
                // Simulate password validation (in real systems, passwords would be hashed and checked)
                return true;
            }
        }
        return false;
    }
}