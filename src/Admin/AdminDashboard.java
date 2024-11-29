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

            waitForEnterKey();
            clearTerminal();
        
            System.out.println();
            
            System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                ADMIN MENU                              ║");
            System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║ [1]  Approve Requests from Transport Agencies                          ║");
            System.out.println("║ [2]  View Login Information                                            ║");
            System.out.println("║ [3]  Delete Account                                                    ║");
            System.out.println("║ [4]  Log Out                                                           ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
            System.out.println();
            System.out.print(" Please enter your choice (1-4): ");

            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1 -> {
                    System.out.println(" ");
                    System.out.println("Processing Request Approval...");
                    System.out.println(" ");
                    new ApproveRequest().approveTransportAgencyRequests();
                }
                // case 2 -> {
                //     System.out.println(" ");
                //     System.out.println("Reviewing User Suggestions and Comments...");
                //     System.out.println(" ");
                //     new SeeReviews().reviewUserSuggestions();
                // }
                case 2 -> {
                    System.out.println(" ");
                    System.out.println("Displaying Login Information...");
                    System.out.println(" ");
                    new ViewAllLogins().showAllLogins();
                }
                case 3 -> {
                    if (new DeleteAccount().deleteAccount()) {
                        System.out.println(" ");
                        System.out.println("\nAccount deleted successfully. Returning to dashboard...");
                        System.out.println(" ");
                        isRunning = false;
                        UserAccess u = new UserAccess();
                        u.start();
                    } else {
                        System.out.println(" ");
                        System.out.println("\nAccount deletion cancelled or failed.");
                        System.out.println(" ");
                    }
                }
                case 4 -> {
                    System.out.println(" ");
                    System.out.println("Logging out of the Admin Dashboard...");
                    System.out.println(" ");
                    isRunning = false;
                    UserAccess u = new UserAccess();
                    u.start();
                }
                default -> {
                    System.out.println(" ");
                    System.out.println("Invalid option. Please try again.");
                    System.out.println(" ");
                }
            }
            System.out.println(" ");
            System.out.println("\nPress Enter to continue...");
            System.out.println(" ");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void waitForEnterKey() {
        System.out.println("\nPress ENTER to continue...");
        Scanner enterScanner = new Scanner(System.in);
        enterScanner.nextLine(); // Waits for the ENTER key press
    }

    private void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to clear terminal.");
        }
    }
}
