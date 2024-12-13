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

    public void viewTripsOnCalendar() {
        viewTrips();
        Calendar calendar = new Calendar();
        calendar.displayTripsOnCalendar(TRIP_FILE); 
    }

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


    public void removeTrip() {
        viewTrips(); 

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
        LocalDate today = LocalDate.now(); 
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(tripFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            int currentLine = 1;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] tripData = line.split(", ");
                LocalDate startDate = LocalDate.parse(tripData[1].split(" ")[0], dateFormatter);
                if (currentLine == serialNumber) {
                    if (startDate.isBefore(today)) {
                        System.out.println("Cannot remove previous trips.");
                        writer.write(line); 
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

        if (tripFile.delete()) {
            if (!tempFile.renameTo(tripFile)) {
                System.out.println("Error renaming temp file to original trip file.");
            }
        } else {
            System.out.println("Error deleting original trip file.");
        }
    }
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
