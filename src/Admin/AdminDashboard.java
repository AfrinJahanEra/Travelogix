package Admin;

import Authentication.DeleteAccount;
import Authentication.UserAccess;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminDashboard {

    public void displayAdminMenu() throws NoSuchAlgorithmException, IOException {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            waitForEnterKey();
            clearTerminal();

            System.out.println();
            System.out.println("                            ╔════════════════════════════════════════════════════════════════════════╗");
            System.out.println("                            ║                                ADMIN MENU                              ║");
            System.out.println("                            ╠════════════════════════════════════════════════════════════════════════╣");
            System.out.println("                            ║ [1]  Approve Requests from Transport Agencies                          ║");
            System.out.println("                            ║ [2]  View Login Information                                            ║");
            System.out.println("                            ║ [3]  Delete Account                                                    ║");
            System.out.println("                            ║ [4]  Log Out                                                           ║");
            System.out.println("                            ╚════════════════════════════════════════════════════════════════════════╝");
            System.out.println();

            int choice = -1; // Default invalid choice
            try {
                System.out.print(" Please enter your choice (1-4): ");
                choice = scanner.nextInt();
                scanner.nextLine(); 

                System.out.println();
                switch (choice) {
                    case 1 -> {
                        System.out.println("Processing Request Approval...");
                        new ApproveRequest().approveTransportAgencyRequests();
                    }
                    case 2 -> {
                        System.out.println("Displaying Login Information...");
                        new ViewAllLogins().showAllLogins();
                    }
                    case 3 -> {
                        if (new DeleteAccount().deleteAccount()) {
                            System.out.println("\nAccount deleted successfully. Returning to dashboard...");
                            isRunning = false;
                            new UserAccess().start();
                        } else {
                            System.out.println("\nAccount deletion cancelled or failed.");
                        }
                    }
                    case 4 -> {
                        System.out.println("Logging out of the Admin Dashboard...");
                        isRunning = false;
                        new UserAccess().start();
                    }
                    default -> {
                        System.out.println("Invalid option. Please try again.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input! Please enter a number between 1 and 4.");
                scanner.nextLine(); // Clear invalid input from scanner
            } catch (IOException | NoSuchAlgorithmException e) {
                System.out.println("\nAn error occurred: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\nUnexpected error: " + e.getMessage());
            }

            System.out.println("\nPress Enter to continue...");
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
