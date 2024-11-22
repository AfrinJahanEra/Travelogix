// package Traveler;

// import Authentication.DeleteAccount;
// import Authentication.UserAccess;
// import Traveler.Checklist_NoteKeeping.NoteKeeping.*;
// import Traveler.Itinerary_Management.Alarm.AlertSystem;
// import Traveler.Trip_Management.*;
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.security.NoSuchAlgorithmException;
// import java.util.Scanner;

// public class TravelerDashboard {
//     TripManager t = new TripManager();
//     Scanner scanner = new Scanner(System.in);
//     public void showDashboard() throws NoSuchAlgorithmException, IOException {

//         boolean isRunning = true;

//         while (isRunning) {
//             // Main dashboard options
//             System.out.println(" ________________________________");
//             System.out.println("|            TRAVELER            |");
//             System.out.println("|________________________________|");

//             System.out.println("[1] Plan a Trip");
//             System.out.println("[2] Manage Trips");
//             System.out.println("[3] Review");
//             System.out.println("[4] Delete Account");
//             System.out.println("[5] Exit");
//             System.out.print("Enter your choice: ");

//             int mainOption = scanner.nextInt();

//             switch (mainOption) {
//                 case 1 -> showPlanATripOptions(scanner);  // Call the sub-option method for "Plan a Trip"
//                 case 2 -> showManageTripsOptions(scanner);  // Functionality for "Browse Trips"
//                 case 3 -> review();  // Functionality for "Review"
//                 case 4 -> new DeleteAccount().deleteAccount(); // Option to delete account
//                 case 5 -> {
//                     System.out.println("Exiting Traveler Dashboard...");
//                     isRunning = false;
//                     UserAccess u = new UserAccess();
//                     u.start();// Exit the loop
//                 }
//                 default -> System.out.println("Invalid option. Please try again.");
//             }
//         }
//         scanner.close();
//     }




//     private void showPlanATripOptions(Scanner scanner) {
//         boolean isPlanning = true;

//         while (isPlanning) {
//             // Sub-options for "Plan a Trip"
//             System.out.println("\n--- Plan a Trip ---");

//             System.out.println("[1] Add Trip");
//             System.out.println("[2] Browse Transports");
//             System.out.println("[3] Manage Notes");
//             System.out.println("[4] Manage Itinerary");
//             System.out.println("[5] Track Budget");
//             System.out.println("[6] Back to Main Menu");
//             System.out.print("Enter your choice: ");

//             int planOption = scanner.nextInt();
//             switch (planOption) {
//                 case 1 -> t.addTrip();
//                 case 2 -> browseTransports();
//                 case 3 -> new NoteKeepingDashboard().displayChecklist();  // Open note-keeping dashboard
//                 case 4 -> manageItinerary(scanner);  // Open itinerary management
//                 // case 5 -> trackBudget();  // Functionality for budget tracking
//                 case 6 -> isPlanning = false;  // Exit to main menu
//                 default -> System.out.println("Invalid option. Please try again.");
//             }
//         }
//     }




//     private void showManageTripsOptions(Scanner scanner) {
//         boolean isManaging = true;

//         while (isManaging) {
//             // Sub-options for "Manage Trip"
//             System.out.println("\n\n--- Manage Trips ---");
//             System.out.println("[1] View Trips");
//             System.out.println("[2] Remove Trips");
//             System.out.println("[3] Back to Main Menu");
//             System.out.print("Enter your choice: ");

//             int manageTripsOption = scanner.nextInt();
//             switch (manageTripsOption) {
//                 case 1 -> t.viewTrips();
//                 case 2 -> t.removeTrip();
//                 case 3 -> isManaging = false;  // Exit to main menu
//                 default -> System.out.println("Invalid option. Please try again.");
//             }
//         }



//     }

//     public void browseTransports() {
//         Scanner scanner = new Scanner(System.in);
//         System.out.print("Enter starting location: ");
//         String startLocation = scanner.nextLine();  // trim() to remove any leading/trailing spaces

//         System.out.print("Enter destination: ");
//         String destination = scanner.nextLine();

//         String busFile = "src\\TXT_Files\\bus.txt";  // Ensure this path is correct for your setup

//         try (BufferedReader reader = new BufferedReader(new FileReader(busFile))) {
//             String line;
//             boolean found = false;

//             while ((line = reader.readLine()) != null) {
//                 String[] busDetails = line.split(",");

