import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Login loginSystem = new Login();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Sign Up\n2. Log In\n3. Exit\nChoose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();


            if (choice == 1) {
                loginSystem.signUp();
            } else if (choice == 2) {
                loginSystem.logIn();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}