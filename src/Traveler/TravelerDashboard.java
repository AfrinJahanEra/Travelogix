package Traveler;

import Traveler.Checklist_NoteKeeping.CheckList.CheckListdashBoard;
import Traveler.Checklist_NoteKeeping.NoteKeeping.NoteKeepingDashboard;
import Traveler.Itinerary_Management.ItineraryDashboard;
import Traveler.Trip_Management.TripDashboard;
import java.util.Scanner;

public class TravelerDashboard {

    public void showDashboard() {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println(" ________________________________");
            System.out.println("|            TRAVELER            |");
            System.out.println("|________________________________|");

            System.out.println("[1] Itinerary Management");
            System.out.println("[2] Keep Notes");
            System.out.println("[3] CheckList");
            System.out.println("[4] Trip Management");
            System.out.println("[5] Exit");
            System.out.print("Enter the index of the option you want to select: ");

            int mainOption = scanner.nextInt();

            switch (mainOption) {
                case 1:
                    ItineraryDashboard itineraryDashboard = new ItineraryDashboard();
                    itineraryDashboard.displayItinerary();
                    break;
                case 2:
                    NoteKeepingDashboard noteKeepingDashboard = new NoteKeepingDashboard();
                    noteKeepingDashboard.displayChecklist();
                    break;
                case 3:
                    CheckListdashBoard checkListDashboard = new CheckListdashBoard();
                    checkListDashboard.displayChecklist();
                    break;
                case 4:
                    TripDashboard tripDashboard = new TripDashboard();
                    tripDashboard.showDashboard(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the Traveler Dashboard.");
                    return;  // Exit the method to stop the loop and exit the dashboard
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
