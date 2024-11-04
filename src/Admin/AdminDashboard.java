// package Admin;

// import java.util.Scanner;

// public class AdminDashboard{

//     public void displayAdminMenu() {

//         ApproveRequest approveReq = new ApproveRequest();
//         SeeReviews seeReview = new SeeReviews();
//         ViewAllLogins viewAllLogins = new ViewAllLogins();
        
        

//         Scanner scanner = new Scanner(System.in);
//         boolean isRunning = true;

//         while (isRunning) {

//             System.out.println(" ________________________________");
//             System.out.println("|              ADMIN             |");
//             System.out.println("|________________________________|");
            
//             System.out.println("\nAdmin Menu:");
//             System.out.println("1. Approve Requests from Transport Agencies");
//             System.out.println("2. Review User Suggestions and Comment");
//             System.out.println("3. View Login Information ");
        

//             int choice = scanner.nextInt();
//             switch (choice) {
//                 case 1:
//                     approveReq.approveTransportAgencyRequests();
//                     break;
//                 case 2:
//                     seeReview.reviewUserSuggestions();
//                     break;
//                 case 3:
//                     viewAllLogins.showAllLogins();
//                     break;
//                 default:
//                     System.out.println("Invalid option. Please try again.");
//             }
//         }
//     }
// }

package Admin;

import java.util.*;

public class AdminDashboard {

    public void displayAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println(" ________________________________");
            System.out.println("|              ADMIN             |");
            System.out.println("|________________________________|");

            System.out.println("\nAdmin Menu:");
            System.out.println("1. Approve Requests from Transport Agencies");
            System.out.println("2. Review User Suggestions and Comments");
            System.out.println("3. View Login Information ");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> new ApproveRequest().approveTransportAgencyRequests();
                case 2 -> new SeeReviews().reviewUserSuggestions();
                case 3 -> new ViewAllLogins().showAllLogins();
                case 4 -> {
                    System.out.println("Exiting Admin Dashboard...");
                    isRunning = false;  // Exit loop
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
