package Draft;

import java.io.*;
import java.util.*;

public class Admin {
    private String email;
    private String password;
    private FileManager fileManager = new FileManager();
    private Scanner scanner = new Scanner(System.in);

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Display the Admin Menu
    public void displayAdminMenu() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Approve Requests from Transport Agencies");
            System.out.println("2. Review User Suggestions and Comment");
            System.out.println("3. View Login Information (Excluding Passwords)");
            System.out.println("4. Manage Admin Account");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    approveTransportAgencyRequests();
                    break;
                case 2:
                    reviewUserSuggestions();
                    break;
                case 3:
                    viewLoginInfo();
                    break;
                case 4:
                    manageAdminAccount();
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

    // Approve requests from Transport Agencies (e.g., account deletion requests)
    private void approveTransportAgencyRequests() {
        System.out.println("Approval Requests from Transport Agencies:");
        String[] requests = fileManager.readFileLines("requests.txt");
        if (requests == null || requests.length == 0) {
            System.out.println("No pending requests.");
            return;
        }

        for (int i = 0; i < requests.length; i++) {
            System.out.println((i + 1) + ". " + requests[i]);
        }

        System.out.println("Select a request to approve or reject (1 - " + requests.length + "):");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > requests.length) {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("Approve or Reject request? (approve/reject)");
        String decision = scanner.next();
        if (decision.equalsIgnoreCase("approve")) {
            System.out.println("Request approved.");
            fileManager.removeLineFromFile("requests.txt", requests[choice - 1]);
        } else {
            System.out.println("Request rejected.");
        }

        // Record the decision to approval history
        fileManager.writeToFile("approval_history.txt", "Request: " + requests[choice - 1] + " - Decision: " + decision);
    }

    // Review user suggestions and comment on them
    private void reviewUserSuggestions() {
        System.out.println("User Suggestions:");
        String[] suggestions = fileManager.readFileLines("suggestions.txt");
        if (suggestions == null || suggestions.length == 0) {
            System.out.println("No suggestions available.");
            return;
        }

        for (int i = 0; i < suggestions.length; i++) {
            System.out.println((i + 1) + ". " + suggestions[i]);
        }

        System.out.println("Select a suggestion to comment on (1 - " + suggestions.length + "):");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > suggestions.length) {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("Write your comment:");
        scanner.nextLine(); // consume newline
        String comment = scanner.nextLine();

        fileManager.writeToFile("comments.txt", "Suggestion: " + suggestions[choice - 1] + " - Admin Comment: " + comment);
        System.out.println("Comment posted.");
    }

    // View login information (excluding passwords)
    private void viewLoginInfo() {
        System.out.println("Viewing Login Information:");
        String[] users = fileManager.readFileLines("user_info.txt");
        String[] agencies = fileManager.readFileLines("agency_info.txt");

        System.out.println("User Logins:");
        if (users == null || users.length == 0) {
            System.out.println("No user login data available.");
        } else {
            for (String user : users) {
                System.out.println(user);
            }
        }

        System.out.println("\nTransport Agency Logins:");
        if (agencies == null || agencies.length == 0) {
            System.out.println("No agency login data available.");
        } else {
            for (String agency : agencies) {
                System.out.println(agency);
            }
        }
    }

    // Manage Admin Account (view account info, delete account)
    private void manageAdminAccount() {
        System.out.println("Admin Account Information:");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        System.out.println("Approval Request History:");
        String[] history = fileManager.readFileLines("approval_history.txt");
        if (history == null || history.length == 0) {
            System.out.println("No history available.");
        } else {
            for (String record : history) {
                System.out.println(record);
            }
        }

        System.out.println("Do you want to delete your account? (yes/no)");
        String deleteAccount = scanner.next();
        if (deleteAccount.equalsIgnoreCase("yes")) {
            System.out.println("Sending account deletion request to higher authorities...");
            fileManager.writeToFile("requests.txt", "Admin Deletion Request - Email: " + email);
        }
    }
}