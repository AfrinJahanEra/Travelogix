package Traveler.Trip_Management;

import Traveler.Itinerary_Management.Calendar.Calendar;
import java.io.*;
import java.util.Scanner;

public class TripManager {

    private static final String TRIP_FILE = "trips.txt";
    private final Scanner scanner = new Scanner(System.in);

    // Method to add a trip to the file
    public void addTrip() {
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine().trim();

        System.out.print("Enter start date and time (yyyy-MM-dd HH:mm:ss): ");
        String startDate = scanner.nextLine().trim();

        System.out.print("Enter end date and time (yyyy-MM-dd HH:mm:ss): ");
        String endDate = scanner.nextLine().trim();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRIP_FILE, true))) {
            writer.write(destination + ", " + startDate + ", " + endDate);
            writer.newLine();
            System.out.println("Trip to " + destination + " added.\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to view trips on the calendar
    public void viewTripsOnCalendar() {
        viewTrips(); // Display the list of trips first
        Calendar calendar = new Calendar();
        calendar.displayTripsOnCalendar(TRIP_FILE); // Show only relevant months with marked dates
    }

    // Method to view all trips
    public void viewTrips() {
            try (BufferedReader reader = new BufferedReader(new FileReader(TRIP_FILE))) {
            String line;
            int index = 1;
            System.out.println("Your Trips: ");
            while ((line = reader.readLine()) != null) {
                String[] tripData = line.split(", ");
                System.out.println(index + ". Destination: " + tripData[0] + " | Start: " + tripData[1] + " | End: " + tripData[2]);
                index++;
            }
            if (index == 1) {
                System.out.println("No trips found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to remove a trip by its serial number
    public void removeTrip() {
        viewTrips(); // Show all trips first

        System.out.print("Enter the index of the trip you want to remove: ");
        int serialNumber = scanner.nextInt();

        File tempFile = new File("temp.txt");
        File tripFile = new File(TRIP_FILE);

        try (BufferedReader reader = new BufferedReader(new FileReader(tripFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            int currentLine = 1;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (currentLine == serialNumber) {
                    found = true;
                    System.out.println("Trip at serial number " + serialNumber + " is removed.");
                } else {
                    writer.write(line);
                    writer.newLine();
                }
                currentLine++;
            }
            if (!found) {
                System.out.println("Trip with serial number " + serialNumber + " not found.");
            }
        } catch (IOException e) {
            System.out.println("Error handling file: " + e.getMessage());
        }

        // Replace original file with the updated one
        tripFile.delete();
        tempFile.renameTo(tripFile);

    }
}