//                 if (busDetails.length >= 8) {
//                     String busName = busDetails[0].trim();
//                     String busStartLocation = busDetails[1].trim();
//                     String busDestination = busDetails[2].trim();
//                     String startingTime = busDetails[3].trim();

//                     if (busStartLocation.equalsIgnoreCase(startLocation) && busDestination.equalsIgnoreCase(destination)) {
//                         System.out.println("Bus Name: " + busName +
//                                 ", Starting Location: " + busStartLocation +
//                                 ", Destination: " + busDestination +
//                                 ", Starting Time: " + startingTime);
//                         found = true;
//                     }
//                 }
//             }

//             if (!found) {
//                 System.out.println("No buses found for the given route.");
//             }

//         } catch (IOException e) {
//             System.out.println("An error occurred while reading the bus file: " + e.getMessage());
//         }
//     }


//     private void manageItinerary(Scanner scanner) {
//         boolean itinerary = true;

//         while (itinerary) {
//             // Sub-options for "Manage Trip"
//             System.out.println("\n\n--- Manage Itinerary ---");
//             System.out.println("[1] View Trips on Calendar");
//             System.out.println("[2] Set Reminder");
//             System.out.println("[3] Back to Main Menu");
//             System.out.print("Enter your choice: ");

//             int manageItineraryOption = scanner.nextInt();
//             switch (manageItineraryOption) {
//                 case 1 -> t.viewTripsOnCalendar();
//                 case 2 -> {
//                     AlertSystem a = new AlertSystem();
//                     a.alertSystem();
//                 }
//                 case 3 -> itinerary = false;  // Exit to main menu
//                 default -> System.out.println("Invalid option. Please try again.");
//             }
//         }
//     }


//     private void review() {
//     }

//     private void trackBudget() {
//     }


//     private void showManageAccountOptions() {
//     }
// }

package Traveler;

