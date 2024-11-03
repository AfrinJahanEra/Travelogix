package Traveler.Trip_Management;

import java.util.Scanner;

import Traveler.Checklist_NoteKeeping.CheckList.CheckListdashBoard;
import Traveler.Checklist_NoteKeeping.NoteKeeping.NoteKeepingDashboard;
import Traveler.Itinerary_Management.ItineraryDashboard;

public class TripDashboard {


    public void showDashboard(Scanner scanner) {
        TripManager t = new TripManager();

        while (true) {


            System.out.println("[1] Add Trip");
            System.out.println("[2] Remove Trip");
            System.out.println("[3] View Trip");
            System.out.println("[4] Trip Checklist");
            System.out.println("[5] Exit");
            System.out.print("Enter the index of the option you want to select (or 0 to exit): ");

            int mainOption = scanner.nextInt();

            switch (mainOption) {
                case 1:
                    t.addTrip();
                    break;
                case 2:
                    t.removeTrip();
                    break;
                case 3:
                    t.viewTripsOnCalendar();
                    break;
                case 4:
                    CheckListdashBoard dashboard = new CheckListdashBoard();
                    dashboard.displayChecklist();
                    break;
                case 5:
                    System.out.println("Exiting the Traveler Dashboard.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

    }
}