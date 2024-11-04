package Traveler;

import Authentication.DeleteAccount;
// import Authentication.UserAccess;
import Traveler.Checklist_NoteKeeping.CheckList.*;
import Traveler.Checklist_NoteKeeping.NoteKeeping.*;
import Traveler.Itinerary_Management.*;
import Traveler.Trip_Management.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class TravelerDashboard {

    public void showDashboard() throws NoSuchAlgorithmException, IOException {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println(" ________________________________");
            System.out.println("|            TRAVELER            |");
            System.out.println("|________________________________|");

            System.out.println("[1] Itinerary Management");
            System.out.println("[2] Keep Notes");
            System.out.println("[3] CheckList");
            System.out.println("[4] Trip Management");
            System.out.println("[5] Delete Account");
            System.out.println("[6] Exit");
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