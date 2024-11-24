package Traveler;

import Authentication.DeleteAccount;
import Authentication.UserAccess;
import Traveler.Checklist_NoteKeeping.NoteKeeping.NoteKeepingDashboard;
import Traveler.Itinerary_Management.Alarm.AlertSystem;
import Traveler.Trip_Management.TripManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class TravelerDashboard {
    TripManager t = new TripManager();
    Scanner scanner = new Scanner(System.in);

    public void showDashboard() throws NoSuchAlgorithmException, IOException {
        boolean isRunning = true;

        while (isRunning) {
            // Main dashboard options
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

            int mainOption = scanner.nextInt();

            switch (mainOption) {
                case 1 -> showPlanATripOptions(scanner);  
                case 2 -> showManageTripsOptions(scanner); 
                case 3 -> {
                    if (new DeleteAccount().deleteAccount()) {
                        System.out.println("\n╔══════════════════════════════════════════════╗");
                        System.out.println("║    Account deleted successfully. Returning   ║");
                        System.out.println("║    to dashboard...                           ║");
                        System.out.println("╚══════════════════════════════════════════════╝");
                        isRunning = false;
                        UserAccess u = new UserAccess();
                        u.start();
                    } else {
                        System.out.println("\n╔══════════════════════════════════════════════╗");
                        System.out.println("║    Account deletion cancelled or failed.     ║");
                        System.out.println("╚══════════════════════════════════════════════╝");
                    }
                }
                case 5 -> {
                    System.out.println("\n╔══════════════════════════════════════════════╗");
                    System.out.println("║    Exiting Traveler Dashboard...             ║");
                    System.out.println("╚══════════════════════════════════════════════╝");
                    isRunning = false;
                    UserAccess u = new UserAccess();
                    u.start(); // Exit the loop
                }
                default -> {
                    System.out.println("\n╔══════════════════════════════════════════════╗");
                    System.out.println("║    Invalid option. Please try again.         ║");
                    System.out.println("╚══════════════════════════════════════════════╝");
                }
            }
        }
        scanner.close();
    }

    private void showPlanATripOptions(Scanner scanner) {
        boolean isPlanning = true;

        while (isPlanning) {
            // Sub-options for "Plan a Trip"
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║              ◄ PLAN A TRIP ►             ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║                                          ║");
            System.out.println("║    [1] Trip Managment                    ║");
            System.out.println("║    [2] Browse Transports                 ║");
            System.out.println("║    [3] Manage Notes                      ║");
            System.out.println("║    [4] Manage Itinerary                  ║");
            System.out.println("║    [5] Track Budget                      ║");
            System.out.println("║    [6] Back to Main Menu                 ║");
            System.out.println("║                                          ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            int planOption = scanner.nextInt();
            switch (planOption) {
                case 1 -> showManageTripsOptions(scanner);
                case 2 -> browseTransports();
                case 3 -> new NoteKeepingDashboard().displayChecklist();  // Open note-keeping dashboard
                case 4 -> manageItinerary(scanner);  // Open itinerary management
                // case 5 -> trackBudget();  // Functionality for budget tracking
                case 6 -> isPlanning = false;  // Exit to main menu
                default -> {
                    System.out.println("\n╔══════════════════════════════════════════╗");
                    System.out.println("║    Invalid option. Please try again.     ║");
                    System.out.println("╚══════════════════════════════════════════╝");
                }
            }
        }
    }

    private void showManageTripsOptions(Scanner scanner) {
        boolean isManaging = true;

        while (isManaging) {
            // Sub-options for "Manage Trip"
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║            ◄ MANAGE TRIPS ►              ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║                                          ║");
            System.out.println("║    [1] View Trips                        ║");
            System.out.println("║    [2] Remove Trips                      ║");
            System.out.println("║    [3] Back to Main Menu                 ║");
            System.out.println("║                                          ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            int manageTripsOption = scanner.nextInt();
            switch (manageTripsOption) {
                case 1 -> t.viewTrips();
                case 2 -> t.removeTrip();
                case 3 -> isManaging = false;  // Exit to main menu
                default -> {
                    System.out.println("\n╔══════════════════════════════════════════╗");
                    System.out.println("║    Invalid option. Please try again.     ║");
                    System.out.println("╚══════════════════════════════════════════╝");
                }
            }
        }
    }

    public void browseTransports() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter starting location: ");
        String startLocation = scanner.nextLine().trim();

        System.out.print("Enter destination: ");
        String destination = scanner.nextLine().trim();

        String busFile = "src\\TXT_Files\\bus.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(busFile))) {
            String line;
            boolean found = false;

            // Print header row with column titles
            System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-20s %-20s %-20s %-20s %-20s ║%n", 
                "Bus Name", "Starting Location", "Destination", "Starting Time", "Contact Number");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

            while ((line = reader.readLine()) != null) {
                String[] busDetails = line.split(",");

                if (busDetails.length >= 8) {
                    String busName = busDetails[0].trim();
                    String busStartLocation = busDetails[1].trim();
                    String busDestination = busDetails[2].trim();
                    String startingTime = busDetails[3].trim();
                    String contactNumber = busDetails[5].trim();

                    // Check if the bus matches the user's search criteria
                    if (busStartLocation.equalsIgnoreCase(startLocation) && busDestination.equalsIgnoreCase(destination)) {
                        // Print each bus detail in aligned columns
                        System.out.printf("║ %-20s %-20s %-20s %-20s %-20s ║%n", 
                            busName, busStartLocation, busDestination, startingTime, contactNumber);
                        found = true;
                    }
                }
            }

            // If no buses are found, print a message
            if (!found) {
                System.out.println("║ No buses found for the given route.                                               ║");
            }
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

        } catch (IOException e) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║    An error occurred while reading the   ║");
            System.out.println("║    bus file: " + e.getMessage() + "     ║");
            System.out.println("╚══════════════════════════════════════════╝");
        }
    }

    private void manageItinerary(Scanner scanner) {
        boolean itinerary = true;

        while (itinerary) {
            // Sub-options for "Manage Itinerary"
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║          ◄ MANAGE ITINERARY ►            ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║                                          ║");
            System.out.println("║    [1] View Trips on Calendar            ║");
            System.out.println("║    [2] Set Reminder                      ║");
            System.out.println("║    [3] Back to Main Menu                 ║");
            System.out.println("║                                          ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            int manageItineraryOption = scanner.nextInt();
            switch (manageItineraryOption) {
                case 1 -> t.viewTripsOnCalendar();
                case 2 -> {
                    AlertSystem a = new AlertSystem();
                    a.alertSystem();
                }
                case 3 -> itinerary = false;  // Exit to main menu
                default -> {
                    System.out.println("\n╔══════════════════════════════════════════╗");
                    System.out.println("║    Invalid option. Please try again.     ║");
                    System.out.println("╚══════════════════════════════════════════╝");
                }
            }
        }
    }


}
