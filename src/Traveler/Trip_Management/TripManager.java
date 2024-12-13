// package Traveler.Trip_Management;

// import Traveler.Itinerary_Management.Calendar.Calendar;
// import java.io.*;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.Scanner;

// public class TripManager {

//     private static final String TRIP_FILE = "src\\TXT_Files\\trips.txt";
//     private final Scanner scanner = new Scanner(System.in);

//     // Method to add a trip to the file
//     public void addTrip() {
//         System.out.println("Enter trip details:");

//         System.out.print("Destination          : ");
//         String destination = scanner.nextLine().trim();

//         System.out.print("Start date and time  : (yyyy-MM-dd HH:mm:ss) ");
//         String startDate = scanner.nextLine().trim();

//         System.out.print("End date and time    : (yyyy-MM-dd HH:mm:ss) ");
//         String endDate = scanner.nextLine().trim();

//         try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRIP_FILE, true))) {
//             writer.write(destination + ", " + startDate + ", " + endDate);
//             writer.newLine();
//             System.out.println("Trip to " + destination + " added successfully.\n");
//         } catch (IOException e) {
//             System.out.println("Error writing to file: " + e.getMessage());
//         }
//     }

//     // Method to view trips on the calendar
//     public void viewTripsOnCalendar() {
//         viewTrips(); // Display the list of trips first
//         Calendar calendar = new Calendar();
//         calendar.displayTripsOnCalendar(TRIP_FILE); // Show relevant months with marked dates
//     }

//     // Method to view all trips in a table format
//     public void viewTrips() {
//         try (BufferedReader reader = new BufferedReader(new FileReader(TRIP_FILE))) {
//             String line;
//             int index = 1;

//             System.out.println("════════════════════════════════════════════════════════════════════════");
//             System.out.println("║ No. ║ Destination        ║ Start               ║ End                 ║");
//             System.out.println("════════════════════════════════════════════════════════════════════════");

//             while ((line = reader.readLine()) != null) {
//                 String[] tripData = line.split(", ");
//                 System.out.printf("║ %-3d ║ %-18s ║ %-19s ║ %-19s ║\n", 
//                                   index, tripData[0], tripData[1], tripData[2]);
//                 index++;
//             }

//             if (index == 1) {
//                 System.out.println("║ No trips found.                                                     ║");
//             }

//             System.out.println("════════════════════════════════════════════════════════════════════════");
//         } catch (IOException e) {
//             System.out.println("Error reading file: " + e.getMessage());
//         }
//     }

//     // Method to remove a trip by its serial number
//     public void removeTrip() {
//         viewTrips(); // Show all trips first

//         System.out.print("Enter the index of the trip you want to remove: ");
//         int serialNumber = scanner.nextInt();
//         scanner.nextLine(); // Consume newline character

//         File tripFile = new File(TRIP_FILE);
//         File tempFile = new File("src\\TXT_Files\\temp.txt");
//         LocalDate today = LocalDate.now(); // Current date
//         DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//         try (BufferedReader reader = new BufferedReader(new FileReader(tripFile));
//              BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

//             String line;
//             int currentLine = 1;
//             boolean found = false;

//             while ((line = reader.readLine()) != null) {
//                 String[] tripData = line.split(", ");
//                 LocalDate startDate = LocalDate.parse(tripData[1].split(" ")[0], dateFormatter);

//                 // Check if the trip is in the past
//                 if (currentLine == serialNumber) {
//                     if (startDate.isBefore(today)) {
//                         System.out.println("Cannot remove previous trips.");
//                         writer.write(line); // Write the line back, as it can't be deleted
//                         writer.newLine();
//                     } else {
//                         found = true;
//                         System.out.println("Trip at serial number " + serialNumber + " has been removed.");
//                     }
//                 } else {
//                     writer.write(line);
//                     writer.newLine();
//                 }
//                 currentLine++;
//             }

//             if (!found) {
//                 System.out.println("Trip with serial number " + serialNumber + " not found or cannot be removed.");
//             }

//         } catch (IOException e) {
//             System.out.println("Error handling file: " + e.getMessage());
//             return;
//         }

//         // Replace the original file only if successful
//         if (tripFile.delete()) {
//             if (!tempFile.renameTo(tripFile)) {
//                 System.out.println("Error renaming temp file to original trip file.");
//             }
//         } else {
//             System.out.println("Error deleting original trip file.");
//         }
//     }
// }



