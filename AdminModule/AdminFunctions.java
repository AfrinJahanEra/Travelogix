package AdminModule;

import java.util.Scanner;

public class AdminFunctions {
    private final Scanner scanner = new Scanner(System.in);

    public void adminDashboard() {
        System.out.println("Welcome to Admin Dashboard");
        System.out.println("1. Approve Requests");
        System.out.println("2. Review Suggestions");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                approveRequests();
                break;
            case 2:
                reviewSuggestions();
                break;
        }
    }

    private void approveRequests() {
        // Code to approve account deletion requests from transport agencies
    }

    private void reviewSuggestions() {
        // Code to review and comment on user suggestions
    }
}
