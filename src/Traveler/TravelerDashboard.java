package Traveler;

import Authentication.DeleteAccount;
import Traveler.Checklist_NoteKeeping.CheckList.*;
import Traveler.Checklist_NoteKeeping.NoteKeeping.*;
import Traveler.Itinerary_Management.*;
import Traveler.Trip_Management.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class TravelerDashboard {
    TripManager t = new TripManager();

    public void showDashboard() throws NoSuchAlgorithmException, IOException {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            // Main dashboard options
            System.out.println(" ________________________________");
            System.out.println("|            TRAVELER            |");
            System.out.println("|________________________________|");

            System.out.println("[1] Plan a Trip");
            System.out.println("[2] Manage Trips");
            System.out.println("[3] Review");
            System.out.println("[4] Manage Account");
            System.out.println("[5] Exit");
            System.out.print("Enter your choice: ");

            int mainOption = scanner.nextInt();

            switch (mainOption) {
                case 1 -> showPlanATripOptions(scanner);  // Call the sub-option method for "Plan a Trip"
                case 2 -> showManageTripsOptions(scanner);  // Functionality for "Browse Trips"
                case 3 -> review();  // Functionality for "Review"
                case 4 -> showManageAccountOptions();  // Option to delete account
                case 5 -> {
                    System.out.println("Exiting Traveler Dashboard...");
                    isRunning = false;  // Exit the loop
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }




    private void showPlanATripOptions(Scanner scanner) {
        boolean isPlanning = true;

        while (isPlanning) {
            // Sub-options for "Plan a Trip"
            System.out.println("\n--- Plan a Trip ---");

            System.out.println("[1] Add Trip");
            System.out.println("[2] Browse Transports");
            System.out.println("[3] Manage Notes");
            System.out.println("[4] Manage Itinerary");
            System.out.println("[5] Track Budget");
            System.out.println("[6] Back to Main Menu");
            System.out.print("Enter your choice: ");

            int planOption = scanner.nextInt();
            switch (planOption) {
                case 1 -> t.addTrip();
                ;
                case 2 -> browseTransports();
                case 3 -> new NoteKeepingDashboard().displayChecklist();  // Open note-keeping dashboard
                case 4 -> new ItineraryDashboard().displayItinerary();  // Open itinerary management
                case 5 -> trackBudget();  // Functionality for budget tracking
                case 6 -> isPlanning = false;  // Exit to main menu
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }


    private void showManageTripsOptions(Scanner scanner) {
        boolean isManaging = true;

        while (isManaging) {
            // Sub-options for "Manage Trip"
            System.out.println("\n--- Manage Trips ---");
            System.out.println("[1] View Trips");
            System.out.println("[2] Remove Trips");
            System.out.println("[3] Back to Main Menu");
            System.out.print("Enter your choice: ");

            int manageTripsOption = scanner.nextInt();
            switch (manageTripsOption) {
                case 1 -> t.viewTrips();
                case 2 -> t.removeTrip();
                case 3 -> isManaging = false;  // Exit to main menu
                default -> System.out.println("Invalid option. Please try again.");
            }
        }



    }

    private void review() {
    }

    private void trackBudget() {
    }


    private void showManageAccountOptions() {
    }
}
