package Admin;

import Admin.AdminFunctionalities.ApproveRequest;
import Admin.AdminFunctionalities.ViewAllLogins;
import Admin.AdminFunctionalities.SeeReviews.SeeReviews;
import Admin.AdminFunctionalities.ManageAccount.ShowloginInfoOfAdmin;
import Admin.AdminFunctionalities.ManageAccount.ManageAccountDashBoard;
import java.util.Scanner;


public class AdminDashboard{
    public void displayAdminMenu() {

        ApproveRequest approveReq = new ApproveRequest();
        SeeReviews seeReview = new SeeReviews();
        ViewAllLogins viewAllLogins = new ViewAllLogins();
        ShowloginInfoOfAdmin showloginInfoOfAdmin = new ShowloginInfoOfAdmin();
        ManageAccountDashBoard manageAccountDashBoard=new ManageAccountDashBoard();
        

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Approve Requests from Transport Agencies");
            System.out.println("2. Review User Suggestions and Comment");
            System.out.println("3. View Login Information ");
            System.out.println("4. Manage Admin Account");
            System.out.println("5. Logout");

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
                case 4:
                    // ManageAccountDashBoard.manageAdminAccount();
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
