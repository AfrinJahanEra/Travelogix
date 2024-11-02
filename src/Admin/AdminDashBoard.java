package Admin;

import java.util.Scanner;

public class AdminDashBoard {

    public void displayAdminMenu() {

        ApproveRequest approveReq = new ApproveRequest();
        SeeReviews seeReview = new SeeReviews();
        ViewAllLogins viewAllLogins = new ViewAllLogins();
        
        

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Approve Requests from Transport Agencies");
            System.out.println("2. Review User Suggestions and Comment");
            System.out.println("3. View Login Information ");
        

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    approveReq.approveTransportAgencyRequests();
                    break;
                case 2:
                    seeReview.reviewUserSuggestions();
                    break;
                case 3:
                    viewAllLogins.showAllLogins();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
