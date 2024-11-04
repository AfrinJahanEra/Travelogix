package Traveler;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import Authentication.DeleteAccount;
import Authentication.UserAccess;
import Traveler.Checklist_NoteKeeping.CheckList.CheckListdashBoard;
import Traveler.Checklist_NoteKeeping.NoteKeeping.NoteKeepingDashboard;
import Traveler.Itinerary_Management.ItineraryDashboard;
import Traveler.Trip_Management.TripDashboard;

public class PlanTripDashboard {
        public void PlanTripDashboard() throws NoSuchAlgorithmException, IOException {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println(" ________________________________");
            System.out.println("|            TRAVELER            |");
            System.out.println("|________________________________|");


            System.out.println("[1] Plan a Trip");
            System.out.println("[2] Browse Trips");
            System.out.println("[3] Trips");
            System.out.println("[4] Trip Management");
            System.out.println("[5] Delete Account");
            System.out.println("[6] Exit");
            System.out.print("Enter your choice: ");

            System.out.println("[1] Itinerary Management");
            System.out.println("[2] Notes");
            System.out.println("[3] Trip Management");
            System.out.println("[4] Back To Traveler DashBoard");
            System.out.print("Enter your choice: ");

            int mainOption = scanner.nextInt();
            switch (mainOption) {
                case 1 -> new ItineraryDashboard().displayItinerary();
                case 2 -> new NoteKeepingDashboard().displayChecklist();
                case 3 -> new CheckListdashBoard().displayChecklist();
                case 4 -> new TripDashboard().showDashboard(scanner);
                case 5 -> new DeleteAccount().deleteAccount();
                case 6 -> {
                    System.out.println("Exiting Traveler Dashboard...");
                    UserAccess userAccess = new UserAccess();
                    userAccess.start();
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
