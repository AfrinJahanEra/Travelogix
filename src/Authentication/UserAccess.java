package Authentication;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserAccess {
    Login in = new Login();
    SignUp out = new SignUp();

    public void start() throws NoSuchAlgorithmException, IOException {
        Scanner scanner = new Scanner(System.in);

        // Welcome Message
        printTitle("Welcome to Travelogix");

        while (true) {
            // Main Menu
            System.out.println("\nMain Menu:");
            System.out.println("----------------------------------------");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("0. Exit");
            System.out.println("----------------------------------------");

            try {
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        System.out.println("\nLogging you in...");
                        in.logIn(); // Call login method
                        break;
                    case 2:
                        System.out.println("\nStarting Sign Up...");
                        out.signUp(); // Call sign up method
                        break;
                    case 0:
                        System.out.println("\nThank you for using Travelogix. Goodbye!");
                        scanner.close();
                        System.exit(0); // Exit the program
                        break;
                    default:
                        System.out.println("\nInvalid option! Please enter 1, 2, or 0.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    private void printTitle(String message) {
        System.out.println("\n========================================");
        System.out.printf("  %s%n", message);
        System.out.println("========================================\n");
    }
}
