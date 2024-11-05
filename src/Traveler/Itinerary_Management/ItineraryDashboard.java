package Traveler.Itinerary_Management;

import Traveler.Itinerary_Management.Alarm.AlertSystem;
import Traveler.Itinerary_Management.Calendar.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ItineraryDashboard {

    private static final String tripFile = "C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\trips.txt";

    public void displayItinerary() {
        Scanner scanner = new Scanner(System.in); // Single scanner instance

        while (true) {
            System.out.println("[1] Set Alarm");
            System.out.println("[2] Mark Trip On Calendar");
            System.out.println("[3] Go back (Exit)");
            System.out.print("Enter your choice: ");

            // Handle potential InputMismatchException
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear the newline

                switch (choice) {
                    case 1:
                        AlertSystem alertSystem = new AlertSystem();
                        alertSystem.alertSystem(); // Pass scanner
                        break;
                    case 2:
                        Calendar calendar = new Calendar();
                        calendar.displayTripsOnCalendar(tripFile);
                        break;
                    case 3:
                        System.out.println("Exiting the checklist manager. Goodbye!");
                        scanner.close(); // Close scanner only here
                        return;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid choice.");
                scanner.nextLine(); // Clear invalid input to prevent looping
            }
        }
    }
}
