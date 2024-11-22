
package Admin;

import Authentication.DeleteAccount;
import Authentication.UserAccess;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class AdminDashboard {

    public void displayAdminMenu() throws NoSuchAlgorithmException, IOException {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println(" ________________________________");
            System.out.println("|              ADMIN             |");
            System.out.println("|________________________________|");

            System.out.println("\nAdmin Menu:");
            System.out.println("[1] Approve Requests from Transport Agencies");
            System.out.println("[2] Review User Suggestions and Comments");
            System.out.println("[3] View Login Information ");
            System.out.println("[4] Delete Account");
            System.out.println("[5] Log Out");
            System.out.println(".............................");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> new ApproveRequest().approveTransportAgencyRequests();
                case 2 -> new SeeReviews().reviewUserSuggestions();
                case 3 -> new ViewAllLogins().showAllLogins();
                case 4 -> {
                    if (new DeleteAccount().deleteAccount()) {
                        System.out.println("Account deleted successfully. Returning to dashboard...");
                        isRunning = false;
                        UserAccess u = new UserAccess();
                        u.start(); // Return to user access point
                    } else {
                        System.out.println("Account deletion cancelled or failed.");
                    }
                }
                case 5 -> {
                    System.out.println("Exiting Traveler Dashboard...");
                    isRunning = false;
                    UserAccess u = new UserAccess();
                    u.start();// Exit the loop
                }
                default -> System.out.println("Invalid option. Please try again.");
                
                
            }
        }
    }
}