package Traveler;

import Traveler.Checklist_NoteKeeping.NoteKeeping.NoteKeepingDashboard;
import Traveler.Itinerary_Management.ItineraryDashboard;
import Traveler.Trip_Management.TripDashboard;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class PlanTripDashboard {
        public void showPlanTripDashboard() throws NoSuchAlgorithmException, IOException {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println(" ________________________________");
            System.out.println("|            TRAVELER            |");
            System.out.println("|________________________________|");

            System.out.println("[1] Itinerary Management");
            System.out.println("[2] Notes");
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
