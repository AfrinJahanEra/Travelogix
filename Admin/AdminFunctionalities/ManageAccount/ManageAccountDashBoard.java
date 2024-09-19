package Admin.AdminFunctionalities.ManageAccount;

import java.util.Scanner;

public class ManageAccountDashBoard {
    private void showMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Show Login Info");
            System.out.println("2. Delete Account");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showLoginInfo();
                    break;
                case 2:
                    deleteAccount();
                    return; // Exit the method and go back to login page
                case 3:
                    logout();
                    return; // Exit the method and go back to login page
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
