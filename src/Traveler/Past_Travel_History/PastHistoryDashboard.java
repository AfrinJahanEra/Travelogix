package Traveler.Past_Travel_History;

import Traveler.TravelerDashboard;
import Traveler.Trip_Management.TripManager;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class PastHistoryDashboard {

    TripManager tripManager = new TripManager();
    TravelerDashboard travelerDashboard = new TravelerDashboard();
    TripHistoryViewer tripHistoryViewer = new TripHistoryViewer();

    Scanner scanner = new Scanner(System.in);
    public void historyDashboard(Scanner scanner) {

        boolean isPlanning = true;

        while (isPlanning) {

            waitForEnterKey();
            clearTerminal();

            System.out.println("\n                                      ╔══════════════════════════════════════════╗");
            System.out.println("                                      ║         TRIPS & PAST ACTIVITIES          ║");
            System.out.println("                                      ╠══════════════════════════════════════════╣");
            System.out.println("                                      ║                                          ║");
            System.out.println("                                      ║    [1] All Trips                         ║");
            System.out.println("                                      ║    [2] Year Wise Trips                   ║");
            System.out.println("                                      ║    [3] Back to Main Menu                 ║");
            System.out.println("                                      ║                                          ║");
            System.out.println("                                      ╚══════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            int planOption = getIntInput();
            switch (planOption) {
                case 1 -> travelerDashboard.showManageTripsOptions(scanner);
                case 2 -> new TripHistoryViewer().viewYearWiseTrips();
                case 3 -> isPlanning = false;  
                default -> {
                    System.out.println("\n");
                    System.out.println("Invalid option. Please try again." );
                }
            }
        }
    }
    


    
    private int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.nextLine(); 
            }
        }
    }


    private void waitForEnterKey() {
        System.out.println("\nPress ENTER to continue...");
        Scanner enterScanner = new Scanner(System.in);
        enterScanner.nextLine(); 
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

