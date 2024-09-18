package User;
import java.util.Scanner;

public class UserDashboard {
    public void displayUserMenu() {
        Scanner scanner=new Scanner(System.in);
        boolean isRunning = true;
        UserFuctionalities userFun = new UserFuctionalities();

        while (isRunning) {
            System.out.println("User Menu");
            System.out.println("1. Select Date and Time to Travel");
            System.out.println("2. View and Manage Account");
            System.out.println("3. Give Suggestions for a Place");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    userFun.selectDateAndTime();
                    break;
                case 2:
                    userFun.manageAccount();
                    break;
                case 3:
                    userFun.giveSuggestions();
                    break;
                case 4:
                    isRunning = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
