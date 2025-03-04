package Traveler.Past_Travel_History;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class PastHistoryDashboard {

    Scanner scanner = new Scanner(System.in);
    public void historyDashboard(Scanner scanner) {

        boolean isPlanning = true;

        while (isPlanning) {

            waitForEnterKey();
            clearTerminal();

            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║                PLAN A TRIP               ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║                                          ║");
            System.out.println("║    [1] All Trip History                  ║");
            System.out.println("║    [2] Year Wise Trip                    ║");
            System.out.println("║    [3] Itinary History Summury           ║");
            System.out.println("║    [4] Back to Main Menu                 ║");
            System.out.println("║                                          ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            int planOption = getIntInput();
            switch (planOption) {
                case 1 -> new TripHistoryViewer().viewPastTrips();
                case 2 -> new TripHistoryViewer().viewYearWiseTrips();
                case 3 -> new AlarmHistoryManager().viewAlarmHistory();  
                case 4 -> isPlanning = false;  
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

