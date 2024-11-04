package Traveler;

import Authentication.DeleteAccount;
import Authentication.ManageAccountDash;
import Authentication.UserAccess;
import Traveler.Checklist_NoteKeeping.CheckList.*;
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


            System.out.println("[1] Plan a Trip");
            System.out.println("[2] Browse Trips");
            System.out.println("[3] Reviwes");
            System.out.println("[4] Manage Account");
            System.out.println("[5] Exit");
        
            System.out.print("Enter your choice: ");


            int mainOption = scanner.nextInt();
            switch (mainOption) {
                case 1 -> new PlanTripDashboard().showPlanTripDashboard();
                case 2 -> new BrowseTripDashboard().showBrowseTripDashboard();
                case 3 -> new TripDashboard().showDashboard(scanner);
                case 4 -> new ManageAccountDash().showManageAccountDash();
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
