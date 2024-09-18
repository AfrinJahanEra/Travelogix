package Admin;
import java.util.Scanner;

public class AdminDashboard {
    public void displayAdminMenu() {

        AdminFunctionalities addFun = new AdminFunctionalities();

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
                    addFun.approveTransportAgencyRequests();
                    break;
                case 2:
                    addFun.reviewUserSuggestions();
                    break;
                case 3:
                    addFun.viewLoginInfo();
                    break;
                case 4:
                    addFun.manageAdminAccount();
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
