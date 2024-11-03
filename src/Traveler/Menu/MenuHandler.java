package Traveler.Menu;

import Traveler.Itinerary_Management.Alarm.AlertSystem;
import Traveler.Trip_Management.TripManager;
import java.util.Scanner;


public class MenuHandler {

    private final TripManager tripManager = new TripManager();
    private final AlertSystem as= new AlertSystem();

    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Manage Trip");
            System.out.println("2. Manage Note");
            System.out.println("3. Manage Checklist");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showTripMenu();
                    break;
                case 2:
                    // Implement note management
                    break;
                case 3:
                    // Implement checklist management
                    break;
                case 4:
                    System.out.println("Set reminder");
                    as.alertSystem();
                    return;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void showTripMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Trip");
            System.out.println("2. View Trip");
            System.out.println("3. Remove Trip");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    tripManager.addTrip();
                    break;
                case 2:
                    tripManager.viewTrips();
                    break;
                case 3:
                    tripManager.removeTrip();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
