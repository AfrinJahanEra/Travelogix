package Traveler;

import java.util.Scanner;

import src.Traveler.Checklist_NoteKeeping.NoteKeeping.NoteKeepingDashboard;
import src.Traveler.Itinerary_Management.ItineraryDashboard;
import src.Traveler.Trip_Management.TripDashboard;

public class BrowseTripDashboard {
    void showBrowseTripDashboard(){
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println(" ________________________________");
            System.out.println("|            TRAVELER            |");
            System.out.println("|________________________________|");

            System.out.println("[1] Location Selection");
            System.out.println("[2] Browse Transport");
            System.out.println("[3] Trip Management");
            System.out.println("[4] Back To Traveler DashBoard");
            System.out.print("Enter your choice: ");

            int mainOption = scanner.nextInt();
            switch (mainOption) {
                case 1 -> new ItineraryDashboard().displayItinerary();
                case 2 -> new NoteKeepingDashboard().displayChecklist();
                case 3 -> new TripDashboard().showDashboard(scanner);
                case 4 -> {
                    System.out.println("Exiting Traveler Dashboard...");
                    new TravelerDashboard().showDashboard();
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
