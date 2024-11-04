package Traveler.Itinerary_Management;

import java.util.Scanner;

import Traveler.Checklist_NoteKeeping.NoteKeeping.VoiceNote.VoiceNoteDashBoard;
import Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote.NotewritingDashboard;
import Traveler.Itinerary_Management.Alarm.AlertSystem;
import Traveler.Itinerary_Management.Calendar.Calendar;

public class ItineraryDashboard {

    private static final String tripFile = "trips.txt";
    public void displayItinerary() {

        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("[1] Set Alarm");
            System.out.println("[2] Mark Trip On Calender");
            System.out.println("[3] Go back (Exit)");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    AlertSystem a = new AlertSystem();
                    a.alertSystem();
                case 2:
                    Calendar calendar= new Calendar();
                    calendar.displayTripsOnCalendar(tripFile);
                    break;
                case 3:
                    System.out.println("Exiting the checklist manager. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please choose again.");
            }
        }
    }
}