import Authentication.DeleteAccount;
import Authentication.UserAccess;
import Traveler.Checklist_NoteKeeping.NoteKeeping.NoteKeepingDashboard;
import Traveler.Itinerary_Management.Alarm.AlertSystem;
import Traveler.Trip_Management.TripManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class TravelerDashboard {
    TripManager t = new TripManager();
    Scanner scanner = new Scanner(System.in);

    public void showDashboard() throws NoSuchAlgorithmException, IOException {
        boolean isRunning = true;

        while (isRunning) {
            // Main dashboard options
            System.out.println(" ________________________________");
            System.out.println("|            TRAVELER            |");
            System.out.println("|________________________________|");

            System.out.println("[1] Plan a Trip");
            System.out.println("[2] Manage Trips");
            System.out.println("[3] Review");
            System.out.println("[4] Delete Account");
            System.out.println("[5] Log Out");
            System.out.print("Enter your choice: ");

            int mainOption = scanner.nextInt();

            switch (mainOption) {
                case 1 -> showPlanATripOptions(scanner);  // Call the sub-option method for "Plan a Trip"
                case 2 -> showManageTripsOptions(scanner);  // Functionality for "Browse Trips"
                case 3 -> review();  // Functionality for "Review"
                case 4 -> {
                    if (new DeleteAccount().deleteAccount()) {
                        System.out.println("Account deleted successfully. Returning to dashboard...");
                        isRunning = false;
                        UserAccess u = new UserAccess();
                        u.start(); // Return to user access point
                    } else {
                        System.out.println("Account deletion cancelled or failed.");
                    }
                }
                case 5 -> {
                    System.out.println("Exiting Traveler Dashboard...");
                    isRunning = false;
                    UserAccess u = new UserAccess();
                    u.start();// Exit the loop
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private void showPlanATripOptions(Scanner scanner) {
        boolean isPlanning = true;

        while (isPlanning) {
            // Sub-options for "Plan a Trip"
            System.out.println("\n--- Plan a Trip ---");

            System.out.println("[1] Add Trip");
            System.out.println("[2] Browse Transports");
            System.out.println("[3] Manage Notes");
            System.out.println("[4] Manage Itinerary");
            System.out.println("[5] Track Budget");
            System.out.println("[6] Back to Main Menu");
            System.out.print("Enter your choice: ");

            int planOption = scanner.nextInt();
            switch (planOption) {
                case 1 -> t.addTrip();
                case 2 -> browseTransports();
                case 3 -> new NoteKeepingDashboard().displayChecklist();  // Open note-keeping dashboard
                case 4 -> manageItinerary(scanner);  // Open itinerary management
                // case 5 -> trackBudget();  // Functionality for budget tracking
                case 6 -> isPlanning = false;  // Exit to main menu
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showManageTripsOptions(Scanner scanner) {
        boolean isManaging = true;

        while (isManaging) {
            // Sub-options for "Manage Trip"
            System.out.println("\n\n--- Manage Trips ---");
            System.out.println("[1] View Trips");
            System.out.println("[2] Remove Trips");
            System.out.println("[3] Back to Main Menu");
            System.out.print("Enter your choice: ");

            int manageTripsOption = scanner.nextInt();
            switch (manageTripsOption) {
                case 1 -> t.viewTrips();
                case 2 -> t.removeTrip();
                case 3 -> isManaging = false;  // Exit to main menu
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // public void browseTransports() {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.print("Enter starting location: ");
    //     String startLocation = scanner.nextLine().trim();

    //     System.out.print("Enter destination: ");
    //     String destination = scanner.nextLine().trim();

    //     String busFile = "src\\TXT_Files\\bus.txt";

    //     try (BufferedReader reader = new BufferedReader(new FileReader(busFile))) {
    //         String line;
    //         boolean found = false;

    //         while ((line = reader.readLine()) != null) {
    //             String[] busDetails = line.split(",");

    //             if (busDetails.length >= 8) {
    //                 String busName = busDetails[0].trim();
    //                 String busStartLocation = busDetails[1].trim();
    //                 String busDestination = busDetails[2].trim();
    //                 String startingTime = busDetails[3].trim();
    //                 String contactNumber = busDetails[5].trim();

    //                 if (busStartLocation.equalsIgnoreCase(startLocation) && busDestination.equalsIgnoreCase(destination)) {
    //                     System.out.println("Bus Name: " + busName +
    //                             ", Starting Location: " + busStartLocation +
    //                             ", Destination: " + busDestination +
    //                             ", Starting Time: " + startingTime +
    //                             ", Contact Number: " + contactNumber);
    //                     found = true;
    //                 }
    //             }
    //         }

    //         if (!found) {
    //             System.out.println("No buses found for the given route.");
    //         }

    //     } catch (IOException e) {
    //         System.out.println("An error occurred while reading the bus file: " + e.getMessage());
    //     }
    // }

    public void browseTransports() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter starting location: ");
        String startLocation = scanner.nextLine().trim();

        System.out.print("Enter destination: ");
        String destination = scanner.nextLine().trim();

        String busFile = "src\\TXT_Files\\bus.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(busFile))) {
            String line;
            boolean found = false;

            // Print header row with column titles
            System.out.printf("%-20s %-20s %-20s %-20s %-20s%n", 
                    "Bus Name", "Starting Location", "Destination", "Starting Time", "Contact Number");
            System.out.println("------------------------------------------------------------------------------------------");

            while ((line = reader.readLine()) != null) {
                String[] busDetails = line.split(",");

                if (busDetails.length >= 8) {
                    String busName = busDetails[0].trim();
                    String busStartLocation = busDetails[1].trim();
                    String busDestination = busDetails[2].trim();
                    String startingTime = busDetails[3].trim();
                    String contactNumber = busDetails[5].trim();

                    // Check if the bus matches the user's search criteria
                    if (busStartLocation.equalsIgnoreCase(startLocation) && busDestination.equalsIgnoreCase(destination)) {
                        // Print each bus detail in aligned columns
                        System.out.printf("%-20s %-20s %-20s %-20s %-20s%n", 
                                busName, busStartLocation, busDestination, startingTime, contactNumber);
                        found = true;
                    }
                }
            }

            // If no buses are found, print a message
            if (!found) {
                System.out.println("No buses found for the given route.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the bus file: " + e.getMessage());
        }
    }



    private void manageItinerary(Scanner scanner) {
        boolean itinerary = true;

        while (itinerary) {
            // Sub-options for "Manage Trip"
            System.out.println("\n\n--- Manage Itinerary ---");
            System.out.println("[1] View Trips on Calendar");
            System.out.println("[2] Set Reminder");
            System.out.println("[3] Back to Main Menu");
            System.out.print("Enter your choice: ");

            int manageItineraryOption = scanner.nextInt();
            switch (manageItineraryOption) {
                case 1 -> t.viewTripsOnCalendar();
                case 2 -> {
                    AlertSystem a = new AlertSystem();
                    a.alertSystem();
                }
                case 3 -> itinerary = false;  // Exit to main menu
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void review() {
        // Review functionality here
    }

    private void trackBudget() {
        // Budget tracking functionality here
    }
}
