// package Traveler;

// import java.util.Scanner;

// import src.Traveler.Checklist_NoteKeeping.NoteKeeping.NoteKeepingDashboard;
// import src.Traveler.Itinerary_Management.ItineraryDashboard;
// import src.Traveler.Trip_Management.TripDashboard;

// public class BrowseTripDashboard {
//     void showBrowseTripDashboard(){
//         Scanner scanner = new Scanner(System.in);
//         boolean isRunning = true;

//         while (isRunning) {
//             System.out.println(" ________________________________");
//             System.out.println("|            TRAVELER            |");
//             System.out.println("|________________________________|");

//             System.out.println("[1] Location Selection");
//             System.out.println("[2] Browse Transport");
//             System.out.println("[3] Trip Management");
//             System.out.println("[4] Back To Traveler DashBoard");
//             System.out.print("Enter your choice: ");

//             int mainOption = scanner.nextInt();
//             switch (mainOption) {
               
//                 case 4 -> {
//                     System.out.println("Exiting Traveler Dashboard...");
//                     new TravelerDashboard().showDashboard();
//                 }
//                 default -> System.out.println("Invalid option. Please try again.");
//             }
//         }
//     }
// }

package Traveler;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class BrowseTripDashboard {
    private Scanner scanner = new Scanner(System.in);

    public void showBrowseTripDashboard() throws NoSuchAlgorithmException, IOException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println(" ________________________________");
            System.out.println("|           TRAVELER             |");
            System.out.println("|________________________________|");
            System.out.println("[1] Browse Locations");
            System.out.println("[2] Browse Transport");
            System.out.println("[3] Go To Traveler Dashboard");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> browseLocations();
                case 2 -> browseTransport();
                case 3 -> {
                    System.out.println("Exiting Browse Location Dashboard...");
                    new TravelerDashboard().showDashboard();
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void browseLocations() {
        ViewBusList viewBusList = new ViewBusList("C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\bus.txt");
        System.out.print("Enter location name or select from available options: ");
        String location = scanner.nextLine();
        
        String locationsList = viewBusList.list(1);
        System.out.println("Available starting locations:\n" + locationsList);
    }

    private void browseTransport() {
        ViewBusList viewBusList = new ViewBusList("C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\bus.txt");
        System.out.println("Browsing all buses with starting and ending locations:");
        System.out.println(viewBusList.list(0));
        
        System.out.print("Enter number plate to view detailed bus info: ");
        String numberPlate = scanner.nextLine();
        ViewBusDetails viewBusDetails = new ViewBusDetails("C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\bus.txt");
        
        try {
            String details = viewBusDetails.viewBusDetails(numberPlate);
            System.out.println(details);
        } catch (IOException e) {
            System.out.println("Error reading bus details.");
        }
    }
}

