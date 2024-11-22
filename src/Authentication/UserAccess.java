package Authentication;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class UserAccess {
    Login in = new Login();
    SignUp out = new SignUp();

    public void start() throws NoSuchAlgorithmException, IOException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("\n \n");
        System.out.println(".................................");
        System.out.println(".    Welcome to Travelogix      .");
        System.out.println(".................................");
        System.out.println("\n");
        System.out.println("Select an option to use Travelogix:");
        System.out.println("[1] Login");
        System.out.println("[2] Sign Up");
        System.out.println("[0] Exit");
        System.out.println("\n");

        while (true) {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    in.logIn();
                    break;
                case 2:
                    out.signUp();
                    break;
                case 0:
                    System.out.println("\nThank you for using Travelogix. Goodbye!");
                    scanner.close();
                    System.exit(0);  // Exit the program
                default:
                    System.out.println("Invalid option! Please enter 1, 2, or 0.");
                    break;
            }
        }
    }
}
