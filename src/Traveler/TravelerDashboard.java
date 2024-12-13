package Traveler;

import Authentication.DeleteAccount;
import Authentication.UserAccess;
import Traveler.Checklist_NoteKeeping.NoteKeeping.NoteKeepingDashboard;
import Traveler.Itinerary_Management.Alarm.AlertSystem;
import Traveler.Trip_Management.TripManager;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TravelerDashboard {
    TripManager t = new TripManager();
    Scanner scanner = new Scanner(System.in);

    public void showDashboard() throws NoSuchAlgorithmException, IOException {
        boolean isRunning = true;

        while (isRunning) {
           
            waitForEnterKey();
            clearTerminal();

            System.out.println("╔════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                           TRAVELER DASHBOARD                       ║");
            System.out.println("╠════════════════════════════════════════════════════════════════════╣");
            System.out.println("║                                                                    ║");
            System.out.println("║    [1] Plan a Trip                                                 ║");
            System.out.println("║    [2] Manage Trips                                                ║");
            System.out.println("║    [3] Delete Account                                              ║");
            System.out.println("║    [5] Log Out                                                     ║");
            System.out.println("║                                                                    ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            int mainOption =  getIntInput();

            switch (mainOption) {
                case 1 -> showPlanATripOptions(scanner);
                case 2 -> showManageTripsOptions(scanner);
                case 3 -> {
                    if (new DeleteAccount().deleteAccount()) {
                        System.out.println("\n");
                        System.out.println("Account deleted successfully. Returning");
                        System.out.println("to dashboard...     ");
                        isRunning = false;
                        UserAccess u = new UserAccess();
                        u.start();
                    } else {
                        System.out.println("\n");
                        System.out.println("Account deletion cancelled or failed.     ");
                    }
                }
                case 5 -> {
                    System.out.println("\n");
                    System.out.println("Exiting Traveler Dashboard...    ");
                    isRunning = false;
                    UserAccess u = new UserAccess();
                    u.start();
                }
                default -> {
                    System.out.println("\n");
                    System.out.println("Invalid option. Please try again.         ");
                }
            }
        }
        scanner.close();
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
    private void showPlanATripOptions(Scanner scanner) {

        boolean isPlanning = true;

        while (isPlanning) {

            waitForEnterKey();
            clearTerminal();

            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║                PLAN A TRIP               ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║                                          ║");
            System.out.println("║    [1] Add a Trip                        ║");
            System.out.println("║    [2] Browse Transports                 ║");
            System.out.println("║    [3] Manage Notes                      ║");
            System.out.println("║    [4] Manage Itinerary                  ║");
            System.out.println("║    [5] Track Budget                      ║");
            System.out.println("║    [6] Back to Main Menu                 ║");
            System.out.println("║                                          ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            int planOption = getIntInput();
            switch (planOption) {
                case 1 -> t.addTrip();
                case 2 -> browseTransports();
                case 3 -> new NoteKeepingDashboard().displayChecklist();  
                case 4 -> manageItinerary(scanner);  
                // case 5 -> trackBudget(); 
                case 6 -> isPlanning = false;  
                default -> {
                    System.out.println("\n");
                    System.out.println("Invalid option. Please try again." );
                }
            }
        }
    }


    private void showManageTripsOptions(Scanner scanner) {

        boolean isManaging = true;

        while (isManaging) {

            waitForEnterKey();
            clearTerminal();

            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║               MANAGE TRIPS               ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║                                          ║");
            System.out.println("║    [1] View Trips                        ║");
            System.out.println("║    [2] Remove Trips                      ║");
            System.out.println("║    [3] Back to Main Menu                 ║");
            System.out.println("║                                          ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            int manageTripsOption = getIntInput();
            switch (manageTripsOption) {
                case 1 -> t.viewTrips();
                case 2 -> t.removeTrip();
                case 3 -> isManaging = false; 
                default -> {
                    System.out.println("\n");
                    System.out.println("Invalid option. Please try again. ");    
                }
            }
        }
    }

    public void browseTransports() {
        Scanner scanner = new Scanner(System.in);

        TransportBrowser transportBrowser=new TransportBrowser();
        System.out.print("Enter starting location: ");
        String startLocation = scanner.nextLine().trim();

        System.out.print("Enter destination: ");
        String destination = scanner.nextLine().trim();

        String busFile = "src\\TXT_Files\\bus.txt";
        List<String[]> results = transportBrowser.searchTransports(startLocation, destination, busFile);
        transportBrowser.displayTransports(results);
    }

    private void manageItinerary(Scanner scanner) {

        boolean itinerary = true;

        while (itinerary) {

            waitForEnterKey();
            clearTerminal();

            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║             MANAGE ITINERARY             ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║                                          ║");
            System.out.println("║    [1] View Trips on Calendar            ║");
            System.out.println("║    [2] Set Reminder                      ║");
            System.out.println("║    [3] Back to Main Menu                 ║");
            System.out.println("║                                          ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            int manageItineraryOption = getIntInput();
            switch (manageItineraryOption) {
                case 1 -> t.viewTripsOnCalendar();
                case 2 -> {
                    AlertSystem a = new AlertSystem();
                    a.alertSystem();
                }
                case 3 -> itinerary = false;  
                default -> {
                    System.out.println("\n");
                    System.out.println("Invalid option. Please try again.          ");
                }
            }
        }
    }

}