package Traveler.Trip_Management;

import Traveler.Itinerary_Management.Calendar.Calendar;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TripManager {

    private static final String TRIP_FILE = "src\\TXT_Files\\trips.txt";
    private final Scanner scanner = new Scanner(System.in);

    // Method to add a trip to the file
    public void addTrip() {
        System.out.println("Enter trip details:");

        System.out.print("Destination          : ");
        String destination = scanner.nextLine().trim();

        String startDate = "";
        while (true) {
            System.out.print("Start date and time  : (yyyy-MM-dd HH:mm:ss) ");
            startDate = scanner.nextLine().trim();
            if (isValidDateTime(startDate)) {
                break;
            } else {
                System.out.println("Invalid format. Please follow the format yyyy-MM-dd HH:mm:ss.");
            }
        }

        String endDate = "";
        while (true) {
            System.out.print("End date and time    : (yyyy-MM-dd HH:mm:ss) ");
            endDate = scanner.nextLine().trim();
            if (isValidDateTime(endDate)) {
                break;
            } else {
                System.out.println("Invalid format. Please follow the format yyyy-MM-dd HH:mm:ss.");
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRIP_FILE, true))) {
            writer.write(destination + ", " + startDate + ", " + endDate);
            writer.newLine();
            System.out.println("Trip to " + destination + " added successfully.\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to view trips on the calendar
    public void viewTripsOnCalendar() {
        viewTrips(); // Display the list of trips first
        Calendar calendar = new Calendar();
        calendar.displayTripsOnCalendar(TRIP_FILE); // Show relevant months with marked dates
    }

    // Method to view all trips in a table format
    public void viewTrips() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TRIP_FILE))) {
            String line;
            int index = 1;

            System.out.println("════════════════════════════════════════════════════════════════════════");
            System.out.println("║ No. ║ Destination        ║ Start               ║ End                 ║");
            System.out.println("════════════════════════════════════════════════════════════════════════");

            while ((line = reader.readLine()) != null) {
                String[] tripData = line.split(", ");
                System.out.printf("║ %-3d ║ %-18s ║ %-19s ║ %-19s ║\n",
                        index, tripData[0], tripData[1], tripData[2]);
                index++;
            }

            if (index == 1) {
                System.out.println("║ No trips found.                                                     ║");
            }

            System.out.println("════════════════════════════════════════════════════════════════════════");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to remove a trip by its serial number
    public void removeTrip() {
        viewTrips(); // Show all trips first

        int serialNumber = -1;
        while (true) {
            System.out.print("Enter the index of the trip you want to remove: ");
            try {
                serialNumber = Integer.parseInt(scanner.nextLine().trim());
                if (serialNumber > 0) {
                    break;
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        File tripFile = new File(TRIP_FILE);
        File tempFile = new File("src\\TXT_Files\\temp.txt");
        LocalDate today = LocalDate.now(); // Current date
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(tripFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            int currentLine = 1;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] tripData = line.split(", ");
                LocalDate startDate = LocalDate.parse(tripData[1].split(" ")[0], dateFormatter);

                // Check if the trip is in the past
                if (currentLine == serialNumber) {
                    if (startDate.isBefore(today)) {
                        System.out.println("Cannot remove previous trips.");
                        writer.write(line); // Write the line back, as it can't be deleted
                        writer.newLine();
                    } else {
                        found = true;
                        System.out.println("Trip at serial number " + serialNumber + " has been removed.");
                    }
                } else {
                    writer.write(line);
                    writer.newLine();
                }
                currentLine++;
            }

            if (!found) {
                System.out.println("Trip with serial number " + serialNumber + " not found or cannot be removed.");
            }

        } catch (IOException | DateTimeParseException e) {
            System.out.println("Error handling file: " + e.getMessage());
            return;
        }

        // Replace the original file only if successful
        if (tripFile.delete()) {
            if (!tempFile.renameTo(tripFile)) {
                System.out.println("Error renaming temp file to original trip file.");
            }
        } else {
            System.out.println("Error deleting original trip file.");
        }
    }

    // Helper method to validate date-time format
    private boolean isValidDateTime(String dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            formatter.parse(dateTime);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
