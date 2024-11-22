
// package Admin;

// import Authentication.DeleteAccount;
// import Authentication.UserAccess;
// import java.io.IOException;
// import java.security.NoSuchAlgorithmException;
// import java.util.*;

// public class AdminDashboard {

//     public void displayAdminMenu() throws NoSuchAlgorithmException, IOException {
//         Scanner scanner = new Scanner(System.in);
//         boolean isRunning = true;

//         while (isRunning) {
//             System.out.println(" ______________________________________________________________________");
//             System.out.println("                                 ADMIN                               ");
//             System.out.println(" ______________________________________________________________________");

//             System.out.println("\nAdmin Menu:");
//             System.out.println("[1] Approve Requests from Transport Agencies");
//             System.out.println("[2] Review User Suggestions and Comments");
//             System.out.println("[3] View Login Information ");
//             System.out.println("[4] Delete Account");
//             System.out.println("[5] Log Out");
//             System.out.println(".............................");

//             int choice = scanner.nextInt();
//             switch (choice) {
//                 case 1 -> new ApproveRequest().approveTransportAgencyRequests();
//                 case 2 -> new SeeReviews().reviewUserSuggestions();
//                 case 3 -> new ViewAllLogins().showAllLogins();
//                 case 4 -> {
//                     if (new DeleteAccount().deleteAccount()) {
//                         System.out.println("Account deleted successfully. Returning to dashboard...");
//                         isRunning = false;
//                         UserAccess u = new UserAccess();
//                         u.start(); // Return to user access point
//                     } else {
//                         System.out.println("Account deletion cancelled or failed.");
//                     }
//                 }
//                 case 5 -> {
//                     System.out.println("Exiting Traveler Dashboard...");
//                     isRunning = false;
//                     UserAccess u = new UserAccess();
//                     u.start();// Exit the loop
//                 }
//                 default -> System.out.println("Invalid option. Please try again.");
                
                
//             }
//         }
//     }
// }
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
            // System.out.print("\033[H\033[2J");  
            // System.out.flush();  
        
            System.out.println();
            
            System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                ADMIN MENU                              ║");
            System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║ [1]  Approve Requests from Transport Agencies                          ║");
            System.out.println("║ [2]  Review User Suggestions and Comments                              ║");
            System.out.println("║ [3]  View Login Information                                            ║");
            System.out.println("║ [4]  Delete Account                                                    ║");
            System.out.println("║ [5]  Log Out                                                           ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
            System.out.println();
            System.out.print(" Please enter your choice (1-5): ");

            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1 -> {
                    System.out.println("Processing Request Approval...");
                    new ApproveRequest().approveTransportAgencyRequests();
                }
                case 2 -> {
                    System.out.println("Reviewing User Suggestions and Comments...");
                    new SeeReviews().reviewUserSuggestions();
                }
                case 3 -> {
                    System.out.println("Displaying Login Information...");
                    new ViewAllLogins().showAllLogins();
                }
                case 4 -> {
                    if (new DeleteAccount().deleteAccount()) {
                        System.out.println("\nAccount deleted successfully. Returning to dashboard...");
                        isRunning = false;
                        UserAccess u = new UserAccess();
                        u.start();
                    } else {
                        System.out.println("\nAccount deletion cancelled or failed.");
                    }
                }
                case 5 -> {
                    System.out.println("Logging out of the Admin Dashboard...");
                    isRunning = false;
                    UserAccess u = new UserAccess();
                    u.start();
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
            
            System.out.println("\nPress Enter to continue...");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
