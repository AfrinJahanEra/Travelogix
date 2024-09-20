package Admin.AdminFunctionalities.ManageAccount;
import Utilities_Package.Musers.Logout;
import java.util.Scanner;

public class ManageAccountDashBoard {
    public void showMenu() {
        Logout logout = new Logout();
        ShowloginInfoOfAdmin showloginInfoOfAdmin= new ShowloginInfoOfAdmin();
        DeleteAccountOfAdmin deleteAccountOfAdmin = new DeleteAccountOfAdmin();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Show Login Info");
            System.out.println("2. Delete Account");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showloginInfoOfAdmin.showLoginInfo();
                    break;
                case 2:
                    deleteAccountOfAdmin.deleteAccount();
                    return; 
                case 3:
                    
                    return; 
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
