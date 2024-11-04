package Authentication;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class ManageAccountDash {
    public void showManageAccountDash() throws IOException, NoSuchAlgorithmException{
         
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {

            System.out.println("[1] Delete Account");
            System.out.println("[2] Log Out");
        
            System.out.print("Enter your choice: ");


            int mainOption = scanner.nextInt();
            switch (mainOption) {
                case 1 -> new DeleteAccount().deleteAccount();
                
                case 2 -> {
                    System.out.println("Logging Out ...");
                    UserAccess userAccess = new UserAccess();
                    userAccess.start();
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

